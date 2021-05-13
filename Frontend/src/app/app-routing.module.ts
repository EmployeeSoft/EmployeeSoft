import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './common/home';
import { AuthGuard } from './common/_helpers';
import { Role } from './common/_models';
import { HireComponent } from './hr/hire/hire.component';
import { EmployeeHomeComponent } from './employee/employee-home/employee-home.component';
import {OnboardComponent} from './employee/onboard/onboard.component';
import { RegisterComponent } from './common/account/register.component';
import { HrHomeComponent } from './hr/hr-home/hr-home.component';
import { PersonalInfoComponent } from './employee/personal-info/personal-info.component';

const accountModule = () => import('./common/account/account.module').then(x => x.AccountModule);
const usersModule = () => import('./common/users/users.module').then(x => x.UsersModule);

const routes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'hr/hire', component: HireComponent, canActivate: [AuthGuard], data: {roles: [Role.hr]}},
    { path: 'hr/home', component: HrHomeComponent, canActivate: [AuthGuard], data: {roles: [Role.hr]}},
    { path: 'employee/home', component: EmployeeHomeComponent, canActivate: [AuthGuard], data: {roles: [Role.hr, Role.employee]}},
    { path: 'users', loadChildren: usersModule },
    { path: 'account', loadChildren: accountModule },
    { path: 'register', component: RegisterComponent },
    { path: 'onboard', component: OnboardComponent},
    { path: 'test', component: PersonalInfoComponent},

    // otherwise redirect to home
    // { path: '**', redirectTo: '' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
