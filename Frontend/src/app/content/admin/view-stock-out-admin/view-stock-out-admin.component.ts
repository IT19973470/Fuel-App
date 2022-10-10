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
  constructor(private fuelAdminService: FuelAdminService, private router: Router) {
  }

  ngOnInit(): void {
    this.getStockOut()
  }

  getStockOut() {
    this.fuelAdminService.getFuelAdminStockOut().subscribe(res => {
      this.data = res
      console.log(this.data)
    })
  }

  updateStockOut() {
    this.fuelAdminService.fuelOut = this.data;
    this.router.navigate(['/update-stockOut']);
    // });
  }

  deleteStockOut(id: string){
    this.fuelAdminService.deleteStockOut(id).subscribe(data => {
      this.getStockOut();
    });
  }

}
