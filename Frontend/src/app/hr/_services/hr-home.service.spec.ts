import { TestBed } from '@angular/core/testing';

import { HrHomeService } from './hr-home.service';

describe('HrHomeService', () => {
  let service: HrHomeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HrHomeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
