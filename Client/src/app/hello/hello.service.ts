import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HelloService {

  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = 'http://localhost:5233/api/test';

  getHello(): Observable<string> {
    return this.http.get(this.apiUrl, { responseType: 'text' });
  }
}
