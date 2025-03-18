import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryDevicesOfUserComponent } from './history-devices-of-user.component';

describe('HistoryDevicesOfUserComponent', () => {
  let component: HistoryDevicesOfUserComponent;
  let fixture: ComponentFixture<HistoryDevicesOfUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryDevicesOfUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryDevicesOfUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
