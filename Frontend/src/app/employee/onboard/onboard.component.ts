import { Component, OnInit } from '@angular/core';
import {Validators} from '@angular/forms';
import { FormBuilder, FormGroup, FormControl, FormArray } from '@angular/forms';

@Component({
  selector: 'app-onboard',
  templateUrl: './onboard.component.html'
})
export class OnboardComponent implements OnInit {
  public formData: any;
  public genderOp: any;
  public visaOp: any;
  public citizenOrNot: boolean;
  public hasLicence: boolean;

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
    this.genderOp = ['Male', 'Female', 'Do not want to answer'];
    this.visaOp = ['H1-B', 'L2', 'F1(CPT/OPT)', 'H4', 'others'];
  }

  get getAvatar() {
    return this.formData.get('avatar') as FormControl;
  }

  get getVisa(){
    return this.formData.get('visa').controls;
  }

  get getWorkAuthDoc() {
    return this.formData.get('visa')?.get('workAuthDoc') as FormControl;
  }

  get getEC() {
    return this.formData.get('emergencyContact').controls;
  }

  addVisa() {
    this.citizenOrNot = false;
    const visa = this.getVisa;
    visa.clear();
    const visaContent = this.fb.group({
      workAuthType: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      workAuthDoc: ['', Validators.required],
    });
    visa.insert(0, visaContent);
  }

  deleteVisa() {
    this.citizenOrNot = true;
    const visa = this.getVisa;
    visa.clear();
  }

  addEC() {
    const temp = this.getEC;
    const list = this.fb.group({
      emergencyFirstName: [''],
      emergencyLastName: [''],
      emergencyMidName: [''],
      emergencyPhone: [''],
      emergencyEmail: [''],
      emergencyRelation: [''],
    });
    temp.insert(0, list);
  }

  deleteEC() {
    if (this.getEC.length > 1) {
      const temp = this.getEC;
      temp.removeAt(temp.length - 1);
    }
  }

  onSubmit(form: any) {
    console.log(form);
  }

  fileChange(event: any, c: FormControl) {
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      c.setValue(event.target.files[0], {emitModelToViewChange: false});
    }
  }
}
