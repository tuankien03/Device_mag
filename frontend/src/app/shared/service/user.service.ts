import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Observable } from 'rxjs/internal/Observable';
import { ResponseApi } from '../model/responseapi';
import { PageResponse } from '../model/pageresponse';
import { User } from '../model/user';
import { tap, catchError } from 'rxjs/operators';
import { Pageable } from '../model/pageable';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/';
  private tokenKey = 'authToken';

  constructor(private http: HttpClient, private messageService: MessageService) { }

  getUsers(pageable: Pageable, searchText: string): Observable<ResponseApi<PageResponse<User[]>>> {
    let params = new HttpParams().set('page', pageable.pageNumber.toString()).set('size', pageable.pageSize.toString());
    if (pageable.property) {
      params = params.set('property', pageable.property);
    }
    if (pageable.direction) {
      params = params.set('direction', pageable.direction);
    }
    if (searchText) {
      params = params.set('searchText', searchText);
    }
    this.messageService.addMessage({ message: 'Lấy danh sách người dùng', status: true });
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.get<ResponseApi<PageResponse<User[]>>>(this.apiUrl + 'user', { headers, params }).pipe();
  }

  getUserById(id: string): Observable<ResponseApi<User>> {
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.get<ResponseApi<User>>(this.apiUrl + 'user/' + id, { headers }).pipe(
      tap(response => {
        console.log(response)
      }
      ), catchError(error => {
        throw error.error;
      }
      )
    );
  }

  updateUser(id: string, userData: {username: string, password: string, role: string }) {
    const {username, password, role } = userData;
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.put<ResponseApi<User>>(this.apiUrl + "user/" + id, {username, password, role }).pipe(
      tap(
        response => {
          console.log(response);
        }
      ), catchError(error => {
        throw error.error;
      })
    )

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


}
