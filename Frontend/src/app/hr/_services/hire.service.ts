import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '../../../environments/environment';
import { Token } from '../../common/_models/token';

@Injectable({
  providedIn: 'root'
})
export class HireService {
  private tokenSubject: BehaviorSubject<Token>;
  public token: Observable<Token>;

  constructor(
    private router: Router,
    private http: HttpClient
) {
    this.tokenSubject = new BehaviorSubject<Token>(JSON.parse(localStorage.getItem('token')!));
    this.token = this.tokenSubject.asObservable();
}

  sendRegistrationToken(email: string) {
    return this.http.post<Token>(`${environment.apiUrl}/generateToken`, {
      "RegistrationTokenDomain": {
        "email": email,
        "createdBy": "HRAdmin"
      }
    })
  }
}
