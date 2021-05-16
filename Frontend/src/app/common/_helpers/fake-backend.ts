import { Injectable } from '@angular/core';
import { HttpRequest, HttpResponse, HttpHandler, HttpEvent, HttpInterceptor, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { delay, materialize, dematerialize } from 'rxjs/operators';
import { stringify } from '@angular/compiler/src/util';
import { EmployeeVisa, User } from '../_models';

// array in local storage for registered users
const usersKey = 'angular-10-registration-login-example-users';
let users = JSON.parse(localStorage.getItem(usersKey)!) || [];

// - start: employee visa fake data
const employeeVisaKey = 'empVisaKey'
let optEadDaysLeft = 90;
let employeeVisaObject = {
  "hasOptReceipt": true,
  "hasUploadedOptEad": true,
  "optEadDaysLeft": optEadDaysLeft,
  "isOptEadLessThan100Days": optEadDaysLeft < 100,
  "hasUploadedFormI983": false,
  "hasFormI983HrSignedAndApproved": false,
  "hasUploadedFormI20": false,
  "hasUploadedOptStemReceipt": false,
  "hasUploadedOptStemEad": false
}
localStorage.setItem('empVisaKey', JSON.stringify(employeeVisaObject));
// - end: employee visa fake data

// - start: employee profile fake data
const employeeProfile = 'empProfile'
let employees = [
  { name: 'Clark', ssn: '123-876532', startDate: '3/15/2020', visaStatus: "F-1 Visa" },
  { name: 'Johnny' , ssn: '123-97641', startDate: '3/15/2020', visaStatus: "F-1 Visa"},
  { name: 'Elliot' , ssn: '123-23112', startDate: '3/15/2020', visaStatus: "F-1 Visa"},
  { name: 'Ding' , ssn: '123-231232', startDate: '3/15/2020', visaStatus: "F-1 Visa"}
];
localStorage.setItem('empProfile', JSON.stringify(employees));
// - end: employee profile fake data

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const { url, method, headers, body } = request;

        return handleRoute();

        function handleRoute() {
            switch (true) {
                case url.endsWith('/users/authenticate') && method === 'POST':
                    return authenticate();
                case url.endsWith('/users/register') && method === 'POST':
                    return register();
                case url.endsWith('/users') && method === 'GET':
                    return getUsers();
                case url.match(/\/users\/\d+$/) && method === 'GET':
                    return getUserById();
                case url.match(/\/users\/\d+$/) && method === 'PUT':
                    return updateUser();
                case url.match(/\/users\/\d+$/) && method === 'DELETE':
                    return deleteUser();
               // case url.match(/\/employee\/visa\//) && method === 'GET':
               //   return getEmployeeVisaById();
               // case url.match(/\/hr\/employee-profile\//) && method === 'GET':
                 // return getAllEmployee();
                default:
                    // pass through any requests not handled above
                    return next.handle(request);
            }
        }

        // route functions

        function authenticate() {
            const { username, password } = body;
            const user = users.find((x: any) => x.username === username && x.password === password);
            if (!user) return error('Username or password is incorrect');
            return ok({
                ...basicDetails(user),
                token: 'fake-jwt-token'
            })
        }

        function register() {
            const user = body

            if (users.find((x: any) => x.username === user.username)) {
                return error('Username "' + user.username + '" is already taken')
            }

            user.id = users.length ? Math.max(...users.map((x: any) => x.id)) + 1 : 1;
            users.push(user);
            localStorage.setItem(usersKey, JSON.stringify(users));
            return ok();
        }

        function getUsers() {
            if (!isLoggedIn()) return unauthorized();
            return ok(users.map((x: any) => basicDetails(x)));
        }

        function getUserById() {
            if (!isLoggedIn()) return unauthorized();

            const user = users.find((x: any) => x.id === idFromUrl());
            return ok(basicDetails(user));
        }

        function updateUser() {
            if (!isLoggedIn()) return unauthorized();

            let params = body;
            let user = users.find((x: any) => x.id === idFromUrl());

            // only update password if entered
            if (!params.password) {
                delete params.password;
            }

            // update and save user
            Object.assign(user, params);
            localStorage.setItem(usersKey, JSON.stringify(users));

            return ok();
        }

        function deleteUser() {
            if (!isLoggedIn()) return unauthorized();

            users = users.filter((x: any) => x.id !== idFromUrl());
            localStorage.setItem(usersKey, JSON.stringify(users));
            return ok();
        }

        // helper functions

        function ok(body?: any) {
            return of(new HttpResponse({ status: 200, body }))
                .pipe(delay(500)); // delay observable to simulate server api call
        }

        function error(message: any) {
            return throwError({ error: { message } })
                .pipe(materialize(), delay(500), dematerialize()); // call materialize and dematerialize to ensure delay even if an error is thrown (https://github.com/Reactive-Extensions/RxJS/issues/648);
        }

        function unauthorized() {
            return throwError({ status: 401, error: { message: 'Unauthorized' } })
                .pipe(materialize(), delay(500), dematerialize());
        }

        function basicDetails(user: User) {
            const { id, username, firstName, lastName } = user;
            return { id, username, firstName, lastName };
        }

        function isLoggedIn() {
            return headers.get('Authorization') === 'Bearer fake-jwt-token';
        }

        function idFromUrl() {
            const urlParts = url.split('/');
            return parseInt(urlParts[urlParts.length - 1]);
        }

        // custom

        function getEmployeeVisaById() {
            const employeeVisa = JSON.parse(localStorage.getItem('empVisaKey')!);
            return ok(basicDetailsEmpVisa(employeeVisa));
        }

        function basicDetailsEmpVisa(employeeVisa: EmployeeVisa) {
          const {
            hasOptReceipt,
            hasUploadedOptEad,
            optEadDaysLeft,
            isOptEadLessThan100Days,
            hasUploadedFormI983,
            hasFormI983HrSignedAndApproved,
            hasUploadedFormI20,
            hasUploadedOptStemReceipt,
            hasUploadedOptStemEad
           } = employeeVisa;
          return  {
            hasOptReceipt,
            hasUploadedOptEad,
            optEadDaysLeft,
            isOptEadLessThan100Days,
            hasUploadedFormI983,
            hasFormI983HrSignedAndApproved,
            hasUploadedFormI20,
            hasUploadedOptStemReceipt,
            hasUploadedOptStemEad
           };
      }

      function getAllEmployee() {
        const employeeProfile = JSON.parse(localStorage.getItem('empProfile')!);
        return ok(employeeProfile);
      }

    }
}

export const fakeBackendProvider = {
    // use fake backend in place of Http service for backend-less development
    provide: HTTP_INTERCEPTORS,
    useClass: FakeBackendInterceptor,
    multi: true
};
