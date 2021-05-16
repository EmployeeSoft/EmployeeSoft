import { Component, OnInit } from '@angular/core';
import { EmployeeProfileService } from '../_services/employee-profile.service';

@Component({
  selector: 'app-employee-profile',
  templateUrl: './employee-profile.component.html',
  styleUrls: ['./employee-profile.component.css']
})
export class EmployeeProfileComponent implements OnInit {

  searchText: any;
  data: any;
  employees: any[];
  constructor(private employeeProfileService: EmployeeProfileService) { }

  ngOnInit(): void {

    const user = JSON.parse(localStorage.getItem('user')!);
    this.employeeProfileService.getAll(user.role)
      .subscribe(data=> {
        console.log(data);
        this.data = data;
        console.log(this.data.employees[0].personDomain);
        this.employees = this.data.employees;
      }, error => console.log(error));
  }

}
