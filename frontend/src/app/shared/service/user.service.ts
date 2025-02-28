import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Observable } from 'rxjs/internal/Observable';
import { ResponseApi } from '../model/responseapi';
import { PageResponse } from '../model/pageresponse';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/';
  private tokenKey = 'authToken';

  constructor(private http: HttpClient,private messageService: MessageService) {}

  getUsers(): Observable<ResponseApi<PageResponse<User[]>>> {
    this.messageService.addMessage({ message: 'Lấy danh sách người dùng', status: true });
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.get<ResponseApi<PageResponse<User[]>>>(this.apiUrl + 'user', { headers }).pipe();
  }

}
