import { Component, OnInit } from '@angular/core';
import {FuelPumperService} from "../../../_service/fuel-pumper.service";
import {FuelAdminService} from "../../../_service/fuel-admin.service";
import {Router} from "@angular/router";
import {NotifierService} from "angular-notifier";

@Component({
  selector: 'app-stock-in-admin',
  templateUrl: './stock-in-admin.component.html',
  styleUrls: ['./stock-in-admin.component.css']
})
export class StockInAdminComponent implements OnInit {
  fuelStockIn;

  constructor(private fuelAdminService: FuelAdminService, private router: Router, private notifierService: NotifierService) {
    this.fuelStockIn = this.fuelAdminService.newAddFuelStock()
  }

  addFuelStockIn(){
    this.fuelAdminService.addFuelAdminStockIn(this.fuelStockIn).subscribe(() => {
      this.notifierService.notify('success', "Stocks in added Successfully");
      this.router.navigate(['/fuel_availability_admin']);
    })
  }


  ngOnInit(): void {
  }

}
