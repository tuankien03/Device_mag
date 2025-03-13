import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Observable } from 'rxjs/internal/Observable';
import { ResponseApi } from '../model/responseapi';
import { PageResponse } from '../model/pageresponse';
import { User } from '../model/user';
import { tap, catchError } from 'rxjs/operators';
import { Pageable } from '../model/pageable';
import { Device } from '../model/device';
import { AuthService } from 'src/app/auth/auth.service';
import { throwError } from 'rxjs';
import { Assignment } from '../model/assignment';
import { UserDevice } from '../model/userdevice';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/';
  private tokenKey = 'authToken';
  private currentUserId = this.authService.getUserId(); 

  constructor(private http: HttpClient, private messageService: MessageService,private authService: AuthService) { }

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
  
  getReturningDevices(pageable: Pageable): Observable<ResponseApi<PageResponse<UserDevice[]>>> {
    let params = new HttpParams().set('page', pageable.pageNumber.toString()).set('size', pageable.pageSize.toString());
    if (pageable.property) {
      params = params.set('property', pageable.property);
    }
    if (pageable.direction) {
      params = params.set('direction', pageable.direction);
    }
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    return this.http.get<ResponseApi<PageResponse<UserDevice[]>>>(this.apiUrl + 'user-device/returning-device', { headers, params }).pipe(
      tap(response => {
        console.log(response);
      }),
      catchError(error => {
        throw error.error;
      })
    ); 

  }

  getBorrowedDevices(pageable: Pageable): Observable<ResponseApi<PageResponse<Device[]>>> {
    let params = new HttpParams().set('page', pageable.pageNumber.toString()).set('size', pageable.pageSize.toString());
    if (pageable.property) {
      params = params.set('property', pageable.property);
    }
    if (pageable.direction) {
      params = params.set('direction', pageable.direction);
    }
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    console.log( this.currentUserId)
    return this.http.get<ResponseApi<PageResponse<Device[]>>>(this.apiUrl + 'user/' + this.currentUserId + '/devices', { headers, params }).pipe(
      tap(response => {
        console.log(response);
      }),
      catchError(error => {
        throw error.error;
      })
    );
  }

  changePassword(id: string, userData: {oldPassword: string,newPassword: string}) {
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.put<ResponseApi<User>>(this.apiUrl + "user/password",userData , {headers}).pipe(
      tap(
        response => {
          console.log(response);
        }
      ), catchError(error => {
        throw error.error;
      })
    )
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
    return this.http.put<ResponseApi<User>>(this.apiUrl + "user/" + id, {username, password, role }, {headers}).pipe(
      tap(
        response => {
          console.log(response);
        }
      ), catchError(error => {
        throw error.error;
      })
    )

  }

  createUser(userData: {username: string, password: string, role: string }) {
    const {username, password, role} = userData;
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.post<ResponseApi<User>>(this.apiUrl + "user", {username, password, role }, {headers}).pipe(
      tap(
        response => {
          console.log(response);
        }
      ), catchError(error => {
        throw error.error;
      })
    )
  }

  assignDevice(deviceId: string, userId: string): Observable<ResponseApi<Assignment>> {
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.post<ResponseApi<Assignment>>(this.apiUrl + "assignment", {userId, deviceId}, {headers}).pipe(
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

  returnDevice(id: string): Observable<ResponseApi<string>> {
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    console.log(token);
    return this.http.put<ResponseApi<string>>(
      `${this.apiUrl}assignment/${id}/return`,  
      {}, 
      { headers } 
    ).pipe(
      tap(response => console.log("API Response:", response)),
      catchError(error => {
        console.error("API Error:", error);
        return throwError(() => error.error);
      })
    );
  }

  confirmDevice(id: string): Observable<ResponseApi<string>> {
    const token = localStorage.getItem(this.tokenKey) || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    console.log(token);
    return this.http.put<ResponseApi<string>>(
      `${this.apiUrl}assignment/${id}/confirm`,  
      {}, 
      { headers } 
    ).pipe(
      tap(response => console.log("API Response:", response)),
      catchError(error => {
        console.error("API Error:", error);
        return throwError(() => error.error);
      })
    );
  }


}
