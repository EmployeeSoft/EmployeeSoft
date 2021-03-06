import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {first} from 'rxjs/operators';
import {AlertService} from '../../../common/_services';
import {UserInfoNameService} from '../../_services/user-info/user-info-name.service';
import {JwtHelperService} from '@auth0/angular-jwt';

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
    private addressService: UserInfoNameService,
    private alertService: AlertService,
  ) { }

  ngOnInit(): void {
    const userInfo = JSON.parse(localStorage.getItem('user-info')!);
    const personId = userInfo.personId;

    this.addressSecEdit = false;
    this.formData = this.fb.group({
      id: [userInfo.address[0].id],
      personId: [personId],
      addressLine1: [userInfo.address[0].addressLine1],
      addressLine2: [userInfo.address[0].addressLine2],
      city: [userInfo.address[0].city],
      zipcode: [userInfo.address[0].zipcode],
      stateName: [userInfo.address[0].stateName],
      stateAbbr: [userInfo.address[0].stateAbbr]
    });
  }
  startEdit() {
    this.addressSecEdit = true;
  }

  endEdit() {
    this.addressSecEdit = false;

    const userInfo = JSON.parse(localStorage.getItem('user-info')!);
    this.formData.controls['addressLine1'].value = userInfo.address[0].addressLine1;
    this.formData.controls['addressLine2'].value = userInfo.address[0].addressLine2;
    this.formData.controls['city'].value = userInfo.address[0].city;
    this.formData.controls['zipcode'].value = userInfo.address[0].zipcode;
    this.formData.controls['stateName'].value = userInfo.address[0].stateName;
    this.formData.controls['stateAbbr'].value = userInfo.address[0].stateAbbr;

    this.alertService.warn('Are you sure to discard all changes?');
  }

  onSubmit() {
    const userInfo = JSON.parse(localStorage.getItem('user-info')!);
    userInfo.address[0].addressLine1 = this.formData.controls['addressLine1'].value;
    userInfo.address[0].addressLine2 = this.formData.controls['addressLine2'].value;
    userInfo.address[0].city = this.formData.controls['city'].value;
    userInfo.address[0].zipcode = this.formData.controls['zipcode'].value;
    userInfo.address[0].stateName = this.formData.controls['stateName'].value;
    userInfo.address[0].stateAbbr = this.formData.controls['stateAbbr'].value;
    localStorage.setItem('user-info', JSON.stringify(userInfo));

    this.addressSecEdit = false;
    this.addressService.updateAddress(this.formData.value)
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
