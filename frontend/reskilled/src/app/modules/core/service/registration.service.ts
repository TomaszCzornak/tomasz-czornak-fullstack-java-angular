import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment.development";
import {HttpClient} from "@angular/common/http";
import {RegistrationOptionalRequest, UserOptionalResponse} from "../models/registration";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private apiUrl = environment.proxyUrl;
  constructor(private httpClient: HttpClient) { }

  postRegistration(registration: RegistrationOptionalRequest): Observable<UserOptionalResponse> {
    return this.httpClient.post<UserOptionalResponse>(`${this.apiUrl}/register`, registration);
  }
}
