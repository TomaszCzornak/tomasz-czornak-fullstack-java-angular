import {Component} from '@angular/core';
import {FormControl} from "@angular/forms";
import {CandidateService} from "../../core/service/candidate.service";
import {HttpResponse} from "@angular/common/http";
import {CandidateResponse} from "../../core/models/candidate";
import {handleErrorStatus} from "../../core/handlers/errorHandlers";


@Component({
  selector: 'app-search-candidate',
  templateUrl: './search-candidate.component.html',
  styleUrls: ['./search-candidate.component.css']
})
export class SearchCandidateComponent {
  filterValue = new FormControl('', { nonNullable: true })
  errorMessage = '';
  dataSource?: CandidateResponse[];
  searchCompleted: boolean = false;

  constructor(private candidateService: CandidateService) {
  }

  findCandidate(): void {
      const email = this.filterValue.value;
      this.candidateService.searchCandidate(email).subscribe((response: CandidateResponse[]) => {
          this.searchCompleted = true;
        this.dataSource = response;
      },
        (error: HttpResponse<string>) => {
          this.searchCompleted = true;
        this.errorMessage = handleErrorStatus(error);
        }

      );
    this.filterValue.setValue('');
  }

}
