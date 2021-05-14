import { TestBed } from '@angular/core/testing';

import { UserInfoAddressService } from './user-info-address.service';

describe('UserInfoAddressService', () => {
  let service: UserInfoAddressService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserInfoAddressService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
