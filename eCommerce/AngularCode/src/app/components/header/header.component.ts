import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs/operators';
import { ApiCallService } from 'src/app/services/api-call.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  currentPath: any
  userInfo: any;
  currentUser: any;
  cartItemsCount = 0;

  constructor(private router: Router, private apiCallService: ApiCallService) { }

  ngOnInit(): void {
    this.router.events.pipe(
      filter((event: any) => event instanceof NavigationEnd)
    ).subscribe(x => {
      this.currentPath = x.url
    })

    let auth;
    if (sessionStorage.getItem('currentUser')) {
      this.currentUser = sessionStorage.getItem('currentUser');
      this.userInfo = JSON.parse(this.currentUser).user;
      console.log(this.userInfo)
      this.apiCallService.getIsLoggedIn().subscribe((res: any) => {
        this.cartItemsCount = res?.iteam?.length
      })
      this.getAllCartItems(this.userInfo?.userId)
    }
  }

  cartIconClicked() {
    this.router.navigate(['cart'])
  }

  navigateToHome() {
    this.router.navigate(['home'])
  }

  logout() {
    window.sessionStorage.clear();
    this.router.navigate(['welcome'])
  }

  goToOrders() {
    this.router.navigate(['order'])
  }

  getAllCartItems(userID: any) {
    this.apiCallService.getCartItemsforUser(userID).subscribe((res: any) => {
      this.cartItemsCount = res.iteam.length
    },
      (error: any) => {
        console.log(error.error.message)
      })
  }
}
