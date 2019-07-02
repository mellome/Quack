import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
    providers: [AuthenticationService]
})
export class LoginComponent implements OnInit {
    username: string;
    password: string;

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
  }

  login(e: Event) {
      e.preventDefault();
      this.authService.requestToken(this.username, this.password).subscribe(
          () => this.router.navigate(['/home']),
          () => {}
      );
  }

}
