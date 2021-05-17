import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl} from '@angular/forms';
import {AlertService} from '../../../common/_services';
import {UserInfoNameService} from '../../_services/user-info/user-info-name.service';
import {first} from 'rxjs/operators';

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
  constructor(
    private fb: FormBuilder,
    private alertService: AlertService,
    private nameService: UserInfoNameService,
    ) { }

  ngOnInit(): void {
    const userInfo = JSON.parse(localStorage.getItem('user-info')!);
    const personId = userInfo.personId;

    this.nameSection = [userInfo.preferName, userInfo.dob,
      userInfo.gender, userInfo.ssn];
    this.genderOp = ['Male', 'Female', 'Do not want to answer'];
    this.nameSecEdit = false;
    this.formData = this.fb.group({
      personId: [personId],
      preferName: [userInfo.preferName],
      avatar: [''],
      dob: [userInfo.dob],
      gender: [userInfo.gender],
      ssn: [userInfo.ssn]
    });
  }
  get getAvatar() {
    return this.formData.get('avatar') as FormControl;
  }

  startEdit() {
    this.nameSecEdit = true;
  }

  endEdit() {
    this.alertService.warn('Are you sure to discard all changes?');

    const userInfo = JSON.parse(localStorage.getItem('user-info')!);
    this.formData.controls['preferName'].value = userInfo.userInfo.preferName;
    this.formData.controls['dob'].value = userInfo.dob;
    this.formData.controls['gender'].value = userInfo.gender;
    this.formData.controls['ssn'].value = userInfo.ssn;

    this.nameSecEdit = false;
  }

  onSubmit(){
    this.nameSecEdit = false;

    const userInfo = JSON.parse(localStorage.getItem('user-info')!);
    userInfo.userInfo.preferName = this.formData.controls['preferName'].value;
    userInfo.dob = this.formData.controls['dob'].value;
    userInfo.gender = this.formData.controls['gender'].value;
    userInfo.ssn = this.formData.controls['ssn'].value;
    localStorage.setItem('user-info', JSON.stringify(userInfo));

    this.nameService.updateName(this.formData.value)
      .pipe(first())
      .subscribe({
        next: (data) => {
        },
        error: error => {
          this.alertService.error(error);
        }
      });
  }

  fileChange(event: any, c: FormControl) {
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      c.setValue(event.target.files[0], {emitModelToViewChange: false});
    }
  }
}
