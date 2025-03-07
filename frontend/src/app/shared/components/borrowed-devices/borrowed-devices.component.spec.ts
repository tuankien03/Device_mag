import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BorrowedDevicesComponent } from './borrowed-devices.component';

describe('BorrowedDevicesComponent', () => {
  let component: BorrowedDevicesComponent;
  let fixture: ComponentFixture<BorrowedDevicesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BorrowedDevicesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BorrowedDevicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
