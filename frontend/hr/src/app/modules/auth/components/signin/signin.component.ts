import {Component, OnDestroy, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {Router} from "@angular/router";
import {SigninService} from "../../../core/service/signin.service";
import {LoginRequest} from "../../../core/models/signin";
import {Subscription} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {getErrorMessage} from "../../../core/validations/validation-messenger";
import {handleErrorStatus} from "../../../core/handlers/errorHandlers";
import {HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit, OnDestroy {
  errorMessage = '';
  subscription?: Subscription;
  token = '';

  public constructor(private titleService: Title,
                     private signinService: SigninService,
                     private router: Router) {
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }

  ngOnInit(): void {
    this.titleService.setTitle('HR Dashboard - Sign In');
  }

  signinForm: FormGroup = new FormGroup({
    email: new FormControl('', {validators:[Validators.required, Validators.email], nonNullable:true}),
    password: new FormControl('', {validators:[Validators.required, Validators.minLength(5), Validators.maxLength(15)], nonNullable:true}),
    rememberMe: new FormControl(false)
  });

  get controls() {
    return this.signinForm.controls;
  }

  onLoginSubmit() {
    const LoginBody: LoginRequest = {
      email: this.signinForm.get('email')?.value,
      password: this.signinForm.get('password')?.value,
    }
    this.signinService.postSignIn(LoginBody).subscribe(
      (response) => {
        const token = response.headers.get('Authorization');
        if (this.signinForm.get('rememberMe')?.value) {
          localStorage.setItem('accessToken', token || '');
        } else {
          if (typeof token === "string") {
            sessionStorage.setItem('accessToken', token);
          }
        }
        this.router.navigate(['/search']);
      },
      (error: HttpResponse<string>) => {
        this.errorMessage = handleErrorStatus( error);
      }
    );
  }


  protected readonly getErrorMessage = getErrorMessage;
}
