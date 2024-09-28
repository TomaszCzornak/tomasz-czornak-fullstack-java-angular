import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignupComponent} from "./components/signup/signup.component";
import {SigninComponent} from "./components/signin/signin.component";
import {AccountActivationComponent} from "./account-activation/account-activation.component";

const routes: Routes = [{
  path: 'signup',
  component: SignupComponent
},
  {
    path: 'signin',
    component: SigninComponent
  },
  {path: 'v1/activate/:uid', component: AccountActivationComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule {

}
