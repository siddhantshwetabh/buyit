import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map } from 'rxjs';
import { Observable, throwError } from 'rxjs';
import { catchError, retry, switchMap } from 'rxjs/operators';

const configDataPath = 'assets/configs/config.json';

@Injectable({
  providedIn: 'root'
})

export class ApiCallService {

  baseURL: any;
  userURL: any;
  cartURL: any;
  orderURL: any;
  paymentURL:any;
  cartItems:any;

  private isLoggedInSource = new BehaviorSubject<any>(false);

  public _isLoggedIn: Observable<boolean> = this.isLoggedInSource.asObservable();

  setIsLoggedIn(logged: any) {
    this.isLoggedInSource.next(logged);
  }

  getIsLoggedIn() {
    return this._isLoggedIn;
  }


  constructor(private http: HttpClient) { }

  getAllProducts() {
    return this.http.get(configDataPath).pipe(
      switchMap((res: any) => {
        this.baseURL = res?.url;
        return this.http.get<String[]>(this.baseURL + 'product/getAllProduct');
      })
    );
  }

  getProductsByCategory(categoryID: number) {
    const params = new HttpParams()
      .set('categoryId', categoryID);
    return this.http.get(configDataPath).pipe(
      switchMap((res: any) => {
        this.baseURL = res?.url;
        return this.http.get<String[]>(this.baseURL + 'product/getAllProductsByCategory', { params: params });
      })
    );
  }

  getAllCategories() {
    return this.http.get(configDataPath).pipe(
      switchMap((res: any) => {
        this.baseURL = res?.url;
        return this.http.get<String[]>(this.baseURL + 'category/getAllCategories');
      })
    );
  }

  registerNewUser(body: any) {
    return this.http.get(configDataPath).pipe(
      switchMap((res: any) => {
        this.userURL = res?.userURL;
        return this.http.post<String[]>(
          this.userURL + 'registerNewUser',
          body
        );
      })
    );
  }

  loginExistingUser(email: any, password: any) {
    const params = new HttpParams()
      .set('emailId', email)
      .set('password', password);
    return this.http.get(configDataPath).pipe(
      switchMap((res: any) => {
        this.userURL = res?.userURL;
        return this.http.get<String[]>(this.userURL + 'loginUser', { params: params });
      })
    );
  }

  getCartItemsforUser(userID: number) {
    const params = new HttpParams()
      .set('userId', userID);
    return this.http.get(configDataPath).pipe(
      switchMap((res: any) => {
        this.cartURL = res?.cartURL;
        (this.http.get<String[]>(this.cartURL + 'cart/getCartByUserId', { params: params })).subscribe((res)=>{
          this.setIsLoggedIn(res)
        })        
        return this.http.get<String[]>(this.cartURL + 'cart/getCartByUserId', { params: params });
      })
    );
  }

  additemToCartForUser(userID: any, productId: any) {
    const params = new HttpParams()
      .set('userId', userID)
      .set('productId', productId)
      .set('quantity', 1);
    return this.http.get(configDataPath).pipe(
      switchMap((res: any) => {
        this.cartURL = res?.cartURL;
        return this.http.post<String[]>(this.cartURL + 'cart/addToCart', null, { params: params });
      })
    );

  }

  removeItemFromCartForUser(userID: any, productId: any) {
    const params = new HttpParams()
      .set('userId', userID)
      .set('productId', productId);
    return this.http.get(configDataPath).pipe(
      switchMap((res: any) => {
        this.cartURL = res?.cartURL;
        return this.http.delete<String[]>(this.cartURL + 'cart/removeProductByProductId', { params: params });
      })
    );
  }

  getOrdersforUser(userID: number) {
    const params = new HttpParams()
      .set('userId', userID);
    return this.http.get(configDataPath).pipe(
      switchMap((res: any) => {
        this.orderURL = res?.orderURL;
        return this.http.get<String[]>(this.orderURL + 'order/getOrderByUserId', { params: params });
      })
    );
  }

  createOrderForUserForThisCart(body: any) {
    return this.http.get(configDataPath).pipe(
      switchMap((res: any) => {
        this.orderURL = res?.orderURL;
        return this.http.post<String[]>(
          this.orderURL + 'order/createOrderByUserId',
          body
        );
      })
    );
  }

  deleteOrderByOrderID(orderID: any) {
    const params = new HttpParams()
      .set('orderId', orderID);
    return this.http.get(configDataPath).pipe(
      switchMap((res: any) => {
        this.orderURL = res?.orderURL;
        return this.http.delete<String[]>(this.orderURL + 'order/deleteOrderById', { params: params });
      })
    );
  }

  paymentByOrderId(body:any){
    return this.http.get(configDataPath).pipe(
      switchMap((res: any) => {
        this.paymentURL = res?.paymentURL;
        return this.http.put<String[]>(
          this.paymentURL + 'payment/payment',
          body
        );
      })
    );
  }
}
