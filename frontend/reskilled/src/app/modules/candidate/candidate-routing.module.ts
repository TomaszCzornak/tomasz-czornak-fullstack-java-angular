import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SearchCandidateComponent} from "./search-candidate/search-candidate.component";

const routes: Routes = [
  {
    path: 'search',
    component: SearchCandidateComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CandidateRoutingModule { }
