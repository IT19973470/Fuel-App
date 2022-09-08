import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {UpdateProfileComponent} from "./content/customer/update-profile/update-profile.component";
import {MyProfileComponent} from "./content/customer/my-profile/my-profile.component";
import {UpdateQuotaComponent} from "./content/fuel-station/update-quota/update-quota.component";
import {RegisterComponent} from "./register/register.component";
import {RegisterCustomerComponent} from "./register/register-customer/register-customer.component";
import {RegisterStationComponent} from "./register/register-station/register-station.component";

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
  // {
  //   path: 'register',
  //   component: RegisterComponent,
  //   children:[
  //     {
  //       path: 'customer',
  //       component: RegisterCustomerComponent,
  //     },
  //     {
  //       path: 'fuel_station',
  //       component: RegisterStationComponent,
  //     }
  //   ]
  // },
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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
