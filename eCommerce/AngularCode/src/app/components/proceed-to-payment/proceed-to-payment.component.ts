import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ApiCallService } from 'src/app/services/api-call.service';
import { DisplayResponseComponent } from '../display-response/display-response.component';

@Component({
  selector: 'app-proceed-to-payment',
  templateUrl: './proceed-to-payment.component.html',
  styleUrls: ['./proceed-to-payment.component.scss']
})
export class ProceedToPaymentComponent {

  paymentForm: FormGroup;
  paymentBody = {
    "cardNumber": "1212121212121218",
    "expirationMonth": "03",
    "getExpirationYear": "2024",
    "amount": 176997,
    "orderId": 2,
    "code": 234
  }
  numbers: any;
  years: any;

  constructor(private formBuilder: FormBuilder, public dialog: MatDialog, private apiCallService: ApiCallService,
    public dialogRef: MatDialogRef<ProceedToPaymentComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) {

    this.numbers = Array(12).fill(0).map((x, i) => i);
    this.years = [2024, 2025, 2026, 2027
      , 2028];

    this.paymentForm = this.formBuilder.group({
      cardNumber: ['', [Validators.required, Validators.pattern('^[0-9]*$'), Validators.minLength(16), Validators.maxLength(16)]],
      expiryMonth: ['', [Validators.required]],
      expiryYear: ['', [Validators.required,]],
      cvv: ['', [Validators.required, Validators.pattern('^[0-9]*$'), Validators.minLength(3), Validators.maxLength(3)]],
    })

  }

  payNow() {
    if (this.paymentForm.valid) {
      this.apiCallService.createOrderForUserForThisCart(this.data).subscribe((res: any) => {
        console.log(res)
        this.paymentBody.cardNumber = this.paymentForm.controls['cardNumber'].value;
        this.paymentBody.expirationMonth = this.paymentForm.controls['expiryMonth'].value;
        this.paymentBody.getExpirationYear = this.paymentForm.controls['expiryYear'].value;
        this.paymentBody.code = this.paymentForm.controls['cvv'].value;
        this.paymentBody.amount = res.orderAmount;
        this.paymentBody.orderId = res.orderId;
        
        this.apiCallService.paymentByOrderId(this.paymentBody).subscribe((res: any) => {
          console.log(res)
          this.openDialog("Payment Successful, Your Order ID is "+ res.orderId)
        },
          (error: any) => {
            console.log(error.error.message)
            this.openDialog("Could Not process Payment" + error.error.message)
          })
      },
        (error: any) => {
          console.log(error.error.message)
          this.openDialog("Error! We Couldnot place your order")
        }
      )
    }
    else {
      this.paymentForm.markAllAsTouched();
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  openDialog(data: any): void {
    const dialogReff = this.dialog.open(DisplayResponseComponent, {
      width: '750px',
      data: data,
    });
    dialogReff.afterClosed().subscribe((result: any) => {
      this.paymentForm.reset();
      this.dialogRef.close('success');
    });
  }
}
