import { Component, OnInit } from '@angular/core';
import { Pageable } from '../../model/pageable';
import { Device, DEVICE_KEYS } from '../../model/device';
import { MatTableDataSource } from '@angular/material/table';
import { DeviceFormComponent } from '../device-form/device-form.component';
import { DeviceService } from '../../service/device.service';
import { MessageService } from '../../service/message.service';
import { MatDialog } from '@angular/material/dialog';
import { CellAction } from '../../model/cellaction';
import { UserService } from '../../service/user.service';
import { Observable } from 'rxjs';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { UserSelectionDialogComponent } from '../user-selection-dialog/user-selection-dialog.component';

@Component({
  selector: 'app-available-devices',
  templateUrl: './available-devices.component.html',
  styleUrls: ['./available-devices.component.css']
})
export class AvailableDevicesComponent implements OnInit {


  totalData: number;
  devices: Device[] = [];
  config: Array<CellAction>;
  displayedColumns: string[] = [...DEVICE_KEYS, 'action'];
  dataSource = new MatTableDataSource<Device>(this.devices);
  pageable: Pageable = { pageNumber: 1, pageSize: 12, property: '', direction: '' };
  constructor(private deviceService: DeviceService, private messageService: MessageService, private dialog: MatDialog,private userService: UserService) {
    console.log(Object.keys(<Device>{}))
    this.config = [
      {
        name: 'Add',
        icon: 'add',
      },
    ];
  }
  ngOnInit(): void {
  }

  openConfirmDialog(): Observable<boolean> {
        const dialogRef = this.dialog.open(ConfirmDialogComponent, {
          width: '350px',
          data: { message: 'Bạn có chắc chắn muốn thực hiện hành động này?' }
        });
        return dialogRef.afterClosed()
  }


  onAction(event: { id: string, nameAction: string }) {
    if (event.nameAction === 'Add') {
      this.assignDevice(event.id);
    }
  }

  assignDevice(deviceId: string) {
    this.userService.getUsers({ pageNumber: 1, pageSize: 5, property: '', direction: '' }, '').subscribe(
      (data) => {
        const dialogRef = this.dialog.open(UserSelectionDialogComponent, {
          width: '400px',
          data: data.body.data
        });
        dialogRef.afterClosed().subscribe(result => {
          if (result) {
            this.userService.assignDevice(deviceId, result.id).subscribe(
              (data) => {
                this.messageService.addMessage({message: 'Thiết bị đã được gán thành công', status: true});
                this.loadData();
              },
              (error) => {
                this.messageService.addMessage({message: error.message, status: false});
              }
            );
          }
        });
      }
    )
  }


   
  
  


  formatDate(dateString: string): string {
    if (!dateString) return '';
    const date = new Date(dateString);
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear();
    const time = date.toLocaleTimeString();
    return `${day}/${month}/${year} - ${time}`;
  }

  loadData(): void {
    this.deviceService.getAvailableDevices(this.pageable, '').subscribe(
      (data) => {
        this.totalData = data.body.totalElements;
        this.devices = [];
        data.body.data.forEach((element: any) => {
          this.devices.push({
            deviceId: element.deviceId,
            name: element.name,
            description: element.description,
            status: element.status,
            createdAt: this.formatDate(element.createdAt),
            updatedAt: this.formatDate(element.updatedAt)
          });
        });
        console.log(this.devices)
        this.dataSource = new MatTableDataSource<Device>(this.devices);
      }
    )
  }

  onPageChange(pageable: Pageable) {
    this.pageable = pageable;
    this.loadData();
  }
  onSearch(value: string) {

  }

  onAdd() {
    
  }

  onRefresh() {
    this.loadData();
  }


}
