import { Component, OnInit } from '@angular/core';
import { EmployeeProfileService } from '../_services/employee-profile.service';

@Component({
  selector: 'app-employee-profile',
  templateUrl: './employee-profile.component.html',
  styleUrls: ['./employee-profile.component.css']
})
export class EmployeeProfileComponent implements OnInit {

  searchText: any;
  employees: any;
  constructor(private employeeProfileService: EmployeeProfileService) { }

  ngOnInit(): void {
    this.employeeProfileService.getAll()
      .subscribe(data=> {
        console.log(data);
        this.employees = data;

      }, error => console.log(error));
  }

}
