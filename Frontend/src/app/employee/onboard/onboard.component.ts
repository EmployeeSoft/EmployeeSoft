import { Component, OnInit } from '@angular/core';
import {Validators} from '@angular/forms';
import { FormBuilder, FormGroup, FormControl, FormArray } from '@angular/forms';
import {UploadService} from '../_services/upload.service';

@Component({
  selector: 'app-onboard',
  templateUrl: './onboard.component.html',
  styleUrls: ['./onboard.component.css']
})
export class OnboardComponent implements OnInit {
  public formData: any;
  public genderOp: ['Male', 'Female', 'Do not want to answer'];
  public visaOp: ['H1-B', 'L2', 'F1(CPT/OPT)', 'H4'];
  public citizenOrNot: boolean;
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.formData = this.fb.group({
      firstName: ['', Validators.compose([Validators.required])],
      lastName: ['', Validators.compose([Validators.required])],
      midName: [''],
      preferName: [''],
      avatar: [''],
      address: ['', Validators.compose([Validators.required])],
      car: [''],
      ssn: ['', Validators.compose([Validators.required])],
      dob: ['', Validators.compose([Validators.required])],
      gender: [''],
      citizenOrNot: ['', Validators.compose([Validators.required])],
      citizenType: [''],
      visa: this.fb.array([]),
      // startDate: [''],
      // endDate: [''],
      // workAuthDoc: [''],
      hasDriverLicence: ['', Validators.compose([Validators.required])],
      driverLicence: [''],
      refFirstName: [''],
      refLastName: [''],
      refMidName: [''],
      refPhone: [''],
      refAddress: [''],
      refEmail: [''],
      refRelation: [''],
      emergencyContact: this.fb.array([
        this.fb.group({
          ecFirstName: [''],
          ecLastName: [''],
          ecMidName: [''],
          ecPhone: [''],
          ecEmail: [''],
          ecRelation: ['']
        })
      ])
    });
  }
  get getAvatar() {
    return this.formData.get('avatar') as FormControl;
  }

  get getVisa(){
    return this.formData.get('visa') as FormArray;
  }

  addVisa() {
    this.citizenOrNot = false;
    const visa = this.getVisa;
    visa.clear();
    const content = this.fb.group({
      type: ['', Validators.required],
      length: ['', Validators.required],
    });
    visa.insert(0, content);
  }

  deleteVisa() {
    this.citizenOrNot = true;
    const visa = this.getVisa;
    visa.clear();
  }

  onSubmit(from: any) {
    console.log(from);
  }

  fileChange(event: any, c: FormControl) {
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      c.setValue(event.target.files[0], {emitModelToViewChange: false});
    }
  }
}