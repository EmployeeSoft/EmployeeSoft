import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { EmployeeVisa } from '../../common/_models';

@Injectable({
  providedIn: 'root'
})
export class EmployeeHomeService {

  private empHomeSubject: BehaviorSubject<any>;
  private empHome: Observable<any>;

  constructor(
    private http: HttpClient
  ) {
    this.empHomeSubject = new BehaviorSubject<EmployeeVisa>(JSON.parse(localStorage.getItem('empVisaKey')!));
    this.empHome = this.empHomeSubject.asObservable();
  }

  getById() {
    return this.http.get<EmployeeVisa>(`${environment.apiUrl}/employee/visa/`)
  }
}
