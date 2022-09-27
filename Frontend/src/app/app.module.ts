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
import {MyProfileComponent} from './content/customer/my-profile/my-profile.component';
import {QRCodeModule} from 'angular2-qrcode';
import {FuelAvailabilityComponent} from './content/fuel-station/fuel-availability/fuel-availability.component';
import {AttendanceFuelTableComponent} from './content/fuel-station/attendance-fuel-table/attendance-fuel-table.component';
import {StockOutAdminComponent} from './content/admin/stock-out-admin/stock-out-admin.component';
import {StockInAdminComponent} from './content/admin/stock-in-admin/stock-in-admin.component';
import {FuelAvailabilityAdminComponent} from './content/admin/fuel-availability-admin/fuel-availability-admin.component';
import {QrScanComponent} from './qr-scan/qr-scan.component';
import {ZXingScannerModule} from "@zxing/ngx-scanner";
import {MarkAttendanceComponent} from './content/fuel_pumper/mark-attendance/mark-attendance.component';
import {UpdateQuotaComponent} from './content/fuel_pumper/update-quota/update-quota.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { UpdateProfileComponent } from './content/customer/update-profile/update-profile.component';
import { RegisterPumperComponent } from './content/fuel-station/register-pumper/register-pumper.component';
import {DatePipe} from "@angular/common";
import { RegisterFuelAdminComponent } from './register/register-fuel-admin/register-fuel-admin.component';
import { ReportsComponent } from './content/fuel-station/reports/reports.component';
import { OrderComponent } from './content/fuel-station/order/order.component';
import {ScrollingModule} from "@angular/cdk/scrolling";

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
    MyProfileComponent,
    FuelAvailabilityComponent,
    AttendanceFuelTableComponent,
    StockOutAdminComponent,
    StockInAdminComponent,
    FuelAvailabilityAdminComponent,
    QrScanComponent,
    MarkAttendanceComponent,
    UpdateQuotaComponent,
    UpdateProfileComponent,
    RegisterPumperComponent,
    RegisterFuelAdminComponent,
    ReportsComponent,
    OrderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QRCodeModule,
    ZXingScannerModule,
    HttpClientModule,
    FormsModule,
    ScrollingModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
