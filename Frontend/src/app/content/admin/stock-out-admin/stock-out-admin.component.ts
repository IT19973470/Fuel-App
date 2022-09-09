import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";

@Component({
  selector: 'app-stock-out-admin',
  templateUrl: './stock-out-admin.component.html',
  styleUrls: ['./stock-out-admin.component.css']
})
export class StockOutAdminComponent implements OnInit {

  fuelStockOut;

  constructor(private fuelAdminService: FuelAdminService) {
    this.fuelStockOut = this.fuelAdminService.newOutFuelStock()
  }

  addFuelStockOut(){
    this.fuelAdminService.addFuelAdminStockOut(this.fuelStockOut).subscribe(() => {
    })
  }

  ngOnInit(): void {
  }

}
