import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProceedToPaymentComponent } from './proceed-to-payment.component';

describe('ProceedToPaymentComponent', () => {
  let component: ProceedToPaymentComponent;
  let fixture: ComponentFixture<ProceedToPaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProceedToPaymentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProceedToPaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
