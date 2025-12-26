import { Component } from '@angular/core';
import { NzBreadCrumbModule } from 'ng-zorro-antd/breadcrumb';
import { RouterLink, RouterOutlet } from '@angular/router';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { Hello } from './hello/hello';

@Component({
  selector: 'app-root',
  imports: [RouterLink, RouterOutlet, NzBreadCrumbModule, NzIconModule, NzLayoutModule, NzMenuModule, Hello],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  isCollapsed: boolean = false;
  date = new Date();
}
