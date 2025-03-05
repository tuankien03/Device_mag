import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

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
    @Inject(MAT_DIALOG_DATA) public data: any 
  ) {}

  ngOnInit(): void {
    this.isEditMode = !!this.data;
    console.log(this.data)
    this.userForm = this.fb.group({
      username: [{ value: this.data?.username || '', disabled: !!this.data }, Validators.required],
      role: [this.data?.role || 'USER', Validators.required],
      password: ['', this.isEditMode ? [] : [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if (this.userForm.valid) {
      this.dialogRef.close(this.userForm.getRawValue()); 
    }
  }

  onClose() {
    this.dialogRef.close();
  }

}
