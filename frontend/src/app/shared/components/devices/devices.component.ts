import { Component, OnInit } from '@angular/core';
import { Device, DEVICE_DISPLAYED_COLUMNS, DEVICE_KEYS } from '../../model/device';
import { CellAction } from '../../model/cellaction';
import { MatTableDataSource } from '@angular/material/table';
import { Pageable } from '../../model/pageable';
import { DeviceService } from '../../service/device.service';
import { MessageService } from '../../service/message.service';
import { MatDialog } from '@angular/material/dialog';
import { DeviceFormComponent } from '../device-form/device-form.component';
import { Observable } from 'rxjs';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})
export class DevicesComponent implements OnInit {
  totalData: number;
  devices: Device[] = [];
  config: Array<CellAction>;
  displayedColumns: string[] = [...DEVICE_KEYS, 'action'];
  displayedColumnsHeader: { [key: string]: string } = DEVICE_DISPLAYED_COLUMNS;
  dataSource = new MatTableDataSource<Device>(this.devices);
  pageable: Pageable = { pageNumber: 1, pageSize: 12, property: '', direction: '' };
  constructor(private deviceService: DeviceService, private messageService: MessageService, private dialog: MatDialog) {
    console.log(Object.keys(<Device>{}))
    this.config = [
      {
        name: 'Edit',
        icon: 'edit',
      },
      {
        name: 'Delete',
        icon: 'delete',
      },
    ];
  }
  ngOnInit(): void {
  }


  onAction(event: { id: string, nameAction: string }) {
    if (event.nameAction === 'Edit') {
      this.edit(event.id);
    }
    if (event.nameAction === 'Delete') {
      this.delete(event.id);
    }
  }

  edit(id: string) {
    this.deviceService.getDeviceById(id).subscribe(
      (data) => {
        console.log(data)
        const device = { ...data.body, id: id };
        const dialogRef = this.dialog.open(DeviceFormComponent, {
          width: '400px',
          data: device
        });
        dialogRef.afterClosed().subscribe(result => {
          if (result) {
            console.log(result);
            this.loadData();
          }
        });
      }
    )
  }

  assignDevice() {

  }
   openConfirmDialog(): Observable<boolean> {
        const dialogRef = this.dialog.open(ConfirmDialogComponent, {
          width: '350px',
          data: { message: 'Bạn có chắc chắn muốn thực hiện hành động này?' }
        });
        return dialogRef.afterClosed()
      }

  delete(id: string) {
    this.openConfirmDialog().subscribe(
      data => {
        if(data) {
          this.deviceService.deleteDevice(id).subscribe(
            (data) => {
              this.messageService.addMessage({ message: data.body, status: true });
              this.loadData();
            }, (error) => {
              this.messageService.addMessage({ message: error.message, status: false });
            }
          )
        }
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
    this.deviceService.getDevices(this.pageable, '').subscribe(
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
    this.pageable.pageNumber = 1;
    this.deviceService.getDevices(this.pageable, value).subscribe(
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

  onAdd() {

    const dialogRef = this.dialog.open(DeviceFormComponent, {
      width: '400px',
      data: null
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log('Device mới:', result);
        this.loadData();
      }
    });
  }

  onRefresh() {
    this.loadData();
  }


}
