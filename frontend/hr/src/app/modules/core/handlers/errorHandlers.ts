import { HttpResponse } from "@angular/common/http";

export function handleErrorStatus(error: HttpResponse<string>) {
  let errorMessage = '';

  switch (error.status) {
    case 403:
      errorMessage = 'Your credentials are incorrect';
      break;
    case 500:
      errorMessage = 'Something went wrong';
      break;
    case 400:
      errorMessage = "Wrong data used";
      break;
    default:
      errorMessage = 'Error:' + errorMessage;
      break;
  }

  return errorMessage;
}

