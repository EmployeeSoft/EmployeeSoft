import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmergencySectionComponent } from './emergency-section.component';

describe('EmergencySectionComponent', () => {
  let component: EmergencySectionComponent;
  let fixture: ComponentFixture<EmergencySectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmergencySectionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmergencySectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
