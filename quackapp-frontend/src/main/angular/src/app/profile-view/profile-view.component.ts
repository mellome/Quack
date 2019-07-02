import { Component, OnInit } from '@angular/core';
import { Profile } from './Profile';
import {QuackRService} from "../quackr.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-profile-view',
  templateUrl: './profile-view.component.html',
  styleUrls: ['./profile-view.component.css'],
  providers: [QuackRService]
})
export class ProfileViewComponent implements OnInit {

  profile: Profile;

  constructor(private route: ActivatedRoute, protected qService: QuackRService) {
      this.route.queryParams.subscribe(params => {
          let id = +params['id'] || 0;
          if (id > 0) {
              this.loadProfile(id);
          }
      });
  }

  ngOnInit() {

  }

  loadProfile(id: number): void {
      this.qService.getUser(id).subscribe(
          user => this.profile = Profile.fromUser(user),
          console.error
      );
  }

}
