import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiCallService } from 'src/app/services/api-call.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  categories: any;
  allProducts: any;
  allProductsBackUp: any;
  search = "";

  constructor(private apiCallService: ApiCallService, private router: Router) {

  }

  ngOnInit(): void {
    if(!sessionStorage.getItem('currentUser')){
      this.router.navigate(['welcome'])
    }
    this.getAllCategories();
    this.getAllProducts();
  }

  getAllProducts() {
    this.apiCallService.getAllProducts().subscribe((res) => {
      this.allProducts = res;
      this.allProductsBackUp = res;
    })
  }

  getAllCategories() {
    this.apiCallService.getAllCategories().subscribe((res) => {
      this.categories = res;
    })
  }

  categoryClicked(category: any) {
    this.apiCallService.getProductsByCategory(category).subscribe((res) => {
      this.allProducts = res;
    })
  }

  productClicked(product: any) {
    sessionStorage.setItem('product', JSON.stringify({ product: product }));
    this.router.navigate(['product']);
  }

  shouldDisplayProduct(productName: string) {
    if (productName.toLowerCase().includes(this.search.toLowerCase())) {
      return true
    }
    else
      return false
  }
}
