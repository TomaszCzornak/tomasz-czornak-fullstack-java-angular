import { Injectable } from '@angular/core';

import {HttpClient, HttpResponse} from "@angular/common/http";
import {LoginRequest, LoginResponse} from "../models/signin";
import {Observable} from "rxjs";
import {environment} from "../../../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class SigninService {
  private apiUrl: string = environment.proxyUrl;

  constructor(private httpClient: HttpClient) {

  }

  postSignIn(loginRequest: LoginRequest): Observable<HttpResponse<LoginResponse>> {
    return this.httpClient.post<LoginResponse>(`${this.apiUrl}/login`, loginRequest, {observe: 'response'});
  }
}
