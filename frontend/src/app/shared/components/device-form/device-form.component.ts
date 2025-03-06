import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Device } from '../../model/device';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-device-form',
  templateUrl: './device-form.component.html',
  styleUrls: ['./device-form.component.css']
})
export class DeviceFormComponent implements OnInit {
  deviceForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<DeviceFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Device
  ) {}

  ngOnInit() {
    this.deviceForm = this.fb.group({
      name: [this.data?.name || '', Validators.required],
      description: [this.data?.description || ''],
      status: [{ value: this.data?.status || "AVAILABLE", disabled: true }, Validators.required]
    });
  }

  onSubmit() {
    if (this.deviceForm.valid) {
      
      this.dialogRef.close(this.deviceForm.getRawValue());
    }
  }

  onCancel() {
    this.dialogRef.close();
  }

}
