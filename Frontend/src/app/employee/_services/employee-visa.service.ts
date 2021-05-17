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

  getVisaStatus(id: string, role: string) {
    return this.http.get<EmployeeVisa>(`http://localhost:8080/employee-visa-status?userId=${id}&userRole=${role}`);
  }
}
