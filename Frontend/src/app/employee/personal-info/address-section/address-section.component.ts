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
  addressSection: any;
  addressSecEdit: boolean;
  primaryAddress: any;
  secondAddress: any;
  constructor(
    private fb: FormBuilder,
    private addressService: UserInfoAddressService,
    private alertService: AlertService,
  ) { }

  ngOnInit(): void {
    this.primaryAddress = '1221 K Eve Blvd';
    this.secondAddress = 'Apt 4';
    this.addressSecEdit = false;
    this.formData = this.fb.group({
      id: [2],
      primaryAddress: [this.primaryAddress],
      secondAddress: [this.secondAddress],
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
