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
      oldPassword: ['', [Validators.required, Validators.minLength(6)]],
      newPassword: ['', [Validators.required, Validators.minLength(6), Validators.pattern(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/)]],
      confirmPassword: ['',  [Validators.required, Validators.minLength(6)]]
    },  { validators: this.passwordMatchValidator });
  }
  passwordMatchValidator(form: FormGroup) {
    const newPassword = form.get('newPassword');
    const confirmPassword = form.get('confirmPassword');
  
    if (confirmPassword?.errors && !confirmPassword.errors['passwordMismatch']) {
      return; // Nếu đã có lỗi khác (VD: required, minlength) thì không kiểm tra tiếp
    }
  
    if (newPassword?.value !== confirmPassword?.value) {
      confirmPassword?.setErrors({ passwordMismatch: true });
    } else {
      confirmPassword?.setErrors(null);
    }
  }


  getNewPasswordError() {
    if (this.userForm.get('newPassword')?.hasError('required')) {
      return "Vui lòng nhập mật khẩu mới";
    }
    if (this.userForm.get('newPassword')?.hasError('minlength')) {
      return "Mật khẩu phải có ít nhất 6 ký tự";
    }
    if (this.userForm.get('newPassword')?.hasError('pattern')) {
      return "Phải chứa ít nhất 1 chữ hoa, 1 chữ thường, 1 số và 1 ký tự";
    }
    return "";
  }

  getConfirmPasswordError() {
    if (this.userForm.get('confirmPassword')?.hasError('required')) {
      return "Vui lòng nhập xác nhận mật khẩu";
    }
    if (this.userForm.get('confirmPassword')?.hasError('minlength')) {
      return "Mật khẩu phải có ít nhất 6 ký tự";
    }
    if (this.userForm.get('confirmPassword')?.hasError('passwordMismatch')) {
      return "Mật khẩu xác nhận không khớp";
    }
    return "";
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
            this.authService.logout();
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
