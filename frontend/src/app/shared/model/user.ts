export interface User {
    id: string;
    username: string;
    role: UserRole;
    createdAt: string;
    updatedAt: string;
}

export enum UserRole {
    Admin = "ADMIN",    
    User = "USER",    
}
  