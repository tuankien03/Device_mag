import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableDevicesComponent } from './available-devices.component';

describe('AvailableDevicesComponent', () => {
  let component: AvailableDevicesComponent;
  let fixture: ComponentFixture<AvailableDevicesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvailableDevicesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailableDevicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
