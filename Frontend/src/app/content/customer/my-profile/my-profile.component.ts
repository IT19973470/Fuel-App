import {Component, OnInit} from '@angular/core';
import {CustomerService} from '../../../_service/customer.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  customer;
  pumps = [];
  consumption = 0;
  trip;

  constructor(private customerS: CustomerService, private router: Router) {
    this.customer = this.customerS.newCustomer();
  }

  ngOnInit(): void {
    // console.log(this.customer)
    this.customerS.getCustomer(JSON.parse(localStorage.getItem('user')).email, JSON.parse(localStorage.getItem('user')).contactNumber).subscribe(customer => {
      this.customer = customer;
      this.getCustomer();
      this.getPumpedAmounts();
    });
  }

  getCustomer() {
    this.customerS.getCustomerByVehicle(this.customer.vehicleNumber).subscribe(customer => {
      this.customer = customer;
    });
  }

  updateCustomer() {
    this.customerS.customer = this.customer;
    this.router.navigate(['/update_profile']);
  }

  getPumpedAmounts() {
    this.customerS.getPumpedAmounts(this.customer.nic).subscribe(pumps => {
      this.pumps = pumps;
    });
  }

  calculateLastTrip() {
    if (this.pumps.length >= 2) {
      console.log(this.pumps[1]);
      this.consumption = this.trip / (this.pumps[1].fuelPumped - this.pumps[0].fuelPumped);
    }
  }
}
