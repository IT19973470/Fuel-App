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
import { VehicleDetailsComponent } from './content/fuel_pumper/vehicle-details/vehicle-details.component';
import {VehicleDetailsFilterPipe} from "./content/fuel_pumper/vehicle-details/vehicle-details-filter.pipe";
import {NgApexchartsModule} from "ng-apexcharts";
import { FuelReportComponent } from './content/fuel_pumper/fuel-report/fuel-report.component';
import { FuelAvailabilityCustomerComponent } from './content/customer/fuel-availability-customer/fuel-availability-customer.component';
import { ViewStockOutAdminComponent } from './content/admin/view-stock-out-admin/view-stock-out-admin.component';
import { FuelConsumptionCustomerComponent } from './content/customer/fuel-consumption-customer/fuel-consumption-customer.component';
import {NotifierModule, NotifierOptions} from "angular-notifier";

const customNotifierOptions: NotifierOptions = {
  position: {
    horizontal: {
      position: "middle",
      distance: 5
    },
    vertical: {
      position: "top",
      distance: 10,
      gap: 10
    }
  },
  theme: "material",
  behaviour: {
    autoHide: 5000,
    onClick: false,
    onMouseover: "pauseAutoHide",
    showDismissButton: false,
    stacking: 4
  },
  animations: {
    enabled: true,
    show: {
      preset: "slide",
      speed: 300,
      easing: "ease"
    },
    hide: {
      preset: "fade",
      speed: 300,
      easing: "ease",
      offset: 50
    },
    shift: {
      speed: 300,
      easing: "ease"
    },
    overlap: 150
  }
};

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
    OrderComponent,
    VehicleDetailsComponent,
    VehicleDetailsFilterPipe,
    FuelReportComponent,
    FuelAvailabilityCustomerComponent,
    ViewStockOutAdminComponent,
    FuelConsumptionCustomerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QRCodeModule,
    ZXingScannerModule,
    HttpClientModule,
    FormsModule,
    ScrollingModule,
    NgApexchartsModule,
    NotifierModule.withConfig(customNotifierOptions)
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
