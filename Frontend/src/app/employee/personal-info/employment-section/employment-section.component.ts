import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-employment-section',
  templateUrl: './employment-section.component.html',
  styleUrls: ['./employment-section.component.css']
})
export class EmploymentSectionComponent implements OnInit {
  formData: any;
  employmentSection: any;
  SecEdit: boolean;
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.employmentSection = ['F1', '2020/08/20', '2023/09/21', '2021/03/09', '2022/09/31', 'Software Developer'];
    this.SecEdit = false;
    this.formData = this.fb.group({
      workAuth: [this.employmentSection[0]],
      waStart: [this.employmentSection[1]],
      waEnd: [this.employmentSection[2]],
      employStart: [this.employmentSection[3]],
      employEnd: [this.employmentSection[4]],
      title: [this.employmentSection[6]]
    });
  }
  startEdit() {
    this.SecEdit = true;
  }

  endEdit() {
    this.SecEdit = false;
  }
}
