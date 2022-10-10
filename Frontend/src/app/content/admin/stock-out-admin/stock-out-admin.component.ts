import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";

@Component({
  selector: 'app-stock-out-admin',
  templateUrl: './stock-out-admin.component.html',
  styleUrls: ['./stock-out-admin.component.css']
})
export class StockOutAdminComponent implements OnInit {

  fuelStockOut;
  fuelStations=[];


  constructor(private fuelAdminService: FuelAdminService) {
    this.fuelStockOut = this.fuelAdminService.newOutFuelStock()
  }

  addFuelStockOut(){
    this.fuelAdminService.addFuelAdminStockOut(this.fuelStockOut).subscribe(() => {
    })
  }

  ngOnInit(): void {
    this.getFuelStations()
  }

  getFuelStations(){
    this.fuelAdminService.getFuelStations().subscribe((data) => {
      console.log(data)
      this.fuelStations=data
    })
  }

}
