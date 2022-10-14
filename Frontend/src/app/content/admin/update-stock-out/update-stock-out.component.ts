import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-update-stock-out',
  templateUrl: './update-stock-out.component.html',
  styleUrls: ['./update-stock-out.component.css']
})
export class UpdateStockOutComponent implements OnInit {
  updatefuelStockOut;

  constructor(private fuelAdminService: FuelAdminService,private router: Router) {
    this.updatefuelStockOut = this.fuelAdminService.fuelOut;
    console.log(fuelAdminService.fuelOut);
  }

  updateFuelStockOut(){
    this.fuelAdminService.updateStockOut(this.updatefuelStockOut).subscribe(data =>{
    this.router.navigate(['/view_stock_out']);
  })}

  ngOnInit(): void {
  }

}
