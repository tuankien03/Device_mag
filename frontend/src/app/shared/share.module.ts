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
import { HomepageComponent } from './components/homepage/homepage.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { DevicesComponent } from './components/devices/devices.component';
import { UserComponent } from './components/user/user.component';
import { TableComponent } from './components/table/table.component';
import { RouterModule } from '@angular/router'; // 
import {MatTableModule} from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';





@NgModule({
  declarations: [MessageComponent, MessageItemComponent , SidebarComponent, HomepageComponent, DashboardComponent, DevicesComponent, UserComponent, TableComponent],
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
    MatPaginatorModule
  ],
  exports: [MessageComponent, MessageItemComponent,SidebarComponent,HomepageComponent,UserComponent,DevicesComponent]
})
export class ShareModule { }
