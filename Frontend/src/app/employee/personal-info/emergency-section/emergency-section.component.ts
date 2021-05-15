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
        name: 'Ding Wang',
        phone: '2222939329',
        address: '2222 fhue Blvd'
      },
      {
        name: 'Uno kk',
        phone: '2394949192',
        address: '3333 djdd Blvd'
      }
    ];
    const arr = [];

    for (let i = 0; i < this.emergencySection.length; i++){
      arr.push(this.BuildFormDynamic(this.emergencySection[i]));
    }

    this.formData = this.fb.group({
      emergency: this.fb.array(arr)
    });
  }
  get getContact(){
    return this.formData.get('emergency').controls;
  }

  BuildFormDynamic(contact: any): FormGroup{
    return this.fb.group({
      name: [contact.name],
      phone: [contact.phone],
      address: [contact.address]
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
