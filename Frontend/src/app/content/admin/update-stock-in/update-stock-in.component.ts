import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";

@Component({
  selector: 'app-update-stock-in',
  templateUrl: './update-stock-in.component.html',
  styleUrls: ['./update-stock-in.component.css']
})
export class UpdateStockInComponent implements OnInit {
  updatefuelStockIn;

  constructor(private fuelAdminService: FuelAdminService) {
    // this.updatefuelStockIn = this.fuelAdminService.updateStockIn()
  }

  updateFuelStockIn(){
  }


  ngOnInit(): void {
  }

}

