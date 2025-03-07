import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotfoudPageComponent } from './notfoud-page.component';

describe('NotfoudPageComponent', () => {
  let component: NotfoudPageComponent;
  let fixture: ComponentFixture<NotfoudPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotfoudPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NotfoudPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
