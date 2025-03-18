import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { DashboardComponent } from './shared/components/dashboard/dashboard.component';
import { UserComponent } from './shared/components/user/user.component';
import { DevicesComponent } from './shared/components/devices/devices.component';
import { AuthGuard } from './guards/auth.guard';
import { RoleGuard } from './guards/role.guard';
import { BorrowedDevicesComponent } from './shared/components/borrowed-devices/borrowed-devices.component';
import { AvailableDevicesComponent } from './shared/components/available-devices/available-devices.component';
import { NotfoudPageComponent } from './shared/components/notfoud-page/notfoud-page.component';
import { RedirectComponent } from './shared/components/redirect/redirect.component';
import { ReturningDeviceComponent } from './shared/components/returning-device/returning-device.component';
import { AssignedDevicesComponent } from './shared/components/assigned-devices/assigned-devices.component';
import { HistoryDevicesOfUserComponent } from './shared/components/history-devices-of-user/history-devices-of-user.component';

const routes: Routes = [
  { path: 'dashboard', component: RedirectComponent },
  { path: 'dashboard',
    component: DashboardComponent,
    children: [
      {path: 'user', component: UserComponent, canActivate: [RoleGuard], data: { role: 'ADMIN' }},
      {path: 'device', component: DevicesComponent, canActivate: [RoleGuard], data: { role: 'ADMIN'}},
      {path: 'returning-device', component: ReturningDeviceComponent, canActivate: [RoleGuard], data: { role: 'ADMIN'}},
      {path: 'available-devices', component: AvailableDevicesComponent, canActivate: [RoleGuard], data: { role: 'ADMIN'}},
      {path: 'current-assignment', component: AssignedDevicesComponent, canActivate: [RoleGuard], data: { role: 'ADMIN'}},
      {path: 'borrowed-devices', component: BorrowedDevicesComponent, canActivate: [RoleGuard], data: { role: 'USER'}},
      {path: 'history-devices', component: HistoryDevicesOfUserComponent, canActivate: [RoleGuard], data: { role: 'USER'}},

    ],
    canActivate: [AuthGuard]
   },
  { path: 'login', component: LoginComponent },
  { path: '', component: RedirectComponent },
  { path: '**', component: NotfoudPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
