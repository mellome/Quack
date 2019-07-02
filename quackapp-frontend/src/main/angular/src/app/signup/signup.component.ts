import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {QuackRService} from "../quackr.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
    providers: [QuackRService]
})
export class SignupComponent implements OnInit {

    public username = "";
    public email = "";
    public password = "";

  constructor(private qService: QuackRService, private router: Router) { }

  ngOnInit() {
  }

  signUp(e: Event) {
      e.preventDefault();
    this.qService.createUser(this.username, this.password, this.email).subscribe(
        () => this.router.navigate(['/login']),
        () => {}
    )
  }

}
