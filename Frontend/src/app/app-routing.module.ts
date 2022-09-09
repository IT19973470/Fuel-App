import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {UpdateProfileComponent} from "./content/customer/update-profile/update-profile.component";
import {MyProfileComponent} from "./content/customer/my-profile/my-profile.component";
import {UpdateQuotaComponent} from "./content/fuel_pumper/update-quota/update-quota.component";
import {RegisterComponent} from "./register/register.component";
import {RegisterCustomerComponent} from "./register/register-customer/register-customer.component";
import {RegisterStationComponent} from "./register/register-station/register-station.component";
import {MarkAttendanceComponent} from "./content/fuel_pumper/mark-attendance/mark-attendance.component";
import {RegisterPumperComponent} from "./content/fuel-station/register-pumper/register-pumper.component";
import {FuelAvailabilityComponent} from "./content/fuel-station/fuel-availability/fuel-availability.component";
import {AttendanceFuelTableComponent} from "./content/fuel-station/attendance-fuel-table/attendance-fuel-table.component";
import {RegisterFuelAdminComponent} from "./register/register-fuel-admin/register-fuel-admin.component";
import {FuelAvailabilityAdminComponent} from "./content/admin/fuel-availability-admin/fuel-availability-admin.component";
import {StockInAdminComponent} from "./content/admin/stock-in-admin/stock-in-admin.component";
import {StockOutAdminComponent} from "./content/admin/stock-out-admin/stock-out-admin.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'my_profile',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'mark_attendance',
    component: MarkAttendanceComponent,
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'fuel_availability',
    component: FuelAvailabilityComponent
  },
  {
    path: 'attendance_fuel',
    component: AttendanceFuelTableComponent
  },
  {
    path: 'register_pumper',
    component: RegisterPumperComponent,
  },
  {
    path: 'register_fuel_admin',
    component: RegisterFuelAdminComponent,
  },
  {
    path: 'my_profile',
    component: MyProfileComponent,
  },
  {
    path: 'update_profile',
    component: UpdateProfileComponent,
  },
  {
    path: 'update_quota',
    component: UpdateQuotaComponent,
  },
  {
    path: 'fuel_availability_admin',
    component: FuelAvailabilityAdminComponent,
  },
  {
    path: 'stockIn_form',
    component: StockInAdminComponent,
  },
  {
    path: 'stockOut_form',
    component: StockOutAdminComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
