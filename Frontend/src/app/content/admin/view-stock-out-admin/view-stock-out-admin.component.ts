import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-view-stock-out-admin',
  templateUrl: './view-stock-out-admin.component.html',
  styleUrls: ['./view-stock-out-admin.component.css']
})
export class ViewStockOutAdminComponent implements OnInit {

  data = []
  fuelTypes = [];
  fuelType;
  constructor(private fuelAdminService: FuelAdminService, private router: Router) {
    // this.data = this.fuelAdminService.newAddFuelStock();
    // this.destination = fuelAdminService.newAddFuelStock().stockFrom;
    // console.log(this.destination + "]]]")
    this.fuelType = this.fuelAdminService.newFuelType();
  }

  ngOnInit(): void {
    this.getStockOut();
    this.getAllFuelTypes();
  }

  getStockOut() {
    this.fuelAdminService.getFuelAdminStockOut().subscribe(res => {
      this.data = res
      console.log(this.data)
    })
  }

  updateStockOut(d) {
    this.fuelAdminService.fuelOut = d;
    this.router.navigate(['/update-stockOut']);
    // });
  }

  deleteStockOut(id: string){
    this.fuelAdminService.deleteStockOut(id).subscribe(data => {
      this.getStockOut();
    });
  }

  getAllFuelTypes(){
    this.fuelAdminService.getFuelTypes().subscribe(data => {
      this.fuelTypes = data;
      console.log(this.fuelTypes)
    })
  }

  getStockInListByFuelType(type){
    console.log(type)
    this.fuelAdminService.getStockInListByFuelType(type).subscribe(data => {
      this.data = data;
      this.getAllFuelTypes();
    });
  }

  getStockOutListByFuelType(type) {
    this.fuelAdminService.getStockOutListByFuelType(type).subscribe(data => {
      this.data = data;
      this.getAllFuelTypes();
    });

  }
}
