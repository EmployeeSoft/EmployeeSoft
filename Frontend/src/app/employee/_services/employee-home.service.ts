import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
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

  getByRoleAndId(userRole: string, userId: string) {
    return this.http.get<any>(`http://localhost:8080/employee/?userRole="${userRole}"&userId=${userId}`)
  }

}
