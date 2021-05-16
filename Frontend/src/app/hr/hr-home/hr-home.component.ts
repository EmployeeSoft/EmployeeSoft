import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-hr-home',
  templateUrl: './hr-home.component.html',
  styleUrls: ['./hr-home.component.css']
})
export class HrHomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    const jwt = localStorage.getItem('jwt');
    const helper = new JwtHelperService();
    const decodedJwt = helper.decodeToken(jwt!);
    const userId = decodedJwt.sub.toString();

    const user = JSON.parse(localStorage.getItem('user')!);
  }

}
