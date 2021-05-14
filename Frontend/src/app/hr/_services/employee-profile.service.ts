import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { EmployeeProfile } from '../../common/_models'

@Injectable({
  providedIn: 'root'
})
export class EmployeeProfileService {
  private empVisaSubject: BehaviorSubject<EmployeeProfile>;
  private empVisa: Observable<EmployeeProfile>;

  constructor(
    private http: HttpClient
  ) {
    this.empVisaSubject = new BehaviorSubject<EmployeeProfile>(JSON.parse(localStorage.getItem('empProfile')!));
    this.empVisa = this.empVisaSubject.asObservable();
  }

  getAll() {
    return this.http.get<EmployeeProfile>(`${environment.apiUrl}/hr/employee-profile/`)
  }
}
