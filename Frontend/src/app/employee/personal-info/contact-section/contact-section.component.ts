import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-contact-section',
  templateUrl: './contact-section.component.html',
  styleUrls: ['./contact-section.component.css']
})
export class ContactSectionComponent implements OnInit {
  formData: any;
  contactSection: any;
  SecEdit: boolean;
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.contactSection = ['dingwang0921@gmail.com', 'dingwang@dusifb.com', '2132932273', '2137263625'];
    this.SecEdit = false;
    this.formData = this.fb.group({
      email: [this.contactSection[0]],
      workEmail: [this.contactSection[1]],
      phone: [this.contactSection[2]],
      workPhone: [this.contactSection[3]],
    });
  }
  startEdit() {
    this.SecEdit = true;
  }

  endEdit() {
    this.SecEdit = false;
  }
}
