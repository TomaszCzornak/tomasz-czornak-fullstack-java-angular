import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { SignupComponent } from './components/signup/signup.component';
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import { SigninComponent } from './components/signin/signin.component';
import { AccountActivationComponent } from './account-activation/account-activation.component';


@NgModule({
  declarations: [
    SignupComponent,
    SigninComponent,
    AccountActivationComponent
  ],
    imports: [
        CommonModule,
        AuthRoutingModule,
        MatCardModule,
        MatInputModule,
        FormsModule,
        ReactiveFormsModule,
        MatButtonModule
    ]
})
export class AuthModule { }
