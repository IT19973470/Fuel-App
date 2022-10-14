import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";
import {Router} from "@angular/router";
import {NotifierService} from "angular-notifier";

@Component({
  selector: 'app-update-stock-out',
  templateUrl: './update-stock-out.component.html',
  styleUrls: ['./update-stock-out.component.css']
})
export class UpdateStockOutComponent implements OnInit {
  updatefuelStockOut;
  fuelStations=[];

  constructor(private fuelAdminService: FuelAdminService,private router: Router, private notifierService: NotifierService) {
    this.updatefuelStockOut = this.fuelAdminService.fuelOut;
    // console.log(fuelAdminService.fuelOut);
    // this.updatefuelStockOut = this.fuelAdminService.newOutFuelStock();
    console.log(this.updatefuelStockOut);

  }

  updateFuelStockOut(){
    this.fuelAdminService.updateStockOut(this.updatefuelStockOut).subscribe(data =>{
      this.notifierService.notify('success', "Stocks out updated Successfully");
      this.router.navigate(['/view_stock_out']);
  })}

  ngOnInit(): void {
    this.getFuelStations()
  }

  getFuelStations(){
    this.fuelAdminService.getFuelStations().subscribe((data) => {
      // console.log(data)
      this.fuelStations=data
    })
  }

}
