import {NgModule} from "@angular/core"
import {RouterModule, Routes} from "@angular/router"
import {LoginComponent} from "./login/login.component"
import {UpdateProfileComponent} from "./content/customer/update-profile/update-profile.component"
import {MyProfileComponent} from "./content/customer/my-profile/my-profile.component"
import {UpdateQuotaComponent} from "./content/fuel_pumper/update-quota/update-quota.component"
import {RegisterComponent} from "./register/register.component"
import {RegisterCustomerComponent} from "./register/register-customer/register-customer.component"
import {RegisterStationComponent} from "./register/register-station/register-station.component"
import {MarkAttendanceComponent} from "./content/fuel_pumper/mark-attendance/mark-attendance.component"
import {RegisterPumperComponent} from "./content/fuel-station/register-pumper/register-pumper.component"
import {FuelAvailabilityComponent} from "./content/fuel-station/fuel-availability/fuel-availability.component"
import {AttendanceFuelTableComponent} from "./content/fuel-station/attendance-fuel-table/attendance-fuel-table.component"
import {RegisterFuelAdminComponent} from "./register/register-fuel-admin/register-fuel-admin.component"
import {FuelAvailabilityAdminComponent} from "./content/admin/view-fuel-in-admin/fuel-availability-admin.component"
import {StockInAdminComponent} from "./content/admin/stock-in-admin/stock-in-admin.component"
import {StockOutAdminComponent} from "./content/admin/stock-out-admin/stock-out-admin.component"
import {ReportsComponent} from "./content/fuel-station/reports/reports.component"
import {OrderComponent} from "./content/fuel-station/order/order.component"
import {VehicleDetailsComponent} from "./content/fuel_pumper/vehicle-details/vehicle-details.component"
import {FuelReportComponent} from "./content/fuel_pumper/fuel-report/fuel-report.component"
import {FuelAvailabilityCustomerComponent} from "./content/customer/fuel-availability-customer/fuel-availability-customer.component"
import {ViewStockOutAdminComponent} from "./content/admin/view-stock-out-admin/view-stock-out-admin.component"
import {VehicleReportComponent} from "./content/fuel-station/vehicle-report/vehicle-report.component"
import {FuelApproveComponent} from "./content/admin/fuel-approve/fuel-approve.component"
import {ViewReportComponent} from "./content/admin/view-report/view-report.component"
import {UpdateStockInComponent} from "./content/admin/update-stock-in/update-stock-in.component";
import {UpdateStockOutComponent} from "./content/admin/update-stock-out/update-stock-out.component";
import {FuelConsumptionCustomerComponent} from "./content/customer/fuel-consumption-customer/fuel-consumption-customer.component";
import {FuelReportCustomerComponent} from "./content/customer/fuel-report-customer/fuel-report-customer.component";


const routes: Routes = [
  {
    path: "",
    redirectTo: "login",
    pathMatch: "full"
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "mark_attendance",
    component: MarkAttendanceComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "fuel_availability",
    component: FuelAvailabilityComponent
  },
  {
    path: "attendance_fuel",
    component: AttendanceFuelTableComponent
  },
  {
    path: "register_pumper",
    component: RegisterPumperComponent
  },
  {
    path: "register_fuel_admin",
    component: RegisterFuelAdminComponent
  },
  { //customer
    path: "my_profile",
    component: MyProfileComponent
  },
  {
    path: "fuel_availability_customer",
    component: FuelAvailabilityCustomerComponent
  },
  {
    path: 'fuel_consumption_customer',
    component: FuelConsumptionCustomerComponent,
  },
  {
    path: 'update_profile',
    component: UpdateProfileComponent,
  },
  {
    path: "update_quota",
    component: UpdateQuotaComponent
  },
  {
    path: "fuel_availability_admin",
    component: FuelAvailabilityAdminComponent
  },
  {
    path: "fuel_report_customer",
    component: FuelReportCustomerComponent
  },
  {
    path: "stockIn_form",
    component: StockInAdminComponent
  },
  {
    path: "stockOut_form",
    component: StockOutAdminComponent
  },
  {
    path: "reports",
    component: ReportsComponent
  },
  {
    path: "order-oil",
    component: OrderComponent
  },
  {
    path: "vehicle_details",
    component: VehicleDetailsComponent
  },
  {
    path: "fuel_report",
    component: FuelReportComponent
  },
  {
    path: "view_stock_out",
    component: ViewStockOutAdminComponent
  },
  {
    path: "vehicle-reports",
    component: VehicleReportComponent
  },
  {
    path: "fuel_approve",
    component: FuelApproveComponent
  },
  {
    path: 'view_report',
    component: ViewReportComponent
  },
  {
    path: 'update-stockIn',
    component: UpdateStockInComponent
  },
  {
    path: 'update-stockOut',
    component: UpdateStockOutComponent
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
