import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-fuel-approve',
  templateUrl: './fuel-approve.component.html',
  styleUrls: ['./fuel-approve.component.css']
})
export class FuelApproveComponent implements OnInit {

  updateStockIn() {

  }

  data = []
  constructor(private fuelAdminService: FuelAdminService, private router: Router) {
  }

  ngOnInit(): void {
    this.getFuelOrders()
  }

  getFuelOrders() {
    this.fuelAdminService.getFuelOrders().subscribe(res => {
      this.data = res
      console.log(this.data)
    })
  }
}


