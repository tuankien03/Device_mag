import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient,private messageService: MessageService) {}

  getUsers(): Observable<any> {
    this.messageService.addMessage({ message: 'Lấy danh sách người dùng', status: true });
    const token = localStorage.getItem('access_token') || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.get(this.apiUrl + 'users', { headers });
  }

}
