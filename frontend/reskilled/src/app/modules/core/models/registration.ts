export interface RegistrationRequest {
  firstName: string;
  lastName: string;
  email: string;
  password: string;

}

export interface UserResponse {
  id: number;
  createdAt: string;
  updatedAt: string;
  firstName: string;
  lastName: string;
  email: string;

}
