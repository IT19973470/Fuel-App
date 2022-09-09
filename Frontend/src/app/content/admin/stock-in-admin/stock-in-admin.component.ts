import { Component, OnInit } from '@angular/core';
import {FuelPumperService} from "../../../_service/fuel-pumper.service";
import {FuelAdminService} from "../../../_service/fuel-admin.service";

@Component({
  selector: 'app-stock-in-admin',
  templateUrl: './stock-in-admin.component.html',
  styleUrls: ['./stock-in-admin.component.css']
})
export class StockInAdminComponent implements OnInit {
  fuelStockIn;

  constructor(private fuelAdminService: FuelAdminService) {
    this.fuelStockIn = this.fuelAdminService.newAddFuelStock()
  }

  addFuelStockIn(){
    this.fuelAdminService.addFuelAdminStockIn(this.fuelStockIn).subscribe(() => {
    })
  }


  ngOnInit(): void {
  }

}
