import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../../_service/customer.service";
import {NotifierService} from "angular-notifier";
import {DomSanitizer} from "@angular/platform-browser";
import {AlertBoxService} from "../../../alert-box/alert-box.service";

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
  selectedOrderBy1 = ''
  selectedOrderBy2 = ''

  alertBox = {
    alert: false,
    msg: '',
    value: ''
  };

  constructor(private customerS: CustomerService, private notifierService: NotifierService, private alertService: AlertBoxService) {
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
    if (this.pumps.length >= 2) {
      if (!(this.pumps[0].fullTanked && this.pumps[1].fullTanked)) {
        this.notifierService.notify("error", "Both fuel pumps #1 and #2 should be full tank");
      }
      if (this.pumps.length >= 2 && this.pumps[0].fullTanked && this.pumps[1].fullTanked) {
        // let diff = this.pumps[1].fuelPumped - this.pumps[0].fuelPumped
        // if (diff === 0) {
        //   diff = 1.0
        // }
        this.consumed = this.pumps[0].fuelPumped
        this.consumption = this.trip / this.pumps[0].fuelPumped;
      }
    } else {
      this.notifierService.notify("error", "Minimum two full pumps should be needed");
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
      if (this.consumed == 0) {
        this.notifierService.notify("success", "Trip is successfully added");
      } else {
        this.notifierService.notify("success", "Consumption is successfully added");
      }
      this.consumed = 0
    })
  }

  deleteFuelConsumption(id) {
    this.alertBox.alert = true;
    this.alertBox.msg = 'Do you want to delete the record?';
    this.alertService.reply.observers = [];
    this.alertService.reply.subscribe(reply => {
      if (reply) {
        this.customerS.deleteFuelConsumption(id).subscribe(() => {
          this.getFuelConsumptions()
        })
      }
      this.alertBox.alert = false;
    })
  }

  getFuelConsumptions() {
    this.customerS.getFuelConsumptions(JSON.parse(localStorage.getItem('user')).id).subscribe(fuelConsumptions => {
      this.fuelConsumptions = fuelConsumptions
      console.log(this.fuelConsumptions)
    })
  }

  reOrderPumps() {
    // console.log(this.pumps)
    let fuelStationsArr = []
    for (let pump of this.pumps) {
      let quantity;
      if (this.selectedOrderBy1 == 'date') {
        quantity = pump.pumpedAt
      } else if (this.selectedOrderBy1 == 'pump') {
        quantity = pump.fuelPumped
      }
      fuelStationsArr.push({
        quantity: quantity,
        fuelStation: pump
      })
    }
    if (this.selectedOrderBy1 == 'date') {
      fuelStationsArr.sort((a, b) => {
        let bb: any = new Date(b.quantity)
        let aa: any = new Date(a.quantity)
        return bb - aa;
      });
    } else {
      fuelStationsArr.sort((a, b) => {
        return b.quantity - a.quantity;
      });
    }
    this.pumps = []
    for (let fuelStation of fuelStationsArr) {
      this.pumps.push(fuelStation.fuelStation)
    }
  }

  reOrderConsumes() {
    // console.log(this.pumps)
    let fuelStationsArr = []
    for (let pump of this.fuelConsumptions) {
      let quantity;
      if (this.selectedOrderBy2 == 'date') {
        quantity = pump.checkedAt
      } else if (this.selectedOrderBy2 == 'consumption') {
        quantity = pump.trip / pump.consumed == 0 ? 1 : pump.consumed
      } else if (this.selectedOrderBy2 == 'trip') {
        quantity = pump.trip
      } else if (this.selectedOrderBy2 == 'pump') {
        quantity = pump.consumed
      }
      fuelStationsArr.push({
        quantity: quantity,
        fuelStation: pump
      })
    }
    if (this.selectedOrderBy2 == 'date') {
      fuelStationsArr.sort((a, b) => {
        let bb: any = new Date(b.quantity)
        let aa: any = new Date(a.quantity)
        return bb - aa;
      });
    } else {
      fuelStationsArr.sort((a, b) => {
        return b.quantity - a.quantity;
      });
    }
    this.fuelConsumptions = []
    for (let fuelStation of fuelStationsArr) {
      this.fuelConsumptions.push(fuelStation.fuelStation)
    }
  }
}
