import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { UserService } from '../../service/user.service';
import { MatDialog } from '@angular/material/dialog';
import { UserFormComponent } from '../user-form/user-form.component';
import { ChangeUserinforFormComponent } from '../change-userinfor-form/change-userinfor-form.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  role: string = this.authService.getRole();

  constructor(public authService: AuthService,private userService: UserService,private dialog: MatDialog) { }

  ngOnInit(): void {
    console.log(this.role)
  }

  openUserInfor() {
    const userId = this.authService.getUserId();
    this.userService.getUserById(userId).subscribe(
          (data) => {
            console.log(data)
            const user = { ...data.body, id: userId };
            const dialogRef = this.dialog.open(ChangeUserinforFormComponent, {
              width: '400px',
              data: user 
            });
            dialogRef.afterClosed().subscribe(result => {
              if (result) {
               console.log(result)
              }
            });
          }
        )
  }

}
