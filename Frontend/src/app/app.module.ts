import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {FooterComponent} from './footer/footer.component';
import {HeaderComponent} from './header/header.component';
import {ContentComponent} from './content/content.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {RegisterCustomerComponent} from './register/register-customer/register-customer.component';
import {RegisterStationComponent} from './register/register-station/register-station.component';
import {MyProfileComponent} from './content/app-user/my-profile/my-profile.component';
import {QRCodeModule} from 'angular2-qrcode';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HeaderComponent,
    ContentComponent,
    LoginComponent,
    RegisterComponent,
    RegisterCustomerComponent,
    RegisterStationComponent,
    MyProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QRCodeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
