import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { UserService } from '../../service/user.service';
import { User } from '../../model/user';


const ELEMENT_DATA: User[] = [
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
  {id: "1", username: 'Hydrogen', role: "user", createdAt: 'H',updatedAt: 'H'},
];

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent{


  displayedColumns: string[] = ['id', 'username', 'role', 'createdAt', 'updatedAt'];
  dataSource = new MatTableDataSource<User>(ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUsers().subscribe(
      (data) => {
        console.log(data);
      }
    )
  }

}
