export interface RegistrationRequest {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  repeatPassword: string;
}

export interface UserResponse extends Omit<RegistrationRequest, 'password' | 'repeatPassword'> {
  id: number;
  createdAt: string;
  updatedAt: string;
}

export type UserOptionalResponse = Partial<Omit<UserResponse, 'createdAt' | 'updatedAt'>>;

export type RegistrationOptionalRequest = Partial<Omit<RegistrationRequest, 'repeatPassword'>>;
