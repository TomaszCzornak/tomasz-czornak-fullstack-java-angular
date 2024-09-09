import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CandidateRoutingModule } from './candidate-routing.module';
import { SearchCandidateComponent } from './search-candidate/search-candidate.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppComponent} from "../../app.component";
import {MatButtonModule} from "@angular/material/button";
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatCardModule} from "@angular/material/card";


@NgModule({
  declarations: [
    SearchCandidateComponent
  ],
  imports: [
    CommonModule,
    CandidateRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatTableModule,
    MatSortModule,
    MatCardModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class CandidateModule { }
