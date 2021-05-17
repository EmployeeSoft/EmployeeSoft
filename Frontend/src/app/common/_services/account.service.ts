import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {HttpClient, HttpParams} from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';

import { environment } from '../../../environments/environment';
import { User } from '../_models';
import { FormGroup } from '@angular/forms';
import { Contact } from '../_models/contact';
import { Address} from '../_models/address';
import {Person} from '../../common/_models/person';
import {Employee} from '../../common/_models/employee';

@Injectable({ providedIn: 'root' })
export class AccountService {
    private userSubject: BehaviorSubject<User>;
    public user: Observable<User>;

    constructor(
        private router: Router,
        private http: HttpClient
    ) {
        this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user')!));
        this.user = this.userSubject.asObservable();
    }

    public get userValue(): User {
        return this.userSubject.value;
    }

    login(username: string, password: string) {
      const headers = {
        'Content-Type':  'application/json'
      };
      return this.http.post<any>(`http://localhost:9999/login`, { username, password }, {
        withCredentials: true
      })
        .pipe(map(user => {
          console.log(user);
          // login successful if there's a jwt token in the response
          //  if (user && user.token) {
          if (user) {
              // store user details and jwt token in local storage to keep user logged in between page refreshes
              localStorage.setItem('user', JSON.stringify(user));
              localStorage.setItem('jwt', user.jwt);
              this.userSubject.next(user);
          }

          return user;
      }));
    }

    logout() {
        // remove user from local storage and set current user to null
        localStorage.clear();
        sessionStorage.clear();

        // CALL LOGOUT API FROM LOCALHOST:9999
        // TO DO


        this.userSubject.next(null!);
        this.router.navigate(['/account/login']);
    }


    register(user: User) {
        return this.http.post(`${environment.apiUrl}/users/register`, user);
    }


    registerNewHire(email: string, username: string, password: string) {

      const headers = {
        'Content-Type':  'application/json'
      };

      return this.http.post<any>(`http://localhost:9999/register`, { email, username, password }, { headers });
    }

    getAll() {
        return this.http.get<User[]>(`${environment.apiUrl}/users`);
    }

    getById(id: string) {
        return this.http.get<User>(`${environment.apiUrl}/users/${id}`);
    }

    update(id: string, params: any) {
        return this.http.put(`${environment.apiUrl}/users/${id}`, params)
            .pipe(map(x => {
                // update stored user if the logged in user updated their own record
                if (id == this.userValue.id) {
                    // update local storage
                    const user = { ...this.userValue, ...params };
                    localStorage.setItem('user', JSON.stringify(user));

                    // publish updated user to subscribers
                    this.userSubject.next(user);
                }
                return x;
            }));
    }

    delete(id: string) {
        return this.http.delete(`${environment.apiUrl}/users/${id}`)
            .pipe(map(x => {
                // auto logout if the logged in user deleted their own record
                if (id == this.userValue.id) {
                    this.logout();
                }
                return x;
            }));
    }

    onboard(personDomain: Person, employeeDomain: Employee, addressDomain: Address, contactDomains: Contact[]) {
      console.log('from account service sending');
      return this.http.post<any>(`http://localhost:8080/onboard`, { personDomain, employeeDomain, addressDomain, contactDomains },
        { headers: {
          'Allow-Cross-Origin-Origin0' : '*'
        }
      });
    }

    upload(formData: FormData) {
      return this.http.post<any>(`http://localhost:8080/upload`, formData, {
        reportProgress: true,
        observe: 'events'
      });
    }

    download(filename: string, userId: string) {
      // const jwt = localStorage.getItem('jwt');
      // const helper = new JwtHelperService();
      // const decodedJwt = helper.decodeToken(jwt!);
      // const userId = decodedJwt.sub.toString();
//       const userId = '4';
      const params = new HttpParams().set('userId', userId).set('filename', filename);
      return this.http.get(`http://localhost:8080/download`, {
        params,
        reportProgress: true,
        observe: 'events',
        // responseType: 'blob'
      });
    }

    fetchData() {
      return this.http.get('/api/data').subscribe((data: any) => {
        return data.json();
      })
    }
}
