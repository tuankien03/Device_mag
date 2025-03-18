import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Device } from '../../model/device';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { DeviceService } from '../../service/device.service';
import { MessageService } from '../../service/message.service';

@Component({
  selector: 'app-device-form',
  templateUrl: './device-form.component.html',
  styleUrls: ['./device-form.component.css']
})
export class DeviceFormComponent implements OnInit {
  deviceForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private deviceService: DeviceService,
    private messageService: MessageService,
    public dialogRef: MatDialogRef<DeviceFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit() {
    this.deviceForm = this.fb.group({
      name: [this.data?.name || '', Validators.required],
      description: [this.data?.description || '', Validators.required],
      status: [{ value: this.data?.status || "AVAILABLE", disabled: true }, Validators.required]
    });
  }

  onSubmit() {
    if (this.deviceForm.valid) {
      this.dialogRef.close(this.deviceForm.getRawValue());
      if (this.data) {
        this.deviceService.updateDevice(this.data.id, this.deviceForm.getRawValue()).subscribe(
          (data) => {
            this.messageService.addMessage({ message: "Sửa thiết bị thành công", status: true });
          }, (error) => {
            this.messageService.addMessage({ message: error.message, status: false });
          }
        );
      } else {
        this.deviceService.createDevice(this.deviceForm.getRawValue()).subscribe(
          (data) => {
            this.messageService.addMessage({ message: "Tạo thiết bị thành công", status: true });
          }, (error) => {
            this.messageService.addMessage({ message: error.message, status: false });
          });
      }
    }
  }

  onCancel() {
    this.dialogRef.close();
  }

}
