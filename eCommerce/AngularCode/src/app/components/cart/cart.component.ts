import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ApiCallService } from 'src/app/services/api-call.service';
import { ConfimationPopUpComponent } from '../confimation-pop-up/confimation-pop-up.component';
import { DisplayResponseComponent } from '../display-response/display-response.component';
import { PaymentComponent } from '../payment/payment.component';
import { ProceedToPaymentComponent } from '../proceed-to-payment/proceed-to-payment.component';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})

export class CartComponent {

  // numbers: any
  product: any;
  productString: any;
  // myDate = Date;
  // date: any;
  userInfo: any;
  currentUser: any;
  cartItems: any;
  AddressGroup: FormGroup;
  cartID: any

  body = {
    "address": "",
    "cartID": 1,
    "userId": 10
  }

  constructor(private formBuilder: FormBuilder, private apiCallService: ApiCallService, private datePipe: DatePipe, private router: Router, public dialog: MatDialog) {
    // this.numbers = Array(5).fill(0).map((x, i) => i);
    // this.date = new Date();
    // this.date.setDate(this.date.getDate() + 5);

    this.AddressGroup = this.formBuilder.group({
      addressLine1: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(50)]],
      addressLine2: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(50)]],
      pinCode: ['', [Validators.required, Validators.pattern('^[0-9]*$'), Validators.minLength(4), Validators.maxLength(6)]],
    })
  }

  ngOnInit() {
    if (!sessionStorage.getItem('currentUser')) {
      this.router.navigate(['welcome'])
    }
    if (sessionStorage.getItem('product')) {
      this.productString = sessionStorage.getItem('product');
      this.product = JSON.parse(this.productString).product;
    }
    console.log(this.product)
    let auth;
    if (sessionStorage.getItem('currentUser')) {
      this.currentUser = sessionStorage.getItem('currentUser');
      this.userInfo = JSON.parse(this.currentUser).user;
      console.log(this.userInfo.userId)
      this.getAllCartItems(this.userInfo.userId)
    }
  }

  getAllCartItems(userID: any) {
    this.apiCallService.getCartItemsforUser(userID).subscribe((res: any) => {
      this.cartItems = res.iteam;
      console.log(res)
      this.cartID = res.cartId;
    },
      (error: any) => {
        console.log(error?.error?.message)
      })
  }

  removeItemFromCart(productID: any) {
    this.apiCallService.removeItemFromCartForUser(this.userInfo.userId, productID).subscribe((res) => {
      console.log(res)
      this.openDialog("Item removed from your cart")
    },
      (error: any) => {
        this.openDialog("Item removed from your cart" + error.error.message);
      })
  }

  productClicked(product: any) {
    sessionStorage.setItem('product', JSON.stringify({ product: product }));
    this.router.navigate(['product']);
  }

  openDialog(data: any): void {
    const dialogRef = this.dialog.open(DisplayResponseComponent, {
      width: '650px',
      data: data,
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      this.getAllCartItems(this.userInfo.userId)
      this.AddressGroup.reset();
    });
  }

  openConfirmationDialog(data: any, productID: any): void {
    const dialogRef = this.dialog.open(ConfimationPopUpComponent, {
      width: 'auto',
      data: data,
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      if (result === 'Confirm') {
        this.removeItemFromCart(productID);
      }
      else {

      }
    });
  }

  placeOrder() {
    if (this.AddressGroup.valid) {
      this.body.address = this.AddressGroup.controls['addressLine1'].value + ', ' + this.AddressGroup.controls['addressLine2'].value + ', ' + this.AddressGroup.controls['pinCode'].value;
      this.body.cartID = this.cartID;
      this.body.userId = this.userInfo.userId;
      this.apiCallService.createOrderForUserForThisCart(this.body).subscribe((res: any) => {
        console.log(res)
        this.openDialog("Your Order is Placed, Your Order ID is " + res.orderId)
      },
        (error: any) => {
          console.log(error.error.message)
          this.openDialog("Error! We Couldnot place your order")
        }
      )
      // this.router.navigate(['order']);
    }
    else {
      this.AddressGroup.markAllAsTouched();
    }
  }

  payNow() {
    if (this.AddressGroup.valid) {
      this.body.address = this.AddressGroup.controls['addressLine1'].value + ', ' + this.AddressGroup.controls['addressLine2'].value + ', ' + this.AddressGroup.controls['pinCode'].value;
      this.body.cartID = this.cartID;
      this.body.userId = this.userInfo.userId;
      this.openProceedToPaymentDialog(this.body)
      // this.apiCallService.createOrderForUserForThisCart(this.body).subscribe((res:any)=>{
      //   console.log(res)
      //   // this.openDialog("Your Order is Placed, Your Order ID is "+ res.orderId)
      //   this.openPaymentDialog(res);
      // },
      // (error:any)=>{
      //   console.log(error.error.message)
      //   this.openDialog("Error! We Couldnot place your order")
      // }
      // )
      // this.router.navigate(['order']);
    }
    else {
      this.AddressGroup.markAllAsTouched();
    }
  }

  openPaymentDialog(data: any) {
    const dialogRef = this.dialog.open(PaymentComponent, {
      width: '650px',
      data: data,
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      this.openDialog("Your Order is Placed, Your Order ID is " + data.orderId)
    });
  }

  openProceedToPaymentDialog(data: any) {
    const dialogRef = this.dialog.open(ProceedToPaymentComponent, {
      width: '650px',
      data: data,
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      if (result === 'success') {
        this.getAllCartItems(this.userInfo.userId)
        this.AddressGroup.reset();
      }
      // this.openDialog("Your Order is Placed, Your Order ID is "+ data.orderId)
    });
  }
}
