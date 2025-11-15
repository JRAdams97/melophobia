import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Hello } from './hello';

describe('Hello', () => {
  let component: Hello;
  let fixture: ComponentFixture<Hello>;
  let service: Hello;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Hello]
    })
    .compileComponents();

    service = TestBed.inject(Hello);
    fixture = TestBed.createComponent(Hello);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
    expect(service).toBeTruthy();
  });
});
