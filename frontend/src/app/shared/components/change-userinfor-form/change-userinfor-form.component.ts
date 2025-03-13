import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { UserService } from '../../service/user.service';
import { UserFormComponent } from '../user-form/user-form.component';
import { MessageService } from '../../service/message.service';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-change-userinfor-form',
  templateUrl: './change-userinfor-form.component.html',
  styleUrls: ['./change-userinfor-form.component.css']
})
export class ChangeUserinforFormComponent implements OnInit {
 userForm: FormGroup;
  isEditMode: boolean = true;
  

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
      oldPassword: ['', this.isEditMode ? [] : [Validators.required, Validators.minLength(6)]],
      newPassword: ['', this.isEditMode ? [] : [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if (this.userForm.valid) {
      const  result = this.userForm.getRawValue();
      console.log(result)
      if (this.data) {
        this.userService.changePassword(this.data.id,  {oldPassword: result.oldPassword ,newPassword: result.newPassword}).subscribe(
          (data) => {
            console.log(result)
            this.messageService.addMessage({message: "Đổi mật khẩu thành công", status: true});
            this.dialogRef.close(); 
          }, (error) => {
            console.log(error)
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
