import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CandidateResponse} from "../models/candidate";
import {environment} from "../../../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class CandidateService {
  apiUrl: string = environment.proxyUrl;

  constructor(private http: HttpClient) {
  }

  searchCandidate(email: string): Observable<CandidateResponse[]> {
      return this.http.get<CandidateResponse[]>(`${this.apiUrl}/candidates/find?search=${email}`);
    }
}
