import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../../_service/customer.service";
import {NotifierService} from "angular-notifier";

@Component({
  selector: 'app-fuel-consumption-customer',
  templateUrl: './fuel-consumption-customer.component.html',
  styleUrls: ['./fuel-consumption-customer.component.css']
})
export class FuelConsumptionCustomerComponent implements OnInit {

  // customer;
  pumps = [];
  consumption = 0;
  consumed = 0;
  trip;
  fuelConsumptions = []

  constructor(private customerS: CustomerService, private notifierService: NotifierService) {
    // this.customer = this.customerS.newCustomer();
  }

  ngOnInit(): void {
    this.getPumpedAmounts();
    this.getFuelConsumptions()
  }

  getPumpedAmounts() {
    // console.log(JSON.parse(localStorage.getItem('user')))
    this.customerS.getPumpedAmounts(JSON.parse(localStorage.getItem('user')).id).subscribe(pumps => {
      this.pumps = pumps;
    });
  }

  calculateLastTrip() {
    if (!(this.pumps[0].fullTanked && this.pumps[1].fullTanked)) {
      this.notifierService.notify("error", "Both fuel pumps #1 and #2 should be full tank");
    }
    if (this.pumps.length < 2) {
      this.notifierService.notify("error", "Minimum two full pumps should be needed");
    }
    if (this.pumps.length >= 2 && this.pumps[0].fullTanked && this.pumps[1].fullTanked) {
      // let diff = this.pumps[1].fuelPumped - this.pumps[0].fuelPumped
      // if (diff === 0) {
      //   diff = 1.0
      // }
      this.consumed = this.pumps[0].fuelPumped
      this.consumption = this.trip / this.pumps[0].fuelPumped;
    }
  }

  addFuelConsumption() {
    let fuelConsumption = {
      customer: JSON.parse(localStorage.getItem('user')).customer,
      consumed: this.consumed,
      trip: this.trip
    }
    this.customerS.addFuelConsumption(fuelConsumption).subscribe(() => {
      this.getFuelConsumptions()
    })
  }

  deleteFuelConsumption(id){
    this.customerS.deleteFuelConsumption(id).subscribe(() => {
      this.getFuelConsumptions()
    })
  }

  getFuelConsumptions() {
    this.customerS.getFuelConsumptions(JSON.parse(localStorage.getItem('user')).id).subscribe(fuelConsumptions => {
      this.fuelConsumptions = fuelConsumptions
    })
  }
}
