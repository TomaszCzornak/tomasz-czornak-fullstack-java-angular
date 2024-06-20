import {FormControl} from "@angular/forms";

export function getErrorMessage(control: FormControl) {
  if (control.hasError('required')) {
    return 'Value in this field is required';
  }
  if (control.hasError('minlength')) {
    return 'Value should be longer';
  }
  if (control.hasError('maxlength')) {
    return 'Value should be shorter';
  }
  if (control.hasError('incorrect')) {
    return control.errors?.['message'];
  }
  return control.hasError('email') ? 'This is not email' : '';

}
