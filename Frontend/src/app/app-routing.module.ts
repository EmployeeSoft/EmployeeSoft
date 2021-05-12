import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './common/home';
import { AuthGuard } from './common/_helpers';
import { HireComponent } from './hr/hire/hire.component';
import { EmployeeHomeComponent } from './employee/employee-home/employee-home.component';
import { RegisterComponent } from './common/account/register.component';

const accountModule = () => import('./common/account/account.module').then(x => x.AccountModule);
const usersModule = () => import('./common/users/users.module').then(x => x.UsersModule);

const routes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'users', loadChildren: usersModule, canActivate: [AuthGuard] },
    { path: 'account', loadChildren: accountModule },
    { path: 'hr/hire', component: HireComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'home', component: EmployeeHomeComponent},

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
