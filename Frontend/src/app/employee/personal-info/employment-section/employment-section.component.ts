import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {first} from 'rxjs/operators';
import {UserInfoEmploymentService} from '../../_services/user-info/user-info-employment.service';
import {AlertService} from '../../../common/_services';
import {UserInfoNameService} from '../../_services/user-info/user-info-name.service';

@Component({
  selector: 'app-employment-section',
  templateUrl: './employment-section.component.html',
  styleUrls: ['./employment-section.component.css']
})
export class EmploymentSectionComponent implements OnInit {
  formData: any;
  employmentSection: any;
  SecEdit: boolean;
  constructor(
    private fb: FormBuilder,
    private employmentService: UserInfoNameService,
    private alertService: AlertService,
  ) { }

  ngOnInit(): void {
    const userInfo = JSON.parse(localStorage.getItem('user-info')!);
    const personId = userInfo.personId;

    this.SecEdit = false;
    this.formData = this.fb.group({
      personId: [personId],
      title: [userInfo.title],
      startDate: [userInfo.employeeStartDate],
      endDate: [userInfo.employeeEndDate],
      avatar: [],
      car: [userInfo.car],
      visaStartDate: [userInfo.visaStartDate],
      visaEndDate: [userInfo.visaEndDate],
      driverLicense: [userInfo.driverLicense],
      driverLicenseExpDate: [userInfo.driverLicenseExpDate]
    });
  }
  startEdit() {
    this.SecEdit = true;
  }

  endEdit() {
    this.alertService.warn('Are you sure to discard all changes?');

    const userInfo = JSON.parse(localStorage.getItem('user-info')!);
    this.formData.controls['title'].value = userInfo.title;
    this.formData.controls['startDate'].value = userInfo.startDate;
    this.formData.controls['endDate'].value = userInfo.endDate;
    this.formData.controls['car'].value = userInfo.car;
    this.formData.controls['visaStartDate'].value = userInfo.visaStartDate;
    this.formData.controls['visaEndDate'].value = userInfo.visaEndDate;
    this.formData.controls['driverLicense'].value = userInfo.driverLicense;
    this.formData.controls['driverLicenseExpDate'].value = userInfo.driverLicenseExpDate;

    this.SecEdit = false;
  }

  onSubmit(){
    this.SecEdit = false;

    const userInfo = JSON.parse(localStorage.getItem('user-info')!);
    userInfo.title = this.formData.controls['title'].value;
    userInfo.startDate = this.formData.controls['startDate'].value;
    userInfo.endDate = this.formData.controls['endDate'].value;
    userInfo.car = this.formData.controls['car'].value;
    userInfo.visaStartDate = this.formData.controls['visaStartDate'].value;
    userInfo.visaEndDate = this.formData.controls['visaEndDate'].value;
    userInfo.driverLicense = this.formData.controls['driverLicense'].value;
    userInfo.driverLicenseExpDate = this.formData.controls['driverLicenseExpDate'].value;
    localStorage.setItem('user-info', JSON.stringify(userInfo));

    this.employmentService.updateEmployment(this.formData.value)
      .pipe(first())
      .subscribe({
        next: (data) => {
        },
        error: error => {
          this.alertService.error(error);
        }
      });
  }
}
