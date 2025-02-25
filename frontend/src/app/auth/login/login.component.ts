import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'src/app/shared/service/message.service';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder,private messageService: MessageService,private loginService: LoginService) {
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
      this.loginService.login(this.loginForm.value.username, this.loginForm.value.password).subscribe(
        (data) => {
          console.log(data);
        }
      )
    } else {
      console.log(this.loginForm.value);
      this.messageService.addMessage({message: "Form is invalid", status: false});
      console.log('Form is invalid');
    }
  }



}
