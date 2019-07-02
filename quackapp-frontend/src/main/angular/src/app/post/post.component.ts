import { Component, OnInit } from '@angular/core';
import {QuackRService} from "../quackr.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
    title: string;
    text: string;

    constructor(private qService: QuackRService, private router: Router) { }

  ngOnInit() {
  }

    quack(e: Event) {
        e.preventDefault();
        this.qService.createQuack(this.title, this.text).subscribe(
            () => this.router.navigate(['/']),
            () => {}
        )
    }
}
