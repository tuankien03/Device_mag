import { MatTableDataSource } from '@angular/material/table';
import { UserService } from '../../service/user.service';
import { User } from '../../model/user';
import { Component } from '@angular/core';


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
  displayedColumns: string[] = ['id', 'username', 'role', 'createdAt', 'updatedAt', 'action'];
  dataSource = new MatTableDataSource<User>(this.users);
  constructor(private userService: UserService) { }
  ngOnInit(): void {
    this.loadData();
  }
  formatDate(dateString: string): string {
    if (!dateString) return ''; // Nếu ngày không có dữ liệu, trả về chuỗi rỗng
    const date = new Date(dateString);
    const day = date.getDate().toString().padStart(2, '0'); // Định dạng 2 chữ số
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Tháng bắt đầu từ 0 nên +1
    const year = date.getFullYear();
    const time = date.toLocaleTimeString();
    return `${day}/${month}/${year} - ${time}`;
  }

  loadData(): void {
    this.userService.getUsers().subscribe(
      (data) => {
        data.body.data.forEach((element: any) => {
          this.users.push({
            id: element.id,
            username: element.username,
            role: element.role,
            createdAt: this.formatDate(element.createdAt), // Format ngày tháng
            updatedAt: this.formatDate(element.updatedAt)
          });
        });
        this.dataSource.data = this.users;
        console.log(this.users);
      }
    )
  }

}
