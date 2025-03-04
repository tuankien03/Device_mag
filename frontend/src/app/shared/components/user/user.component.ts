import { MatTableDataSource } from '@angular/material/table';
import { UserService } from '../../service/user.service';
import { User } from '../../model/user';
import { Component } from '@angular/core';
import { CellAction } from '../../model/cellaction';
import { MessageService } from '../../service/message.service';


const ELEMENT_DATA: User[] = [
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H', },
];

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent{
  users: User[] = [];
  config: Array<CellAction>;
  displayedColumns: string[] = ['id', 'username', 'role', 'createdAt', 'updatedAt', 'action'];
  dataSource = new MatTableDataSource<User>(this.users);
  constructor(private userService: UserService,private messageService: MessageService) {
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
    this.loadData();
  }

  onAction(event: {id: string, nameAction: string}) {
    if(event.nameAction === 'Edit') {
      this.edit(event.id);
    }
    if(event.nameAction === 'Delete') {
      this.delete(event.id);
    }
  }

  edit(id: string){
    console.log(id)
  }

  delete(id: string){
    console.log(id)
    this.userService.deleteUser(id).subscribe(
      (data) => {
        this.messageService.addMessage({message: data.body, status: true});
        this.loadData();
      }, (error) => {
        this.messageService.addMessage({message: error.message, status: false});
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
    this.userService.getUsers().subscribe(
      (data) => {
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
        this.dataSource.data = this.users;
      }
    )
  }

  onSearch(value: string) {
    this.userService.searchUserByUsername(value, 1, 10, null, null).subscribe(
      (data) => {
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
        this.dataSource.data = this.users;
      }
    )
  }

  onAdd() {
    console.log('Add button clicked từ Toolbar');
  }

  onRefresh() {
    this.loadData();
  }

}
