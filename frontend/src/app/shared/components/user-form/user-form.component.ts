import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { UserService } from '../../service/user.service';
import { MessageService } from '../../service/message.service';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  userForm: FormGroup;
  isEditMode: boolean = false;
  

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<UserFormComponent>,
    private userService: UserService,
    private messageService: MessageService,
    private authService: AuthService,
    @Inject(MAT_DIALOG_DATA) public data: any, 
  ) {}


  ngOnInit(): void {
    this.isEditMode = !!this.data;
    this.userForm = this.fb.group({
      username: [{ value: this.data?.username || '', disabled: !!this.data }, Validators.required],
      role: [{value: this.data?.role || 'USER', disabled: this.authService.getUserId() === this.data?.id}, Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if (this.userForm.valid) {
      const  result = this.userForm.getRawValue();
      if (this.data) {
        this.userService.updateUser(this.data.id, result).subscribe(
          (data) => {
            this.messageService.addMessage({message: "Sửa user thành công", status: true});
            this.dialogRef.close(data); 
          }, (error) => {
            console.log(error)
            this.messageService.addMessage({ message: error.message, status: false });
          }
        );
      } else {
        this.userService.createUser(result).subscribe(
          (data) => {
            this.messageService.addMessage({message: "Tạo user thành công", status: true});
            this.dialogRef.close(data); 
          }, (error) => {
            this.messageService.addMessage({ message: error.message, status: false });
          }
        );
      }
    }
  }

  onClose() {
    this.dialogRef.close();
  }

}
