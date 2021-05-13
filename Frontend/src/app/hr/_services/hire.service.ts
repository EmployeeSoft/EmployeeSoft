import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '../../../environments/environment';
import { Token } from '../../common/_models/token';
import { NewHire } from '../../common/_models/new-hire';

@Injectable({
  providedIn: 'root'
})
export class HireService {
  private tokenSubject: BehaviorSubject<Token>;
  public token: Observable<Token>;

  private newHireSubject: BehaviorSubject<NewHire>;
  public newHire: Observable<NewHire>;

  constructor(
    private router: Router,
    private http: HttpClient
) {
    this.tokenSubject = new BehaviorSubject<Token>(JSON.parse(localStorage.getItem('token')!));
    this.token = this.tokenSubject.asObservable();

    this.newHireSubject = new BehaviorSubject<NewHire>(JSON.parse(localStorage.getItem('token')!));
    this.newHire = this.newHireSubject.asObservable();
}

/* for fake-backend
  sendRegistrationToken(email: string) {
    return this.http.post<Token>(`${environment.apiUrl}/generateToken`, {
      "RegistrationTokenDomain": {
        "email": email,
        "createdBy": "HRAdmin"
      }
    })
  }

  */
  sendRegistrationToken(email: string) {

    const headers = {
      'Content-Type':  'application/json'
    }

    const body = {

      "email": email,
      "createdBy": "HRAdmin"

    }

    return this.http.post<any>(`http://localhost:9999/generateToken`, body, { headers });
  }
}
