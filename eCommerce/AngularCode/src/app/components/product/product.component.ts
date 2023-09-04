import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ApiCallService } from 'src/app/services/api-call.service';
import { DisplayResponseComponent } from '../display-response/display-response.component';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent {
  product: any;
  productString: any;
  // myDate = Date;
  // date: any;
  userInfo: any;
  currentUser: any;

  constructor(private apiCallService: ApiCallService, private datePipe: DatePipe, public dialog: MatDialog, private router: Router) {
    // this.date = new Date();
    // this.date.setDate(this.date.getDate() + 5);
  }

  ngOnInit() {
    if (!sessionStorage.getItem('currentUser')) {
      this.router.navigate(['welcome'])
    }
    // if (sessionStorage.getItem('product')) {
    //   this.router.navigate(['home'])
    // }
    if (sessionStorage.getItem('product')) {
      this.productString = sessionStorage.getItem('product');
      this.product = JSON.parse(this.productString).product;
    }
    console.log(this.product)
    let auth;
    if (sessionStorage.getItem('currentUser')) {
      this.currentUser = sessionStorage.getItem('currentUser');
      this.userInfo = JSON.parse(this.currentUser).user;
    }
  }

  addToCart() {
    this.apiCallService.additemToCartForUser(this.userInfo.userId, this.product.productId).subscribe((res: any) => {
      console.log(res)
      this.openDialog("Item Added To Cart")
    },
      (error: any) => {
        this.openDialog("Error Item not added to cart. " + error.error.message)
        console.log(error.error.message)
      })
  }

  openDialog(data: any): void {
    const dialogRef = this.dialog.open(DisplayResponseComponent, {
      width: '650px',
      data: data,
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      this.getAllCartItemsForHeader();
    });
  }

  getAllCartItemsForHeader() {
    this.apiCallService.getCartItemsforUser(this.userInfo.userId).subscribe((res: any) => {
      console.log(res)
    },
      (error: any) => {
        console.log(error?.error?.message)
      })
  }
}

