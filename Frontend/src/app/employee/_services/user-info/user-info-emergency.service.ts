import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Contact} from '../../_models/contact';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';
import {Emergency} from '../../_models/emergency';

@Injectable({
  providedIn: 'root'
})
export class UserInfoEmergencyService {
  public emergency: Observable<Emergency>;

  constructor(private router: Router,
              private http: HttpClient) { }

  update(emergencys: []) {
    return this.http.post(`${environment.backendUrl}/user-info/emergency`, emergencys);
  }
}
