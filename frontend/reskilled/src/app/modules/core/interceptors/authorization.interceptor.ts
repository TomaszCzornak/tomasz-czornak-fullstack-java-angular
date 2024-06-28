import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthorizationInterceptor implements HttpInterceptor {


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const jwt = localStorage.getItem('Authorization') ? `Bearer ${localStorage.getItem('Authorization')}` : '';
    debugger;
    if (localStorage.getItem('Authorization')) {
      const cloneReq = request.clone({
        setHeaders: {
          Authorization: 'Bearer ' + jwt,
        }
      });
      return next.handle(cloneReq);
    }
    return next.handle(request);
  }
}
