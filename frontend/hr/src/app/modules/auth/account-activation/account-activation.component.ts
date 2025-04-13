import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RegistrationService} from "../../core/service/registration.service";
import {switchMap} from "rxjs";

@Component({
  selector: 'app-account-activation',
  templateUrl: './account-activation.component.html',
  styleUrls: ['./account-activation.component.css']
})
export class AccountActivationComponent implements OnInit {
  errorMessage: null | string = null;

  constructor(
    private route: ActivatedRoute,
    private registrationService: RegistrationService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.route.paramMap
      .pipe(
        switchMap((params) =>
          this.registrationService.activateAccount(params.get('uid') as string)
        )
      )
      .subscribe({
        next: () => {
          //
          this.router.navigate(['/signin']);
        },
        error: (err) => {
          this.errorMessage = err;
        },
      });
  }
}
