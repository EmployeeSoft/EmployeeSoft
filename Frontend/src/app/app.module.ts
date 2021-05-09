import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeMainComponent } from './employee/employee-main/employee-main.component';
import { HrMainComponent } from './hr/hr-main/hr-main.component';

@NgModule({
  declarations: [
    AppComponent,
    EmployeeMainComponent,
    HrMainComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
