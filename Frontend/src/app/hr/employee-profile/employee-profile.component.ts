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
  employees: any[] = [];
  constructor(private employeeProfileService: EmployeeProfileService) { }

  ngOnInit(): void {

    const user = JSON.parse(localStorage.getItem('user')!);
    this.employeeProfileService.getAll(user.role)
      .subscribe(data=> {
        this.data = data;
        for (let i = 0; i < this.data.employees.length; i++) {
          let name = this.data.employees[i].personDomain.firstName + " " + this.data.employees[i].personDomain.middleName + " " + this.data.employees[i].personDomain.lastName;
          let ssn = this.data.employees[i].personDomain.ssn;
          let startDate = this.data.employees[i].startDate;
          let visaType = this.data.employees[i].visaType;
          let obj = {"name": name, "ssn": ssn, "startDate": startDate, "visaType": visaType}
          this.employees.push(obj);
        }
      }, error => console.log(error));
  }

}
