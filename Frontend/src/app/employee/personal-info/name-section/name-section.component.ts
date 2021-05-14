import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl} from '@angular/forms';

@Component({
  selector: 'app-name-section',
  templateUrl: './name-section.component.html',
  styleUrls: ['./name-section.component.css']
})
export class NameSectionComponent implements OnInit {
  formData: any;
  nameSection: any;
  nameSecEdit: boolean;
  genderOp: any;
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.nameSection = ['Ding Wang', 'Ding Wang', '09/21/1995', '18', 'Male', '*0000'];
    this.genderOp = ['Male', 'Female', 'Do not want to answer'];
    this.nameSecEdit = false;
    this.formData = this.fb.group({
      fullName: [this.nameSection[0]],
      preferName: [this.nameSection[1]],
      avatar: [''],
      dob: [this.nameSection[2]],
      age: [this.nameSection[3]],
      gender: [this.nameSection[4]],
      ssn: [this.nameSection[5]]
    });
  }
  get getAvatar() {
    return this.formData.get('avatar') as FormControl;
  }

  startEdit() {
    this.nameSecEdit = true;
  }

  endEdit() {
    this.nameSecEdit = false;
  }

  fileChange(event: any, c: FormControl) {
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      c.setValue(event.target.files[0], {emitModelToViewChange: false});
    }
  }
}
