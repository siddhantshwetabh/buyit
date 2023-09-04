import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfimationPopUpComponent } from './confimation-pop-up.component';

describe('ConfimationPopUpComponent', () => {
  let component: ConfimationPopUpComponent;
  let fixture: ComponentFixture<ConfimationPopUpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfimationPopUpComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfimationPopUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
