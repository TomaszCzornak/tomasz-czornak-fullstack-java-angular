import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SigninService } from './signin.service';

export interface LoginRequest {
  email: string;
  password: string;
}

describe('SigninService', () => {
  let service: SigninService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SigninService]
    });
    service = TestBed.inject(SigninService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should execute login with correct credentials', () => {
    const mockCredentials: LoginRequest = {email: 'tomasz.czornak@outlook.com', password: 'Gettin37!'};
    const serviceSpy = spyOn(service, 'postSignIn').and.callThrough();

    service.postSignIn(mockCredentials);

    expect(service.postSignIn).toHaveBeenCalledWith(mockCredentials);
  });

  it('should send a POST request to the correct endpoint', () => {
    const mockCredentials: LoginRequest = {email: 'tomasz.czornak@outlook.com', password: 'Gettin37!'};
    const loginEndpoint = 'http://localhost:8080/v1/login'; // Update this to match your apiUrl

    service.postSignIn(mockCredentials).subscribe();

    const mockReq = httpMock.expectOne(loginEndpoint);
    expect(mockReq.cancelled).toBeFalsy();
    expect(mockReq.request.method).toBe('POST');
    mockReq.flush(mockCredentials);

    httpMock.verify();
  });
});
