import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeVisaStatusManagementComponent } from './employee-visa-status-management.component';

describe('EmployeeVisaStatusManagementComponent', () => {
  let component: EmployeeVisaStatusManagementComponent;
  let fixture: ComponentFixture<EmployeeVisaStatusManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeVisaStatusManagementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeVisaStatusManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
