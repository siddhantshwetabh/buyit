import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { BodyComponent } from './components/body/body.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminComponent } from './components/admin/admin.component';
import { HttpClientModule } from '@angular/common/http';
import { ProductComponent } from './components/product/product.component';
import { DisplayResponseComponent } from './components/display-response/display-response.component';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { LoaderComponent } from './components/loader/loader.component';
import { CartComponent } from './components/cart/cart.component';
import { DatePipe } from '@angular/common';
import { OrderComponent } from './components/order/order.component';
import { PaymentComponent } from './components/payment/payment.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { ConfimationPopUpComponent } from './components/confimation-pop-up/confimation-pop-up.component';
import { ProceedToPaymentComponent } from './components/proceed-to-payment/proceed-to-payment.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    BodyComponent,
    WelcomeComponent,
    AdminComponent,
    ProductComponent,
    DisplayResponseComponent,
    LoaderComponent,
    CartComponent,
    OrderComponent,
    PaymentComponent,
    ConfimationPopUpComponent,
    ProceedToPaymentComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    MatDialogModule,
    MatMenuModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    HttpClientModule,
    MatSlideToggleModule,
    MatExpansionModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
