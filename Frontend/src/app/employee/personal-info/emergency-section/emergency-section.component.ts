import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, FormArray } from '@angular/forms';
import { AlertService } from '../../../common/_services';
import {UserInfoEmergencyService} from '../../_services/user-info/user-info-emergency.service';
import {Address} from '../../_models/address';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-emergency-section',
  templateUrl: './emergency-section.component.html',
  styleUrls: ['./emergency-section.component.css']
})
export class EmergencySectionComponent implements OnInit {
  formData: any;
  emergencySection: any;
  SecEdit: boolean;
  emergency: any;
  controls: any;

  constructor(
    private fb: FormBuilder,
    private alertService: AlertService,
    private emergencyService: UserInfoEmergencyService,
  ) {
  }

  ngOnInit(): void {
    const userInfo = JSON.parse(localStorage.getItem('user-info')!);
    const personId = userInfo.personId;

    this.SecEdit = false;
    this.emergencySection = [
      {
        fullName: 'Ding Wang',
        phone: '2392931921',
        relationship: 'Parents',
        title: 'SDE',
        address: '2332 I fhud blvd',
      },
      {
        fullName: 'Alice Dao',
        phone: '3382391293',
        relationship: 'Friends',
        title: 'SDE',
        address: '3994 I fhud blvd',
      }
    ];
    const arr = [];

    for (let i = 0; i < this.emergencySection.length; i++){
      arr.push(this.BuildFormDynamic(this.emergencySection[i]));
    }

    this.formData = this.fb.group({
      personId: [2],
      emergency: this.fb.array(arr)
    });
  }
  get getContact(){
    return this.formData.get('emergency').controls;
  }

  BuildFormDynamic(contact: any): FormGroup{
    return this.fb.group({
      fullName: [contact.full_name],
      phone: [contact.phone],
      relationship: [contact.relationship],
      title: [contact.title],
      address: [contact.address],
    });
  }

  startEdit() {
    this.SecEdit = true;
  }

  cancelEdit() {
    this.alertService.warn('Are you sure to cancel all updates?');
    this.SecEdit = false;
  }

  onSubmit() {
    this.SecEdit = false;
    this.emergencyService.update(this.formData.value)
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
