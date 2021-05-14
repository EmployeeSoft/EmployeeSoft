import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { EmployeeVisa } from '../../common/_models'

@Injectable({
  providedIn: 'root'
})
export class EmployeeVisaService {
  private empVisaSubject: BehaviorSubject<EmployeeVisa>;
  private empVisa: Observable<EmployeeVisa>;

  constructor(
    private http: HttpClient
  ) {
    this.empVisaSubject = new BehaviorSubject<EmployeeVisa>(JSON.parse(localStorage.getItem('empVisaKey')!));
    this.empVisa = this.empVisaSubject.asObservable();
  }

  getById() {
    return this.http.get<EmployeeVisa>(`${environment.apiUrl}/employee/visa/`)
  }
}
