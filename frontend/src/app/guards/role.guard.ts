import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../shared/components/confirm-dialog/confirm-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router, private dialog: MatDialog) {}
 openConfirmDialog(): Observable<boolean> {
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        width: '350px',
        data: { message: 'Bạn không vào trang này muốn sử dụng tài khoản khác không?'}
      });
      return dialogRef.afterClosed()
    }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      const expectedRole = route.data['role'];
    if (!this.authService.hasRole(expectedRole)) {
     this.openConfirmDialog().subscribe(
        data => {
          if(data) {
            this.authService.logout();
          } else {
            this.router.navigate(['/dashboard']);
          }
        }
     );
      return false;
    }
    return true;
  }
  
}
