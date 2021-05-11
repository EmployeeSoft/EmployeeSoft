import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

// used to create fake backend
import { ErrorInterceptor, fakeBackendProvider, JwtInterceptor } from './common/_helpers';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HrMainComponent } from './hr/hr-main/hr-main.component';
import { AlertComponent } from './common/_components';
import { HomeComponent } from './common/home';
import { HireComponent } from './hr/hire/hire.component';
import { RegisterNewHireComponent } from './hr/hire/register-new-hire/register-new-hire.component';
import { EmployeeHomeComponent} from './employee/employee-home/employee-home.component';
import { NavbarComponent } from './employee/navbar/navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    HrMainComponent,
    AlertComponent,
    HomeComponent,
    HireComponent,
    RegisterNewHireComponent,
    EmployeeHomeComponent,
    NavbarComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },

    // provider used to create fake backend
    fakeBackendProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
