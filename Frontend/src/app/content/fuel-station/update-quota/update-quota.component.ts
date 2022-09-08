import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../../_service/customer.service";
import {FuelStationService} from "../../../_service/fuel-station.service";
import {QrScanService} from "../../../qr-scan/qr-scan.service";

@Component({
  selector: 'app-update-quota',
  templateUrl: './update-quota.component.html',
  styleUrls: ['./update-quota.component.css']
})
export class UpdateQuotaComponent implements OnInit {

  customer
  quota
  customerFuel
  customers = []

  constructor(private customerS: CustomerService, private fuelStationS: FuelStationService, private qrScanS: QrScanService) {
    this.customer = this.customerS.newCustomer()
    qrScanS.qrValue.subscribe(value => {
      this.getCustomerByVehicle(value)
    })
  }

  ngOnInit(): void {

  }

  getCustomerByVehicle(vehicle) {
    this.customerS.getCustomerByVehicle(vehicle).subscribe(customerR => {
      if (customerR !== null) {
        let vehicleR = this.customers.find(customer => {
          return customer.vehicleNumber === customerR.vehicleNumber
        })
        // console.log(vehicleR)
        if (vehicleR === undefined) {
          this.customers.push(customerR)
        }
      }
    })
  }

  updateCustomerFuel(quota) {
    if (quota === 0) {
      this.fuelStationS.deleteCustomerFuel(this.customer.nic, '123qwe').subscribe(customerFuel => {

      })
    } else {
      let customerFuel = {
        customer: this.customer,
        fuelStation: {
          id: '123qwe'
        },
        fuelPumped: quota
      }
      this.fuelStationS.addCustomerFuel(customerFuel).subscribe(customerFuel => {
        this.customer = customerFuel.customer
      })
    }
  }

  setVehicle(vehicle) {
    this.customer = vehicle
  }
}
