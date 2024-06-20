import {Component, OnDestroy, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RegistrationOptionalRequest, UserOptionalResponse} from "../../../core/models/registration";
import {RegistrationService} from "../../../core/service/registration.service";
import {Router} from "@angular/router";
import {registerValidation} from "../../../core/validations/register-validations";
import {merge, Subscription} from "rxjs";
import {getErrorMessage} from "../../../core/validations/validation-messenger";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit, OnDestroy {

  subscription?: Subscription;
  errorMessage='';

  public constructor(private titleService: Title,
                     private registrationService: RegistrationService,
                     private router: Router)  {
  }

  public ngOnInit() {
    this.titleService.setTitle('HR Dashboard - Sign Up');
    merge(
      this.registerForm.controls['password'].valueChanges,
      this.registerForm.controls['repeatPassword'].valueChanges
    ).subscribe(() => {
      registerValidation(this.registerForm)})
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }

  registerForm = new FormGroup({
    firstName: new FormControl('', {validators:[Validators.required, Validators.minLength(3), Validators.maxLength(15)], nonNullable:true}),
    lastName: new FormControl('', {validators:[Validators.required, Validators.minLength(3), Validators.maxLength(15)], nonNullable:true}),
    email: new FormControl('', {validators:[Validators.required, Validators.email], nonNullable:true}),
    password: new FormControl('', {validators:[Validators.required, Validators.minLength(5), Validators.maxLength(15)], nonNullable:true}),
    repeatPassword: new FormControl('', {validators:[Validators.required, Validators.minLength(5), Validators.maxLength(15)], nonNullable:true}),
  })

  onSubmitRegisterForm() {
    const RegistrationRequestBody: RegistrationOptionalRequest = {
      firstName: this.registerForm.controls['firstName'].value as string,
      lastName: this.registerForm.controls['lastName'].value as string,
      email: this.registerForm.controls['email'].value as string,
      password: this.registerForm.controls['password'].value as string
    }

    this.registrationService.postRegistration(RegistrationRequestBody).subscribe(
      (response: UserOptionalResponse) => {
        this.router.navigate(['signin']);
      },
      (error: any) => {
        this.errorMessage = 'Wystąpił błąd w api';
      }
    );
  }

  get controls() {
    return this.registerForm.controls;
  }

  protected readonly getErrorMessage = getErrorMessage;
}
