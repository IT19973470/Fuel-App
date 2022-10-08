import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-fuel-availability-admin',
  templateUrl: './fuel-availability-admin.component.html',
  styleUrls: ['./fuel-availability-admin.component.css']
})
export class FuelAvailabilityAdminComponent implements OnInit {

  data = []
  fuelIn;
  constructor(private fuelAdminService: FuelAdminService, private router: Router) {
    this.fuelIn = this.fuelAdminService.fuelIn;
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

  updateStockIn() {
    this.fuelAdminService.updateStockIn(this.fuelIn).subscribe(customer => {
      this.router.navigate(['/update-stockIn'])
    })
  }

}
