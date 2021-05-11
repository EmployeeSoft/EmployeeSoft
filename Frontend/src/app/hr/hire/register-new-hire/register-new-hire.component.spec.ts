import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterNewHireComponent } from './register-new-hire.component';

describe('RegisterNewHireComponent', () => {
  let component: RegisterNewHireComponent;
  let fixture: ComponentFixture<RegisterNewHireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterNewHireComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterNewHireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
