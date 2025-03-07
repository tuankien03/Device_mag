import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'src/app/shared/service/message.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder,private messageService: MessageService,private authService: AuthService,private router: Router) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    
  }
  
  onSubmit() {
    if(this.loginForm.valid){
      console.log(this.loginForm.value);
      this.authService.login(this.loginForm.value.username, this.loginForm.value.password).subscribe(
        (data) => {
          if(data.status){
            this.messageService.addMessage({message: "Login success", status: true});
            this.router.navigate(['/']);
          }
        },
        (error) => {
          this.messageService.addMessage({message: error.message, status: false});
        }
      )
    } else {
      console.log(this.loginForm.value);
      this.messageService.addMessage({message: "Form is invalid", status: false});
      console.log('Form is invalid');
    }
  }



}
