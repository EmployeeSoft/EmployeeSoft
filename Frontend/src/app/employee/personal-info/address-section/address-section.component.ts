import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';

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
}
