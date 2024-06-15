import {FormGroup} from "@angular/forms";

export function validateRegisterValidation(registerForm: FormGroup) {
  const password = registerForm.controls['password'].value;
  const repeatPassword = registerForm.controls['repeatPassword'].value

  registerForm.controls['repeatPassword'].setErrors(null);

  // check if password is empty
  if (password === '') {
    registerForm.controls['password'].setErrors({ incorrect: true, message: 'password is empty' });
  }

  // then check if passwords match
  if (password !== repeatPassword) {
    registerForm.controls['repeatPassword'].setErrors({ incorrect: true, message: 'passwords don\'t match' });
  }
}
