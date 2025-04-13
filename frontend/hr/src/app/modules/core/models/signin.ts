export interface LoginRequest {
  email: string;
  password: string;
}

export interface User {
  id: number;
  createdAt: string;
  updatedAt: string;
  firstName: string;
  lastName: string;
  password: string;
  repeatPassword: string;
  isLock: boolean;
  isEnabled:boolean;
  role: string;
}

export interface LoginResponse extends Omit<User, 'password' | 'repeatPassword' | 'isLock' | 'isEnabled' | 'role'>  {

}
