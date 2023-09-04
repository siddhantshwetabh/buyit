import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ApiCallService } from 'src/app/services/api-call.service';
import { ConfimationPopUpComponent } from '../confimation-pop-up/confimation-pop-up.component';
import { DisplayResponseComponent } from '../display-response/display-response.component';
import { PaymentComponent } from '../payment/payment.component';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent {

  currentUser: any;
  userInfo: any;
  panelOpenState = false;
  orders:any;

  constructor(private router: Router, private apiCallService: ApiCallService, public dialog: MatDialog) {
  }
  
  ngOnInit(): void {
    if (!sessionStorage.getItem('currentUser')) {
      this.router.navigate(['welcome'])
    }
    let auth;
    if (sessionStorage.getItem('currentUser')) {
      this.currentUser = sessionStorage.getItem('currentUser');
      this.userInfo = JSON.parse(this.currentUser).user;
      this.getOrdersforUser(this.userInfo.userId);
    }
  }

  getOrdersforUser(userID:any) {
    this.apiCallService.getOrdersforUser(userID).subscribe((res:any)=>{
      console.log(res)
      this.orders = res
    },
    (error:any)=>{
      console.log(error.error.message)
    })
  }

  productClicked(product:any){
    sessionStorage.setItem('product', JSON.stringify({ product: product }));
    this.router.navigate(['product']);
  }

  payNow(order:any){
    this.openPaymentDialog(order);
  }
  
  openPaymentDialog(data: any) {
    const dialogRef = this.dialog.open(PaymentComponent, {
      width: '650px',
      data: data,
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      this.getOrdersforUser(this.userInfo.userId);
    });
  }

  cancelOrder(orderID:any){
    this.apiCallService.deleteOrderByOrderID(orderID).subscribe((res:any)=>{
      console.log(res)
      this.openDialog("Order Deleted");
    },
    (error:any)=>{
      console.log(error.error.message)
      this.openDialog("Error ! Could Not delete your order !" +error.error.message)
    })
  }

  openConfirmationDialog(data: any,orderID: any): void {
    const dialogRef = this.dialog.open(ConfimationPopUpComponent, {
      width: 'auto',
      data: data,
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      if(result === 'Confirm'){
        this.cancelOrder(orderID);
      }
      else
      {

      }
    });
  }

  openDialog(data: any): void {
    const dialogRef = this.dialog.open(DisplayResponseComponent, {
      width: '650px',
      data: data,
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      this.getOrdersforUser(this.userInfo.userId);
    });
  }
}
