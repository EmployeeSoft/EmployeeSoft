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
    this.employmentSection = ['F1', '2020/08/20', '2023/09/21', '2021/03/09', '2022/09/31', 'Software Developer'];
    this.SecEdit = false;
    this.formData = this.fb.group({
      id: [2],
      workAuth: [this.employmentSection[0]],
      waStart: [this.employmentSection[1]],
      waEnd: [this.employmentSection[2]],
      employStart: [this.employmentSection[3]],
      employEnd: [this.employmentSection[4]],
      title: [this.employmentSection[5]]
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
