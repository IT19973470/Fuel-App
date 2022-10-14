import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../../_service/customer.service";
import {FuelStationService} from "../../../_service/fuel-station.service";
import {QrScanService} from "../../../qr-scan/qr-scan.service";
import {FuelPumperService} from "../../../_service/fuel-pumper.service";
import {UserService} from "../../../_service/user.service";
import {NotifierService} from "angular-notifier";

@Component({
  selector: 'app-update-quota',
  templateUrl: './update-quota.component.html',
  styleUrls: ['./update-quota.component.css']
})
export class UpdateQuotaComponent implements OnInit {

  customer
  quota
  customerIndex;
  customers = []
  val;
  fuelTypes = [];
  fuelType = {
    id: ''
  };
  fullTanked = false

  constructor(private customerS: CustomerService, private fuelPumperS: FuelPumperService, private qrScanS: QrScanService, private userS: UserService,private notifierService: NotifierService) {
    this.customer = this.customerS.newCustomer()
    qrScanS.qrValue.subscribe(value => {
      this.getCustomerByVehicle(value)
    })
  }

  ngOnInit(): void {
    this.getFuelTypes();
  }

  getFuelTypes() {
    this.userS.getFuelTypes().subscribe(fuelTypes => {
      this.fuelTypes = fuelTypes;
    })
  }

  getCustomerByVehicle(vehicle) {
    this.customerS.getCustomerByVehicle(vehicle).subscribe(customerR => {
      // console.log(customerR)
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
      this.fuelPumperS.deleteCustomerFuel(this.customer.nic, JSON.parse(localStorage.getItem('user')).id).subscribe(customerFuel => {
        this.customer = customerFuel.customer
      })
    } else {
      let customerFuel = {
        customer: this.customer,
        fuelStation: {
          id: JSON.parse(localStorage.getItem('user')).fuelPumper.fuelStation.id
        },
        fuelPumped: quota,
        fuelType: this.fuelType,
        fullTanked: this.fullTanked
      }
      // console.log(customerFuel)
      this.fuelPumperS.addCustomerFuel(customerFuel).subscribe(customerFuel => {
        // console.log(customerFuel)
        this.customer.quota = customerFuel.customer.quota
        this.notifierService.notify("success", "Minimum two full pumps should be needed");
      })

    }
  }

  setVehicle(vehicle, index) {
    this.customer = vehicle
    this.customerIndex = index
  }
}
