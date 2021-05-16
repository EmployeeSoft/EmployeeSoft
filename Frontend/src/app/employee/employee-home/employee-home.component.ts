import {Component, NgModule, OnInit} from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { NavbarComponent } from '../../navbar/navbar.component';
import { EmployeeHomeService } from '../_services/employee-home.service';
import { AccountService } from '../../common/_services/account.service'
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-employee-home',
  templateUrl: './employee-home.component.html',
})

export class EmployeeHomeComponent implements OnInit {

  user: any;
  data: any;
  constructor(private http: HttpClient, private employeeHomeService: EmployeeHomeService, private accountService: AccountService) { }

  ngOnInit(): void {
    // call the GET request here
    // Get all the information of an employee
    console.log("employee home component initialized")
    const jwt = localStorage.getItem('jwt');
    const helper = new JwtHelperService();
    const decodedJwt = helper.decodeToken(jwt!);
    const userId = decodedJwt.sub.toString();

    const user = JSON.parse(localStorage.getItem('user')!);
    this.employeeHomeService.getByRoleAndId(user.role, userId).subscribe((data)=> {
      console.log(data);
      localStorage.setItem("user-info", JSON.stringify(data));
      this.data = data;
    });
  }

}
