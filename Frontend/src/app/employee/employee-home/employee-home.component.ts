import {Component, NgModule, OnInit} from '@angular/core';
import {NavbarComponent} from '../navbar/navbar.component';

@Component({
  selector: 'app-employee-home',
  templateUrl: './employee-home.component.html',
})

export class EmployeeHomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    // call the GET request here
    // Get all the information of an employee
  }

}
