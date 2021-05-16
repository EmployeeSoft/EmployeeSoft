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
    this.SecEdit = false;
    this.emergencySection = [
      {
        full_name: 'Ding Wang',
        phone: '2392931921',
        relationship: 'Parents',
        title: 'SDE',
        address: '2332 I fhud blvd',
      },
      {
        full_name: 'Alice Dao',
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
      person_id: [2],
      emergency: this.fb.array(arr)
    });
  }
  get getContact(){
    return this.formData.get('emergency').controls;
  }

  BuildFormDynamic(contact: any): FormGroup{
    return this.fb.group({
      full_name: [contact.full_name],
      phone: [contact.phone],
      relationship: [contact.relationship],
      title: [contact.title],
      address: [contact.address],
      is_reference: [false],
      is_emergency: [true],
      is_landlord: [true],
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
