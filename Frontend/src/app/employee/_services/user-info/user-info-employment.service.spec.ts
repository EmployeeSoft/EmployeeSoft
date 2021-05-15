import { TestBed } from '@angular/core/testing';

import { UserInfoEmploymentService } from './user-info-employment.service';

describe('UserInfoEmploymentService', () => {
  let service: UserInfoEmploymentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserInfoEmploymentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
