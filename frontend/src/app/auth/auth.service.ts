import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from '../shared/service/message.service';
import { Observable, of } from 'rxjs';
import { AuthenticationResponse } from '../shared/model/authenticationresponse';
import { ResponseApi } from '../shared/model/responseapi';
import { catchError, tap } from 'rxjs/operators';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth/';
  private tokenKey = 'authToken';
  constructor(private router: Router, private http: HttpClient, private messageService: MessageService) { }


  // ✅ Đăng nhập với API
  login(username: string, password: string): Observable<ResponseApi<AuthenticationResponse>> {
    return this.http.post<any>(this.apiUrl + 'login', { username, password }).pipe(
      tap(response => {
        localStorage.setItem(this.tokenKey, response.body.token);
      }
      ),
      catchError(error => {
        throw error.error;
      })
    );
  }

  logout() {
    localStorage.removeItem(this.tokenKey);
    this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem('authToken');
    this.http.post<any>(this.apiUrl + 'introspect', { token }).subscribe(
      (data) => {
        console.log(data);
      }
    );
    return token !== null;
  }

  getRole(): string | null {
    const token = localStorage.getItem('authToken');
    if (!token) return null;
    try {
      const decoded: any = jwtDecode(token);
      return decoded.scope || null;
    } catch (error) {
      console.error('Invalid token:', error);
      this.messageService.addMessage({ message: 'Invalid token', status: false });
      return null;
    }
  }

  getUsername(): string | null {
    const token = localStorage.getItem('authToken');
    if (!token) return null;
    try {
      const decoded: any = jwtDecode(token);
      return decoded.sub || null;
    } catch (error) {
      console.error('Invalid token:', error);
      this.messageService.addMessage({ message: 'Invalid token', status: false });
      return null;
    }
  }

  hasRole(role: string): boolean {
    return this.getRole() === role;
  }
}
