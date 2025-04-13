import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SearchCandidateComponent} from "./modules/candidate/search-candidate/search-candidate.component";

const routes: Routes = [
  {path:'', redirectTo: '/signup', pathMatch: 'full', title:'Sign Up'},
  {path:'search', component: SearchCandidateComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
