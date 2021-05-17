import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HrHomeService } from '../_services/hr-home.service';

@Component({
  selector: 'app-hr-home',
  templateUrl: './hr-home.component.html',
  styleUrls: ['./hr-home.component.css']
})
export class HrHomeComponent implements OnInit {

  employees: any;
  constructor(private hrHomeService: HrHomeService) { }

  ngOnInit(): void {
    const jwt = localStorage.getItem('jwt');
    const helper = new JwtHelperService();
    const decodedJwt = helper.decodeToken(jwt!);
    const userId = decodedJwt.sub.toString();

    const user = JSON.parse(localStorage.getItem('user')!);
    console.log(user.role);

    this.hrHomeService.getAllEmployeeWithStatus(user.role)
      .subscribe((data: any) => {
        console.log("data from backend")
        this.employees = data.employees;
      })
  }

}
