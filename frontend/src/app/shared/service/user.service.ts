import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Observable } from 'rxjs/internal/Observable';
import { ResponseApi } from '../model/responseapi';
import { PageResponse } from '../model/pageresponse';
import { User } from '../model/user';
import { tap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/';
  private tokenKey = 'authToken';

  constructor(private http: HttpClient, private messageService: MessageService) { }

  getUsers(): Observable<ResponseApi<PageResponse<User[]>>> {
    this.messageService.addMessage({ message: 'Lấy danh sách người dùng', status: true });
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.get<ResponseApi<PageResponse<User[]>>>(this.apiUrl + 'user', { headers }).pipe();
  }

  deleteUser(id: string): Observable<ResponseApi<string>> {
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.delete<ResponseApi<string>>(this.apiUrl + 'user/' + id, { headers }).pipe(
      tap(response => {
      }
      ), catchError(error => {
        throw error.error;
      }
      )
    );
  }

  searchUserByUsername(username: string, page: number, size: number, property: string, direction: string): Observable<ResponseApi<PageResponse<User[]>>> {
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    const defaultPage = 0;
    const defaultSize = 10;
    const defaultProperty = 'id';
    const defaultDirection = 'asc';


    let params = new HttpParams()
      .set('page', (page ?? defaultPage).toString())
      .set('size', (size ?? defaultSize).toString());
    // Chỉ thêm 'property' nếu có giá trị, nếu không dùng giá trị mặc định
    if (property) {
      params = params.set('property', property);
    }
    
    // Chỉ thêm 'direction' nếu có giá trị
    if (direction) {
      params = params.set('direction', direction);
    }
    return this.http.get<ResponseApi<PageResponse<User[]>>>(this.apiUrl + 'user/search/' + username , { headers, params }).pipe(
      tap(response => {
      }
      ), catchError(error => {
        throw error.error;
      }
      )
    );
  }

}
