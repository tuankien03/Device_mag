import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { ResponseApi } from '../model/responseapi';
import { catchError, tap } from 'rxjs/operators';
import { PageResponse } from '../model/pageresponse';
import { UserDevice } from '../model/userdevice';
import { Observable } from 'rxjs';
import { Pageable } from '../model/pageable';

@Injectable({
  providedIn: 'root'
})
export class AssignmentService {

  private apiUrl = 'http://localhost:8080/api/';
    private tokenKey = 'authToken';
  
    constructor(private http: HttpClient, private messageService: MessageService) { }

    getAllCurrentAssignedDevice(pageable: Pageable):  Observable<ResponseApi<PageResponse<UserDevice[]>>> {
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
      
          return this.http.get<ResponseApi<PageResponse<UserDevice[]>>>(this.apiUrl + 'user-device/assigned-device', { headers, params }).pipe(
            tap(response => {
              console.log(response);
            }),
            catchError(error => {
              throw error.error;
            })
          ); 
    }

    deleteAssignment(id: string) {
        const token = localStorage.getItem(this.tokenKey) || '';
        const headers = new HttpHeaders({
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        });
        return this.http.delete<ResponseApi<string>>(this.apiUrl + "assignment/" + id, { headers }).pipe(
          tap(
            response => {
              console.log(response);
            }
          ), catchError(error => {
            throw error.error;
          })
        )
      }
}
