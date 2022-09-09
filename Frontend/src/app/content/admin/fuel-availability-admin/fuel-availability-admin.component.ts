import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";

@Component({
  selector: 'app-fuel-availability-admin',
  templateUrl: './fuel-availability-admin.component.html',
  styleUrls: ['./fuel-availability-admin.component.css']
})
export class FuelAvailabilityAdminComponent implements OnInit {

  data = []
  constructor(private fuelAdminService: FuelAdminService) {
  }

  ngOnInit(): void {
    this.getCustomer()
  }

  getCustomer() {
    this.fuelAdminService.getFuelAdminStockIn().subscribe(res => {
      this.data = res
      console.log(this.data)
    })
  }

}
