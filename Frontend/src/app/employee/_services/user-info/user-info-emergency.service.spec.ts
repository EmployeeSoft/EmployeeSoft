import { TestBed } from '@angular/core/testing';

import { UserInfoEmergencyService } from './user-info-emergency.service';

describe('UserInfoEmergencyService', () => {
  let service: UserInfoEmergencyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserInfoEmergencyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
