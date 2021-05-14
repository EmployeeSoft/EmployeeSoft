import { TestBed } from '@angular/core/testing';

import { EmployeeVisaService } from './employee-visa.service';

describe('EmployeeVisaService', () => {
  let service: EmployeeVisaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmployeeVisaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
