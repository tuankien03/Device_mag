import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-redirect',
  templateUrl: './redirect.component.html',
  styleUrls: ['./redirect.component.css']
})
export class RedirectComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    const role = this.authService.getRole(); // Lấy role của user
    if (role === 'ADMIN') {
      this.router.navigate(['/dashboard/user']);
    } else {
      this.router.navigate(['/dashboard/available-devices']);
    }
  }
}
