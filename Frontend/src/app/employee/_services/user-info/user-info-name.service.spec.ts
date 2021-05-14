import { TestBed } from '@angular/core/testing';

import { UserInfoNameService } from './user-info-name.service';

describe('UserInfoNameService', () => {
  let service: UserInfoNameService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserInfoNameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
