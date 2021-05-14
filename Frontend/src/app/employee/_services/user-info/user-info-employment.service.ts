import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Contact} from '../../_models/contact';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';
import {Employment} from '../../_models/employment';

@Injectable({
  providedIn: 'root'
})
export class UserInfoEmploymentService {
  public employment: Observable<Employment>;

  constructor(private router: Router,
              private http: HttpClient) { }

  update(employment: Employment) {
    return this.http.post(`${environment.backendUrl}/user-info/employment`, employment);
  }
}
