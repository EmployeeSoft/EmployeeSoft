import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Contact} from '../../_models/contact';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';
import {Name} from '../../_models/name';
import {Employment} from '../../_models/employment';
import {Address} from '../../_models/address';

@Injectable({
  providedIn: 'root'
})
export class UserInfoNameService {
  public name: Observable<Name>;

  constructor(private router: Router,
              private http: HttpClient) { }

  updateName(name: Name) {
    return this.http.post(`${environment.backendUrl}/person/name`, name);
  }

  updateAddress(address: Address) {
    return this.http.post(`${environment.backendUrl}/person/address`, address);
  }

  updateContact(contact: Contact) {
    return this.http.post(`${environment.backendUrl}/person/contact`, contact);
  }

  updateEmployment(employ: Employment) {
    return this.http.post(`${environment.backendUrl}/person/employment`, employ);
  }
}
