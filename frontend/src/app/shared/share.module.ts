import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatInputModule} from '@angular/material/input';
import { MessageComponent } from './components/message/message.component';
import { MessageItemComponent } from './components/message-item/message-item.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { DevicesComponent } from './components/devices/devices.component';
import { UserComponent } from './components/user/user.component';
import { TableComponent } from './components/table/table.component';
import { RouterModule } from '@angular/router'; // 
import {MatTableModule} from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { UserFormComponent } from './components/user-form/user-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTooltipModule } from '@angular/material/tooltip';
import { DeviceFormComponent } from './components/device-form/device-form.component';
import { BorrowedDevicesComponent } from './components/borrowed-devices/borrowed-devices.component';
import { AvailableDevicesComponent } from './components/available-devices/available-devices.component';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { MatMenuModule } from '@angular/material/menu';
import { NotfoudPageComponent } from './components/notfoud-page/notfoud-page.component';
import { RedirectComponent } from './components/redirect/redirect.component';
import { ReturningDeviceComponent } from './components/returning-device/returning-device.component';
import { AssignmentFormComponent } from './components/assignment-form/assignment-form.component';






@NgModule({
  declarations: [MessageComponent, MessageItemComponent , SidebarComponent, DashboardComponent, DevicesComponent, UserComponent, TableComponent, UserFormComponent, DeviceFormComponent  , BorrowedDevicesComponent, AvailableDevicesComponent, ConfirmDialogComponent, NotfoudPageComponent, RedirectComponent, ReturningDeviceComponent, AssignmentFormComponent],
  imports: [
    CommonModule,MatInputModule,
    MatCardModule,
    MatFormFieldModule,
    MatButtonModule,
    MatSelectModule,
    MatSidenavModule,
    MatIconModule,
    MatToolbarModule,
    RouterModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatDialogModule,      
    MatFormFieldModule,  
    MatInputModule,       
    MatButtonModule,     
    ReactiveFormsModule,
    MatTooltipModule,
    MatMenuModule
  ],
  exports: [MessageComponent, MessageItemComponent,SidebarComponent,UserComponent,DevicesComponent,UserFormComponent]
})
export class ShareModule { }
