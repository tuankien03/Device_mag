import { MatTableDataSource } from '@angular/material/table';
import { UserService } from '../../service/user.service';
import { User } from '../../model/user';
import { Component } from '@angular/core';
import { CellAction } from '../../model/cellaction';
import { MessageService } from '../../service/message.service';
import { Pageable } from '../../model/pageable';
import { MatDialog } from '@angular/material/dialog';
import { UserFormComponent } from '../user-form/user-form.component';
import { Observable } from 'rxjs';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  totalData: number;
  users: User[] = [];
  config: Array<CellAction>;
  displayedColumns: string[] = ['id', 'username', 'role', 'createdAt', 'updatedAt', 'action'];
  dataSource = new MatTableDataSource<User>(this.users);
  pageable: Pageable = { pageNumber: 1, pageSize: 12, property: '', direction: '' };
  searchText: string = '';
  constructor(private userService: UserService, private messageService: MessageService, private dialog: MatDialog) {
    this.config = [
      {
        name: 'Edit',
        icon: 'edit',
      },
      {
        name: 'Delete',
        icon: 'delete',
      }
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
    this.userService.getUserById(id).subscribe(
      (data) => {
        console.log(data)
        const user = { ...data.body, id: id };
        const dialogRef = this.dialog.open(UserFormComponent, {
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
          this.userService.deleteUser(id).subscribe(
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
    this.userService.getUsers(this.pageable, this.searchText).subscribe(
      (data) => {
        this.totalData = data.body.totalElements;
        this.users = [];
        data.body.data.forEach((element: any) => {
          this.users.push({
            id: element.id,
            username: element.username,
            role: element.role,
            createdAt: this.formatDate(element.createdAt),
            updatedAt: this.formatDate(element.updatedAt)
          });
        });
        this.dataSource = new MatTableDataSource<User>(this.users);
      }
    )
  }

  onPageChange(pageable: Pageable) {
    this.pageable = pageable;
    this.loadData();
  }
  onSearch(value: string) {
    this.searchText = value;
    this.pageable.pageNumber = 1;
    this.userService.getUsers(this.pageable, value).subscribe(
      (data) => {
        this.users = [];
        this.totalData = data.body.totalElements;
        data.body.data.forEach((element: any) => {
          this.users.push({
            id: element.id,
            username: element.username,
            role: element.role,
            createdAt: this.formatDate(element.createdAt),
            updatedAt: this.formatDate(element.updatedAt)
          });
        });
        this.dataSource.data = this.users;
      }
    )
  }

  onAdd() {
    const dialogRef = this.dialog.open(UserFormComponent, {
      width: '400px',
      data: null 
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadData();
        console.log('User mới:', result);
      }
    });
  }

  onRefresh() {
    this.loadData();
  }

}
