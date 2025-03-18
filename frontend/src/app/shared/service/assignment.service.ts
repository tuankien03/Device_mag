import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { ResponseApi } from '../model/responseapi';
import { catchError, tap } from 'rxjs/operators';
import { PageResponse } from '../model/pageresponse';
import { UserDevice } from '../model/userdevice';
import { Observable } from 'rxjs';
import { Pageable } from '../model/pageable';
import { getHeaders } from '../model/headers';

@Injectable({
  providedIn: 'root'
})
export class AssignmentService {

  private apiUrl = 'http://localhost:8080/api/';  
    constructor(private http: HttpClient, private messageService: MessageService) { }
    getAllCurrentAssignedDevice(pageable: Pageable):  Observable<ResponseApi<PageResponse<UserDevice[]>>> {
       let params = new HttpParams().set('page', pageable.pageNumber.toString()).set('size', pageable.pageSize.toString());
          if (pageable.property) {
            params = params.set('property', pageable.property);
          }
          if (pageable.direction) {
            params = params.set('direction', pageable.direction);
          }
          return this.http.get<ResponseApi<PageResponse<UserDevice[]>>>(this.apiUrl + 'user-device/assigned-device', { headers: getHeaders(), params }).pipe(
            tap(response => {
              console.log(response);
            }),
            catchError(error => {
              throw error.error;
            })
          ); 
    }

    deleteAssignment(id: string) {
        return this.http.delete<ResponseApi<string>>(this.apiUrl + "assignment/" + id, { headers: getHeaders() }).pipe(
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
