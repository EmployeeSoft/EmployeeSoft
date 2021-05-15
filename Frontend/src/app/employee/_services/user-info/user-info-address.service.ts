import { Injectable } from '@angular/core';
import {User} from '../../../common/_models';
import {environment} from '../../../../environments/environment';
import {Address} from '../../_models/address';
import {BehaviorSubject, Observable} from 'rxjs';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserInfoAddressService {
  private addressSubject: BehaviorSubject<Address>;
  public address: Observable<Address>;
  constructor(private router: Router,
              private http: HttpClient) { }

  update(address: Address) {
    return this.http.post(`${environment.backendUrl}/user-info/address`, address);
  }
}
