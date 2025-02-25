import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { LoginComponent } from './login/login.component';
import {MatSelectModule} from '@angular/material/select';



@NgModule({
  declarations: [LoginComponent],
  imports: [
    MatCardModule,  // ✅ Đảm bảo MatCardModule được import
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatSelectModule
  ],
  exports: [LoginComponent]
})
export class AuthModule { }
