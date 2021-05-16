import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {first} from 'rxjs/operators';
import {UserInfoEmploymentService} from '../../_services/user-info/user-info-employment.service';
import {AlertService} from '../../../common/_services';

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
    private employmentService: UserInfoEmploymentService,
    private alertService: AlertService,
  ) { }

  ngOnInit(): void {
    this.employmentSection = ['Software Developer', '2020/08/20',
      '2023/09/21', 'aaa_bb_ccc', '2021/03/09', '2022/09/31', '382912832', '2023/11/23'];
    this.SecEdit = false;
    this.formData = this.fb.group({
      person_id: [2],
      title: [this.employmentSection[0]],
      manager_id: [3],
      start_date: [this.employmentSection[1]],
      end_date: [this.employmentSection[2]],
      avatar: [],
      car: [this.employmentSection[3]],
      visa_status_id: [],
      visa_start_date: [this.employmentSection[4]],
      visa_end_date: [this.employmentSection[5]],
      driver_license: [this.employmentSection[6]],
      driver_license_exp_date: [this.employmentSection[7]]
    });
  }
  startEdit() {
    this.SecEdit = true;
  }

  endEdit() {
    this.alertService.warn('Are you sure to discard all changes?');
    this.SecEdit = false;
  }

  onSubmit(){
    this.SecEdit = false;
    this.employmentService.update(this.formData.value)
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
