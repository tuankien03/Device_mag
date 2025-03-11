import { Component, OnInit } from '@angular/core';
import { UserDevice, USERDEVICE_KEYS } from '../../model/userdevice';
import { CellAction } from '../../model/cellaction';
import { MatTableDataSource } from '@angular/material/table';
import { Pageable } from '../../model/pageable';
import { UserService } from '../../service/user.service';
import { MessageService } from '../../service/message.service';
import { MatDialog } from '@angular/material/dialog';
import { Device } from '../../model/device';
import { Observable } from 'rxjs';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { DeviceFormComponent } from '../device-form/device-form.component';
import { AssignmentService } from '../../service/assignment.service';

@Component({
  selector: 'app-assigned-devices',
  templateUrl: './assigned-devices.component.html',
  styleUrls: ['./assigned-devices.component.css']
})
export class AssignedDevicesComponent implements OnInit {

 
   totalData: number;
   userDevices: UserDevice[] = [];
   config: Array<CellAction>;
   displayedColumns: string[] = [...USERDEVICE_KEYS, 'action'];
   dataSource = new MatTableDataSource<UserDevice>(this.userDevices);
   pageable: Pageable = { pageNumber: 1, pageSize: 12, property: '', direction: '' };
   constructor(private userService: UserService, private messageService: MessageService, private dialog: MatDialog, private assignmentService: AssignmentService) {
     console.log(Object.keys(<Device>{}))
     this.config = [
       {
         name: 'Delete',
         icon: 'delete',
       },
     ];
   }
   ngOnInit(): void {
   }
 
 
   onAction(event: { id: string, nameAction: string }) {
     if (event.nameAction === 'Delete') {
      this.openConfirmDialog().subscribe(
        confirmed => {
          if (confirmed) {
            this.delete(event.id);
          }
        }
      )
     }
   }
 
   
 
   delete(id: string) {
    this.assignmentService.deleteAssignment(id).subscribe(
      (data) => {
        this.messageService.addMessage({ message: data.body, status: true });
        this.loadData();
      }, (error) => {
        console.log(error)
        this.messageService.addMessage({ message: error.message, status: false });
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
     this.assignmentService.getAllCurrentAssignedDevice(this.pageable).subscribe(
       (data) => {
         this.totalData = data.body.totalElements;
         this.userDevices = [];
         console.log(data.body.data)
         data.body.data.forEach((element: any) => {
           this.userDevices.push({
             id: element.id,
             deviceName: element.deviceName,
             descriptionDevice: element.descriptionDevice,
             statusDevice: element.statusDevice,
             assignedAt: this.formatDate(element.assignedAt),
             returnedAt: this.formatDate(element.returnedAt) ,
             userId: element.userId,
             username: element.username,
             role: element.role,
             deviceId: element.deviceId
           });
         });
         this.dataSource = new MatTableDataSource<UserDevice>(this.userDevices);
       }
     )
   }
 
   onPageChange(pageable: Pageable) {
     this.pageable = pageable;
     this.loadData();
   }
   onSearch(value: string) {
 
   }
 
   openConfirmDialog(): Observable<boolean> {
     const dialogRef = this.dialog.open(ConfirmDialogComponent, {
       width: '350px',
       data: { message: 'Bạn có chắc chắn muốn thực hiện hành động này?' }
     });
     return dialogRef.afterClosed()
   }
 
   onAdd() {
     const dialogRef = this.dialog.open(DeviceFormComponent, {
       width: '400px',
       data: null
     });
 
     dialogRef.afterClosed().subscribe(result => {
       if (result) {
         console.log('Device mới:', result);
       }
     });
   }
 
   onRefresh() {
     this.loadData();
   }

}
