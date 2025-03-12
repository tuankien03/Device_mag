import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeUserinforFormComponent } from './change-userinfor-form.component';

describe('ChangeUserinforFormComponent', () => {
  let component: ChangeUserinforFormComponent;
  let fixture: ComponentFixture<ChangeUserinforFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeUserinforFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeUserinforFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
