import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatFormFieldModule} from "@angular/material/form-field";
import {CandidateModule} from "./modules/candidate/candidate.module";
import {AuthModule} from "./modules/auth/auth.module";
import {AuthorizationInterceptor} from "./modules/core/interceptors/authorization.interceptor";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AuthModule,
    HttpClientModule,
    MatFormFieldModule,
    CandidateModule,
  ],
  providers: [  { provide: HTTP_INTERCEPTORS, useClass: AuthorizationInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
