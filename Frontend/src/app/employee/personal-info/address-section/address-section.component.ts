import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {Address} from '../../_models/address';
import {UserInfoAddressService} from '../../_services/user-info-address.service';

@Component({
  selector: 'app-address-section',
  templateUrl: './address-section.component.html',
  styleUrls: ['./address-section.component.css']
})
export class AddressSectionComponent implements OnInit {
  formData: any;
  addressSection: any;
  addressSecEdit: boolean;
  primaryAddress: any;
  secondAddress: any;
  addressService: UserInfoAddressService;
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.primaryAddress = '1221 K Eve Blvd';
    this.secondAddress = 'Apt 4';
    this.addressSecEdit = false;
    this.formData = this.fb.group({
      primaryAddress: [this.primaryAddress],
      secondAddress: [this.secondAddress],
    });
  }
  startEdit() {
    this.addressSecEdit = true;
  }

  endEdit() {
    this.addressSecEdit = false;
  }

  onSubmit() {
    const address: Address;
    address.primaryAddress = this.formData.controls.primaryAddress.value;
    address.secondAddress = this.formData.controls.secondAddress.value;
    this.addressService.update(address);
  }
}