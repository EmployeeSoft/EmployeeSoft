import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

// used to create fake backend
import { ErrorInterceptor, fakeBackendProvider, JwtInterceptor } from './common/_helpers';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeMainComponent } from './employee/employee-main/employee-main.component';
import { HrMainComponent } from './hr/hr-main/hr-main.component';
import { AlertComponent } from './common/_components';
import { HomeComponent } from './common/home';

@NgModule({
  declarations: [
    AppComponent,
    EmployeeMainComponent,
    HrMainComponent,
    AlertComponent,
    HomeComponent
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
