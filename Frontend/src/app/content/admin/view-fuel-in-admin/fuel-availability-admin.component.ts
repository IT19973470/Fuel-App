import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-fuel-availability-admin',
  templateUrl: './fuel-availability-admin.component.html',
  styleUrls: ['./fuel-availability-admin.component.css']
})
export class FuelAvailabilityAdminComponent implements OnInit {

  // data = []
  data;
  constructor(private fuelAdminService: FuelAdminService, private router: Router) {
    this.data = this.fuelAdminService.newAddFuelStock();
  }

  ngOnInit(): void {
    this.getCustomer();
  }

  getCustomer() {
    this.fuelAdminService.getFuelAdminStockIn().subscribe(res => {
      console.log(res);
      this.data = res;
      // this.fuelAdminService.fuelIn = res;
      // console.log(this.data);
    })
  }


  updateStockIn(d) {
    console.log(this.fuelAdminService.fuelIn)

    this.fuelAdminService.fuelIn = d;
    // console.log(this.fuelAdminService.fuelIn.id)
      this.router.navigate(['/update-stockIn']);
    // });
  }

  deleteStockIn(id: string){
    this.fuelAdminService.deleteStockIn(id).subscribe(data => {
      this.getCustomer();
    })
  }

}
