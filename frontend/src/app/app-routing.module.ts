import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomepageComponent } from './shared/components/homepage/homepage.component';
import { LoginComponent } from './auth/login/login.component';
import { DashboardComponent } from './shared/components/dashboard/dashboard.component';
import { UserComponent } from './shared/components/user/user.component';
import { DevicesComponent } from './shared/components/devices/devices.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: 'dashboard',
    component: DashboardComponent,
    children: [
      {path: 'home', component: HomepageComponent},
      {path: 'user', component: UserComponent},
      {path: 'device', component: DevicesComponent},
     
    ],
    canActivate: [AuthGuard]
   },
  { path: 'login', component: LoginComponent },
  { path: '',   redirectTo: 'dashboard', pathMatch: 'full' }, // redirect to `first-component`
  { path: '**', component: HomepageComponent },
  { path: '**', component: UserComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
