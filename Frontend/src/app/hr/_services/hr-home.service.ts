import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HrHomeService {
  constructor(
    private http: HttpClient
  ) { }

  getAllEmployeeWithStatus(userRole: any) {
    return this.http.get<any>(`http://localhost:8080/incomplete-visa-status?userRole=${userRole}`);
  }

}
