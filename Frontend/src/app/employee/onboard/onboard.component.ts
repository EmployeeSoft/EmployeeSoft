import { Component, OnInit } from '@angular/core';
import {Validators} from '@angular/forms';
import { FormBuilder, FormGroup, FormControl, FormArray } from '@angular/forms';
import {AccountService, AlertService} from '../../common/_services';
import {User} from '../../common/_models';
import {Address} from '../../common/_models/address';
import {Contact} from '../../common/_models/contact';
import {first} from 'rxjs/operators';
import {ActivatedRoute, Router} from '@angular/router';
import {VisaStatus} from '../../common/_models/visaStatus';
import {Employee} from '../../common/_models/employee';
import {Person} from '../../common/_models/person';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-onboard',
  templateUrl: './onboard.component.html',
  styleUrls: ['./onboard.component.css']
})
export class OnboardComponent implements OnInit {
  submitted = false;
  public sample: any;
  Gender: any = ['Male', 'Female', 'I don\'t want to answer'];
  WorkAuthorization: any = ['H1B', 'L2', 'F1(CPT/OPT)', 'H4', 'Other Work Authorization'];
  isCitizen = 0;
  isOtherWorkAuthorization = false;
  isDriverLicense = false;
  user: User = {} as User;
  address: Address = {} as Address;
  contact: Contact = {} as Contact;
  visaStatus: VisaStatus = {} as VisaStatus;
  employee: Employee = {} as Employee;
  person: Person = {} as Person;
  contactList: Contact[] = [];
  email: string | null;
  userId: string | null;
  avatarFile: File = {} as File;
  userInfoSuccess: false;
  avatarSuccess: false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private alertService: AlertService,
    private accountService: AccountService
  ) { }

  ngOnInit(): void {
    this.email = this.route.snapshot.queryParamMap.get('email');
    this.userId = this.route.snapshot.queryParamMap.get('userId');
    this.sample = this.formBuilder.group({
      avatar: [''],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      middleName: [''],
      preferredName: [''],
      addressLine1: ['', Validators.required],
      addressLine2: [''],
      city: ['', Validators.required],
      zipcode: ['', Validators.required],
      stateName: ['', Validators.required],
      stateAbbr: ['', Validators.required],
      cellPhone: ['', Validators.required],
      workPhone: [''],
      carMaker: [''],
      carModel: [''],
      carColor: [''],
      email: [''],
      ssn: ['', Validators.required],
      dob: ['', Validators.required],
      gender: [''],
      citizenOrNot: [''],
      greenCardOrCitizen: [''],
      workAuthorization: [''],
      otherWorkAuthorization: [''],
      visaStartDate: [''],
      visaEndDate: [''],
      haveDriverLicense: [''],
      driverLicenseNumber: [''],
      driverLicenseExpDate: [''],
      driverLicenseCopy: [''],
      emergency: this.formBuilder.array([
        this.formBuilder.group({
          emergencyFullName: [''],
          emergencyPhone: [''],
          emergencyRelationship: [''],
          emergencyTitle: [''],
          emergencyAddress: ['']
        })
      ])
    });
  }

  get f() { return this.sample.controls; }

  getEmergency() {
    return this.sample.get('emergency') as FormArray;
  }

  addEmergency() {
    const temp = this.getEmergency();
    const list = this.formBuilder.group({
      emergencyFullName: [''],
      emergencyPhone: [''],
      emergencyRelationship: [''],
      emergencyTitle: [''],
      emergencyAddress: ['']
    });
    temp.insert(0, list);
  }

  deleteEmergency() {
    if (this.getEmergency().length > 1) {
      const temp = this.getEmergency();
      temp.removeAt(temp.length - 1);
    }
  }

  onSubmit(form: FormGroup) {
    console.log(form.value);
    this.submitted = true;
    this.alertService.clear();
    if (this.sample.invalid) {
      return;
    }

    if (typeof this.userId === 'string') {
      // tslint:disable-next-line:radix
      this.person.userId = parseInt(this.userId);
    }
    this.person.firstName = form.value.firstName;
    this.person.lastName = form.value.lastName;
    this.person.middleName = form.value.middleName;
    this.person.preferName = form.value.preferredName;
    this.person.email = this.email || '';
    this.person.cellPhone = form.value.cellPhone;
    this.person.altPhone = form.value.altPhone;
    this.person.gender = form.value.gender;
    this.person.ssn = form.value.ssn;
    this.person.dob = form.value.dob;

    if (form.value.citizenOrNot === 'yes') {
      if (form.value.greenCardOrCitizen === 'Green Card') { this.employee.visaStatusId = 1; }
      else { this.employee.visaStatusId = 2; }
      this.employee.visaStartDate = '';
      this.employee.visaEndDate = '';
    } else {
      this.employee.visaStatusId = 3;
      this.employee.visaStartDate = form.value.visaStartDate;
      this.employee.visaEndDate = form.value.visaEndDate;
    }
    this.employee.car = form.value.carMaker + '_' + form.value.carModel + '_' + form.value.carColor;
    if (form.value.haveDriverLicense === 'yes') {
      this.employee.driverLicense = form.value.driverLicenseNumber;
      this.employee.driverLicenseExpDate = form.value.driverLicenseExpDate;
    } else {
      this.employee.driverLicense = '';
      this.employee.driverLicenseExpDate = '';
    }

    this.address.addressLine1 = form.value.addressLine1;
    this.address.addressLine2 = form.value.addressLine2;
    this.address.city = form.value.city;
    this.address.zipcode = form.value.zipcode;
    this.address.stateName = form.value.stateName;
    this.address.stateAbbr = form.value.stateAbbr;

    for (let i = 0; i < form.value.emergency.length; i++) {
      this.contact.fullName = form.value.emergency[i].emergencyFullName;
      this.contact.phone = form.value.emergency[i].emergencyPhone;
      this.contact.relationship = form.value.emergency[i].emergencyRelationship;
      this.contact.title = form.value.emergency[i].emergencyTitle;
      this.contact.address = form.value.emergency[i].emergencyAddress;
      this.contact.isEmergency = true;
      this.contact.isReference = true;
      this.contact.isLandlord = false;
      this.contactList.push(this.contact);
    }

    // Uploading user info
    // this.accountService.onboard(this.person, this.employee, this.address, this.contactList)
    //   .pipe(first())
    //   .subscribe({
    //     next: (x) => {
    //       console.log(x);
    //       this.alertService.success('successful', { keepAfterRouteChange: true });
    //       this.router.navigateByUrl('/home');
    //     },
    //     error: error => {
    //       this.alertService.error(error);
    //     }
    //   });

    // Uploading file
    console.log('Uploading file');
    const formData = new FormData();
    formData.append('file', this.avatarFile, this.avatarFile.name);
    // formData.append('userId', this.userId!);
    formData.append('userId', '1');
    formData.append('uploadTo', 'avatar');
    formData.append('fileTitle', this.avatarFile.name);
    this.accountService.upload(formData).subscribe(
      event => {
        console.log(event);
        console.log(typeof event);
        // console.log();
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );

    // Redirect to a different page
  }

  get genderValue() {
    return this.sample.get('gender');
  }

  changeGender(e: any) {
    this.genderValue.setValue(e.target.value, {
      onlySelf: true
    });
  }

  get workAuthorizationValue() {
    return this.sample.get('workAuthorization');
  }

  changeWorkAuthorization(e: any) {
    this.workAuthorizationValue.setValue(e.target.value, {
      onlySelf: true
    });
    const chosenValue = this.workAuthorizationValue.value.substring(0, 2);
    if (chosenValue === '5:') {
      this.isOtherWorkAuthorization = true;
    } else { this.isOtherWorkAuthorization = false; }
  }

  setAvatar(event: any) {
    console.log(event.target.files[0]);
    this.avatarFile = event.target.files[0];
  }

  onDownload() {
    this.accountService.download('test');
  }
}
