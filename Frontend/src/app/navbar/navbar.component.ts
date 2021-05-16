import {Component, NgModule, OnInit} from '@angular/core';
import { AccountService } from '../common/_services'

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
})

export class NavbarComponent implements OnInit {

  constructor(private accountService: AccountService) { }

  user: any;
  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem("user")!);
    console.log(this.user);
  }

  logout() {
    this.accountService.logout();
  }

}
