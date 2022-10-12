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
  fuelIn;

  constructor(private fuelAdminService: FuelAdminService) {
    this.updatefuelStockOut = this.fuelAdminService.fuelOut;
    console.log(fuelAdminService.fuelOut);
     // this.updatefuelStockOut = this.fuelAdminService.fuelIn();
  }

  updateFuelStockOut(){
 this.fuelAdminService.updateStockOut(this.updatefuelStockOut).subscribe()
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
