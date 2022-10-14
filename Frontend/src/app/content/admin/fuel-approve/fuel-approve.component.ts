import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-fuel-approve',
  templateUrl: './fuel-approve.component.html',
  styleUrls: ['./fuel-approve.component.css']
})
export class FuelApproveComponent implements OnInit {

  data;

  constructor(private fuelAdminService: FuelAdminService, private router: Router) {
    this.data = this.fuelAdminService.order();
  }

  ngOnInit(): void {
    // this.d = (document.getElementById('dest') as HTMLFontElement)['value'];
    this.getOrder();
  }



  getOrder(){
    this.fuelAdminService.getFuelOrders().subscribe(res =>{
      this.data = res;
      console.log(this.data)
    })
  }

  approveOrder(order){
    this.fuelAdminService.approveOrder(order).subscribe(data => {
    this.getOrder();
    })
  }

  denyOrder(order){
    console.log(order)
    this.fuelAdminService.denyOrder(order).subscribe(data => {
      this.getOrder();
    })
  }
}
