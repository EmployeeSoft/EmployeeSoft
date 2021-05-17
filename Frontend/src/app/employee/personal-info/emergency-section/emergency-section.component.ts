import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, FormArray } from '@angular/forms';
import { AlertService } from '../../../common/_services';
import {UserInfoEmergencyService} from '../../_services/user-info/user-info-emergency.service';
import {Address} from '../../_models/address';
import {first} from 'rxjs/operators';
import {Contact} from '../../../common/_models/contact';
import {UserInfoNameService} from '../../_services/user-info/user-info-name.service';

@Component({
  selector: 'app-emergency-section',
  templateUrl: './emergency-section.component.html',
  styleUrls: ['./emergency-section.component.css']
})
export class EmergencySectionComponent implements OnInit {
  formData: any;
  emergencySection: Contact[];
  SecEdit: boolean;
  emergency: any;
  controls: any;

  constructor(
    private fb: FormBuilder,
    private alertService: AlertService,
    private emergencyService: UserInfoNameService,
  ) {
  }

  ngOnInit(): void {
    const userInfo = JSON.parse(localStorage.getItem('user-info')!);
    const personId = userInfo.personId;

    this.SecEdit = false;
    console.log(userInfo.contracts[0].fullName);

    this.emergencySection = [];
    for(let i = 0; i < userInfo.contracts.length; i++){
      this.emergencySection.push(new Contact(userInfo.contracts[i].id,
        userInfo.contracts[i].fullName, userInfo.contracts[i].phone,
        userInfo.contracts[i].relationship, userInfo.contracts[i].address));
    }

    const arr = [];

    for (let i = 0; i < this.emergencySection.length; i++){
      arr.push(this.BuildFormDynamic(this.emergencySection[i]));
    }

    this.formData = this.fb.group({
      personId: [personId],
      emergency: this.fb.array(arr)
    });

    console.log(this.formData.value);

  }
  get getContact(){
    return this.formData.get('emergency').controls;
  }

  BuildFormDynamic(contact: any): FormGroup{
    return this.fb.group({
      id: [contact.id],
      fullName: [contact.fullName],
      phone: [contact.phone],
      relationship: [contact.relationship],
      // title: [contact.title],
      address: [contact.address],
    });
  }

  startEdit() {
    this.SecEdit = true;
  }

  cancelEdit() {
    alert('Are you sure to cancel all updates?');
    this.SecEdit = false;
  }

  onSubmit() {
    this.SecEdit = false;
    console.log(this.formData.value.emergency);
    this.emergencyService.updateContact(this.formData.value.personId, this.formData.value.emergency)
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
