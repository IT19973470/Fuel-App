import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";

@Component({
  selector: 'app-update-stock-in',
  templateUrl: './update-stock-in.component.html',
  styleUrls: ['./update-stock-in.component.css']
})
export class UpdateStockInComponent implements OnInit {
  updatefuelStockIn;
  fuelIn;

  constructor(private fuelAdminService: FuelAdminService) {
    console.log(this.fuelAdminService.fuelIn.id);
    this.updatefuelStockIn = this.fuelAdminService.fuelIn.id;
    // this.updatefuelStockIn = this.fuelAdminService.updateStockIn()
  }

  updateFuelStockIn(){
    this.fuelAdminService.updateStockIn(this.updatefuelStockIn).subscribe(data => {

    })
  }


  ngOnInit(): void {
  }

}

