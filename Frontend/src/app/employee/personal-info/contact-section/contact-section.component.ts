import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {AlertService} from '../../../common/_services';
import {UserInfoContactService} from '../../_services/user-info/user-info-contact.service';
import {Contact} from '../../_models/contact';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-contact-section',
  templateUrl: './contact-section.component.html',
  styleUrls: ['./contact-section.component.css']
})
export class ContactSectionComponent implements OnInit {
  formData: any;
  contactSection: any;
  SecEdit: boolean;
  constructor(
    private fb: FormBuilder,
    private alertService: AlertService,
    private contactService: UserInfoContactService,
  ) { }

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
    this.alertService.warn('Are you sure to discard all changes?');
  }

  onSubmit(){
    this.SecEdit = false;
    this.contactService.update(this.formData.value as Contact)
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
