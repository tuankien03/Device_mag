import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from '../shared/service/message.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'http://localhost:8080/api/auth/'; // 🔥 Đổi URL API của bạn
  private tokenKey = 'authToken';

  constructor(private http: HttpClient,private router: Router,private messageService: MessageService) {}

  // ✅ Đăng nhập với API
  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(this.apiUrl + 'login', { username, password }).pipe(
      tap(response => {
        if (response.body && response.body.token) {
          localStorage.setItem(this.tokenKey, response.token);
          this.router.navigate(['/']);
          this.messageService.addMessage({ message: 'Đăng nhập thành công', status: true });
        }
      }),
      catchError(error => {
        this.messageService.addMessage({ message:error.error.message, status: false });
        return of(null); // or any other observable
      })
    );
  }

  // ✅ Kiểm tra trạng thái đăng nhập
  isLoggedIn(): boolean {
    return !!localStorage.getItem(this.tokenKey);
  }

  // ✅ Lấy token
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  // ✅ Đăng xuất
  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }
}
