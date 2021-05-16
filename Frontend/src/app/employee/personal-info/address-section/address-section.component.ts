import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {Address} from '../../_models/address';
import {UserInfoAddressService} from '../../_services/user-info/user-info-address.service';
import {first} from 'rxjs/operators';
import {AlertService} from '../../../common/_services';

@Component({
  selector: 'app-address-section',
  templateUrl: './address-section.component.html',
  styleUrls: ['./address-section.component.css']
})
export class AddressSectionComponent implements OnInit {
  formData: any;
  addressData: any;
  addressSecEdit: boolean;
  primaryAddress: any;
  secondAddress: any;
  constructor(
    private fb: FormBuilder,
    private addressService: UserInfoAddressService,
    private alertService: AlertService,
  ) { }

  ngOnInit(): void {
    this.addressData = ['1221 K Eve Blvd', 'Apt 4', 'LA',
    '90034', 'California', 'CA'];
    this.addressSecEdit = false;
    this.formData = this.fb.group({
      person_id: [2],
      address_line_1: [this.addressData[0]],
      address_line_2: [this.addressData[1]],
      city: [this.addressData[2]],
      zipcode: [this.addressData[3]],
      state_name: [this.addressData[4]],
      state_abbr: [this.addressData[5]]
    });
  }
  startEdit() {
    this.addressSecEdit = true;
  }

  endEdit() {
    this.addressSecEdit = false;
    this.alertService.warn('Are you sure to discard all changes?')
  }

  onSubmit() {
    this.addressSecEdit = false;
    this.addressService.update(this.formData.value)
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
