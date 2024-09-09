export interface CandidateResponse {
  id: number;
  email: string;
  createdBy: UserDto;
  error?: string;
}

export interface UserDto {
  id: number;
  createdAt: string;
  updatedAt: string;
  firstName: string;
  lastName: string;
  email: string;
}

export class Candidate implements CandidateResponse {
  constructor(
    public id: number,
  public email: string,
  public createdBy: UserDto){}
}
