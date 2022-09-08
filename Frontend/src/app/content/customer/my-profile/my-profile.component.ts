import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../../_service/customer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  customer;
  pumps = []
  consumption = 0
  trip

  constructor(private customerS: CustomerService, private router: Router) {
    this.customer = this.customerS.newCustomer()
  }

  ngOnInit(): void {
    // console.log(this.customer)
    this.getCustomer()
    this.getPumpedAmounts()
  }

  getCustomer() {
    this.customerS.getCustomerByVehicle('CAB 4563').subscribe(customer => {
      this.customer = customer
    })
  }

  updateCustomer() {
    this.customerS.customer = this.customer
    this.router.navigate(['/update_profile'])
  }

  getPumpedAmounts() {
    this.customerS.getPumpedAmounts('961751152V').subscribe(pumps => {
      this.pumps = pumps
    })
  }

  calculateLastTrip() {
    if (this.pumps.length >= 2) {
      this.consumption = this.trip * (this.pumps[1] - this.pumps[0])
    }
  }
}
