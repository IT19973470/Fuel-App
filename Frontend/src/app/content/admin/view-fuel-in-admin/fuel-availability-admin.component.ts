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
  fuelTypes = [];
  fuelType;
  destination;
  d;
  destinations =[
    "India",
    "Russia",
    "China",
    "Pakistan"
  ];
  constructor(private fuelAdminService: FuelAdminService, private router: Router) {
    this.data = this.fuelAdminService.newAddFuelStock();
    this.destination = fuelAdminService.newAddFuelStock().stockFrom;
    console.log(this.destination + "]]]")
    this.fuelType = this.fuelAdminService.newFuelType();
  }

  ngOnInit(): void {
    this.d = (document.getElementById('dest') as HTMLFontElement)['value'];
    this.getCustomer();
    this.getAllFuelTypes();
  }

  getAllFuelTypes(){
    this.fuelAdminService.getFuelTypes().subscribe(data => {
      this.fuelTypes = data;
      console.log(this.fuelTypes)
    })
  }

  getStockInListByFuelType(type){
    this.fuelAdminService.getStockInListByFuelType(type).subscribe(data => {
        this.data = data;
      this.getAllFuelTypes();
    })
  }

  getStockInByStockFrom(){
    // var d = (document.getElementById('dest') as HTMLFontElement)['value'];
    console.log(this.d + ".................")
    this.fuelAdminService.getStockInByStockFrom(this.d).subscribe(data => {
      this.data = data
    })
  }

  getCustomer() {
    this.fuelAdminService.getFuelAdminStockIn().subscribe(res => {
      // console.log(res);
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
