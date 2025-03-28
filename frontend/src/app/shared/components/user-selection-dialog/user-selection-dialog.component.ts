import { Component, Inject, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { User, UserRole } from '../../model/user';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-user-selection-dialog',
  templateUrl: './user-selection-dialog.component.html',
  styleUrls: ['./user-selection-dialog.component.css']
})
export class UserSelectionDialogComponent implements OnInit {
  searchControl = new FormControl('');
  filteredUsers = this.data;
  selectedUser: any = null;

  constructor(
    public dialogRef: MatDialogRef<UserSelectionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService
  ) {}



  ngOnInit() {
    this.filteredUsers  = this.filterUsers();
    this.searchControl.valueChanges.subscribe(value => {
      this.userService.getUsers({ pageNumber: 1, pageSize: 10 }, value.trim()).pipe(
      ).subscribe((response) => {
        this.filteredUsers = response.body.data;
        this.filteredUsers  = this.filterUsers();
      }
      );
    });
  }

  selectUser(user: User) {
    this.selectedUser = user;
  }

  filterUsers(): User[] {
    return this.filteredUsers
      ? this.filteredUsers.filter((user: User) => user.role === UserRole.User)
      : [];
  }
  

  confirmSelection() {
    this.dialogRef.close(this.selectedUser);
  }

  closeDialog() {
    this.dialogRef.close();
  }

}
