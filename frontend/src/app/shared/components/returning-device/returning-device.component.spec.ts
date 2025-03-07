import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturningDeviceComponent } from './returning-device.component';

describe('ReturningDeviceComponent', () => {
  let component: ReturningDeviceComponent;
  let fixture: ComponentFixture<ReturningDeviceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReturningDeviceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReturningDeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
