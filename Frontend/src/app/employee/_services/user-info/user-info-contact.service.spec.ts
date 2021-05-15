import { TestBed } from '@angular/core/testing';

import { UserInfoContactService } from './user-info-contact.service';

describe('UserInfoContactService', () => {
  let service: UserInfoContactService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserInfoContactService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
