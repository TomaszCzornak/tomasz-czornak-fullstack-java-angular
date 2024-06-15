import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment.development";
import {HttpClient} from "@angular/common/http";
import {RegistrationRequest, UserResponse} from "../models/registration";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private apiUrl = environment.proxyUrl;
  constructor(private httpClient: HttpClient) { }

  postRegistration(registration: RegistrationRequest): Observable<UserResponse> {
    return this.httpClient.post<UserResponse>(`${this.apiUrl}/register`, registration);
  }
}
