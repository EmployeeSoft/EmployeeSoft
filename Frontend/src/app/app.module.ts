import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

// used to create fake backend
import { ErrorInterceptor, fakeBackendProvider, JwtInterceptor } from './common/_helpers';

// search module
import { Ng2SearchPipeModule } from 'ng2-search-filter';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HrMainComponent } from './hr/hr-main/hr-main.component';
import { AlertComponent } from './common/_components';
import { HomeComponent } from './common/home';
import { HireComponent } from './hr/hire/hire.component';
import { RegisterNewHireComponent } from './hr/hire/register-new-hire/register-new-hire.component';
import { EmployeeHomeComponent} from './employee/employee-home/employee-home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { OnboardComponent } from './employee/onboard/onboard.component';
import { HrHomeComponent } from './hr/hr-home/hr-home.component';
// tslint:disable-next-line:max-line-length
import { EmployeeVisaStatusManagementComponent } from './employee/employee-visa-status-management/employee-visa-status-management.component';
import { PersonalInfoComponent } from './employee/personal-info/personal-info.component';
import { NameSectionComponent } from './employee/personal-info/name-section/name-section.component';
import { AddressSectionComponent } from './employee/personal-info/address-section/address-section.component';
import { ContactSectionComponent } from './employee/personal-info/contact-section/contact-section.component';
import { EmploymentSectionComponent } from './employee/personal-info/employment-section/employment-section.component';
import { EmergencySectionComponent } from './employee/personal-info/emergency-section/emergency-section.component';
import { DocumentSectionComponent } from './employee/personal-info/document-section/document-section.component';
import { EmployeeProfileComponent } from './hr/employee-profile/employee-profile.component';

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
    OnboardComponent,
    HrHomeComponent,
    EmployeeVisaStatusManagementComponent,
    PersonalInfoComponent,
    NameSectionComponent,
    AddressSectionComponent,
    ContactSectionComponent,
    EmploymentSectionComponent,
    EmergencySectionComponent,
    DocumentSectionComponent,
    EmployeeProfileComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng2SearchPipeModule,
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
