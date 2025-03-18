import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Pageable } from '../model/pageable';
import { Observable } from 'rxjs';
import { ResponseApi } from '../model/responseapi';
import { PageResponse } from '../model/pageresponse';
import { Device } from '../model/device';
import { catchError, tap } from 'rxjs/operators';
import { getHeaders } from '../model/headers';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  private apiUrl = 'http://localhost:8080/api/';
  private tokenKey = 'authToken';

  constructor(private http: HttpClient, private messageService: MessageService) { }

  getDevices(pageable: Pageable, searchText: string): Observable<ResponseApi<PageResponse<Device[]>>> {
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
    this.messageService.addMessage({ message: 'Lấy danh sách thiết bị', status: true });
    return this.http.get<ResponseApi<PageResponse<Device[]>>>(this.apiUrl + 'device', { headers: getHeaders(), params }).pipe();
  }

  getAvailableDevices(pageable: Pageable, searchText: string): Observable<ResponseApi<PageResponse<Device[]>>> {
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
    this.messageService.addMessage({ message: 'Lấy danh sách thiết bị', status: true });
    return this.http.get<ResponseApi<PageResponse<Device[]>>>(this.apiUrl + 'device/available', { headers: getHeaders(), params }).pipe();
  }

  getDeviceById(id: string) {
    return this.http.get<ResponseApi<PageResponse<Device[]>>>(this.apiUrl + 'device/' + id, { headers: getHeaders() }).pipe(
      tap(response => {
        console.log(response)
      }
      ), catchError(error => {
        throw error.error;
      }
      )
    );
  }

  createDevice(deviceData: { name: string, status: string, description: string }) {
    return this.http.post<ResponseApi<Device>>(this.apiUrl + 'device', deviceData, { headers: getHeaders() }).pipe(
      tap(
        response => {
          console.log(response);
        }
      ), catchError(error => {
        throw error.error;
      })
    )
  }

  updateDevice(id: string, deviceData: { name: string, status: string, description: string }) {
    return this.http.put<ResponseApi<Device>>(this.apiUrl + "device/" + id, deviceData, { headers: getHeaders() }).pipe(
      tap(
        response => {
          console.log(response);
        }
      ), catchError(error => {
        throw error.error;
      })
    )
  }

  deleteDevice(id: string) {
    return this.http.delete<ResponseApi<string>>(this.apiUrl + "device/" + id, { headers: getHeaders() }).pipe(
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
