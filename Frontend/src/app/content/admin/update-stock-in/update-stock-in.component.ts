import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-update-stock-in',
  templateUrl: './update-stock-in.component.html',
  styleUrls: ['./update-stock-in.component.css']
})
export class UpdateStockInComponent implements OnInit {
  updatefuelStockIn;
  fuelIn;

  constructor(private fuelAdminService: FuelAdminService,  private router: Router) {

    this.updatefuelStockIn = this.fuelAdminService.fuelIn;
    console.log(">>>>")
    console.log(this.updatefuelStockIn);
    this.updatefuelStockIn = this.fuelAdminService.newAddFuelStock()
  }

  updateFuelStockIn(){
    console.log(this.updatefuelStockIn.id + "...");
    this.fuelAdminService.updateStockIn(this.updatefuelStockIn).subscribe(data => {
      this.router.navigate(['/fuel_availability_admin']);
    })
  }


  ngOnInit(): void {
    this.updatefuelStockIn = this.fuelAdminService.fuelIn;
  }

}

