import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";

@Component({
  selector: 'app-view-stock-out-admin',
  templateUrl: './view-stock-out-admin.component.html',
  styleUrls: ['./view-stock-out-admin.component.css']
})
export class ViewStockOutAdminComponent implements OnInit {

  data = []
  constructor(private fuelAdminService: FuelAdminService) {
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

}
