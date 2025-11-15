import { Component, inject, OnInit } from '@angular/core';
import { HelloService } from './hello.service';

@Component({
  standalone: true,
  selector: 'app-hello',
  template: '<p>{{ message }}</p>'
})
export class Hello implements OnInit {

  private helloService: HelloService = inject(HelloService);
  message: string = '';

  ngOnInit(): void {
    this.helloService.getHello().subscribe(text => {
      this.message = text;
    });
  }
}
