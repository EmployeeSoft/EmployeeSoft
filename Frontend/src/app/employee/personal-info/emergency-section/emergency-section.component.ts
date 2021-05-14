import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, FormArray } from '@angular/forms';
import { AlertService } from '../../../common/_services';

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
  private alertService: AlertService;

  constructor(private fb: FormBuilder) {
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

  endEdit() {
    this.SecEdit = false;
  }

  cancelEdit() {
    this.alertService.warn('Are you sure to cancel all updates?');
    this.SecEdit = false;
  }
}
