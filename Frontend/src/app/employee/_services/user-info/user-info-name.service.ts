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

  getDocumentData(userId: string){
    return this.http.get(`${environment.backendUrl}/person/getDocuments?userId=${userId}`);
  }

  updateName(name: Name) {
    return this.http.post(`${environment.backendUrl}/person/name`, name);
  }

  updateAddress(address: Address) {
    return this.http.post(`${environment.backendUrl}/person/address`, address);
  }

  updateContact(personId: number, emergency: Contact[]) {
    return this.http.post(`${environment.backendUrl}/person/contact`, {personId, emergency});
  }

  updateEmployment(employ: Employment) {
    return this.http.post(`${environment.backendUrl}/person/employment`, employ);
  }
}
