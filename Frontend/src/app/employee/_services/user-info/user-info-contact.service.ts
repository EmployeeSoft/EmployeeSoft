import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Address} from '../../_models/address';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {Contact} from '../../_models/contact';
import {environment} from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserInfoContactService {
  public contact: Observable<Contact>;

  constructor(private router: Router,
              private http: HttpClient) { }

  update(contact: Contact) {
    return this.http.post(`${environment.backendUrl}/user-info/contact`, contact);
  }
}
