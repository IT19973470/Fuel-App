import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";

@Component({
  selector: 'app-update-stock-out',
  templateUrl: './update-stock-out.component.html',
  styleUrls: ['./update-stock-out.component.css']
})
export class UpdateStockOutComponent implements OnInit {
  updatefuelStockOut;
  fuelStations=[];

  constructor(private fuelAdminService: FuelAdminService) {
    // this.updatefuelStockIn = this.fuelAdminService.updateStockIn()
  }

  updateFuelStockOut(){
  }

  ngOnInit(): void {
  }

  getFuelStations(){
    this.fuelAdminService.getFuelStations().subscribe((data) => {
      console.log(data)
      this.fuelStations=data
    })
  }

}
