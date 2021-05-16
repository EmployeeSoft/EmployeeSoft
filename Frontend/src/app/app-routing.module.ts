import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './common/home';
import { AuthGuard } from './common/_helpers';
import { Role } from './common/_models/role';
import { HireComponent } from './hr/hire/hire.component';
import { EmployeeHomeComponent } from './employee/employee-home/employee-home.component';
import {OnboardComponent} from './employee/onboard/onboard.component';
import { RegisterComponent } from './common/account/register.component';
import { HrHomeComponent } from './hr/hr-home/hr-home.component';
import { EmployeeVisaStatusManagementComponent } from './employee/employee-visa-status-management/employee-visa-status-management.component';
import { PersonalInfoComponent } from './employee/personal-info/personal-info.component';
import { EmployeeProfileComponent } from './hr/employee-profile/employee-profile.component';
import { LoginComponent } from './common/account/login.component';

const accountModule = () => import('./common/account/account.module').then(x => x.AccountModule);
const usersModule = () => import('./common/users/users.module').then(x => x.UsersModule);

const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'hr/hire', component: HireComponent, canActivate: [AuthGuard], data: {roles: [Role.hr]}},
    { path: 'hr/home', component: HrHomeComponent, canActivate: [AuthGuard], data: {roles: [Role.hr]}},
    { path: 'employee/home', component: EmployeeHomeComponent, canActivate: [AuthGuard], data: {roles: [Role.hr, Role.employee]}},
    { path: 'employee/visa', component: EmployeeVisaStatusManagementComponent, canActivate: [AuthGuard], data: {roles: [Role.employee]}},
    { path: 'employee/personal', component: PersonalInfoComponent},
    { path: 'users', loadChildren: usersModule },
    { path: 'account', loadChildren: accountModule },
    { path: 'register', component: RegisterComponent },
    { path: 'onboard', component: OnboardComponent},
    { path: 'test', component: PersonalInfoComponent},
    { path: 'hr/employee-profile', component: EmployeeProfileComponent},

    // otherwise redirect to home
    // { path: '**', redirectTo: '' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
