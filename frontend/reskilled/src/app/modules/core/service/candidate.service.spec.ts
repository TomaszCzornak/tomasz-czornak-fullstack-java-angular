import { TestBed } from '@angular/core/testing';
import { HttpTestingController } from '@angular/common/http/testing';
import { CandidateService } from './candidate.service';
import {CandidateResponse} from "../models/candidate";
import {environment} from "../../../../environments/environment.development";
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('CandidateService', () => {
  let service: CandidateService;
  let httpMock: HttpTestingController;
  let apiUrl: string = environment.proxyUrl;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(CandidateService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Verify that no unmatched requests are outstanding
  });

  it('should make a GET request to the API when searchCandidate is called', () => {
    const email = "test@example.com";
    const url = `${apiUrl}/candidates/find?search=${email}`;
    let response: CandidateResponse[] = []; // Expected response goes here

    service.searchCandidate(email).subscribe(data => {
      expect(data).toEqual(response); // The data should be the equal to the mocked response
    });

    const req = httpMock.expectOne(url);
    expect(req.request.method).toEqual('GET'); // The request method should be GET
    req.flush(response);
  });
});
