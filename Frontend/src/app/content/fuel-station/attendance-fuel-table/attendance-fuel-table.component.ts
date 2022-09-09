import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";
import {FuelStationService} from "../../../_service/fuel-station.service";

@Component({
  selector: 'app-attendance-fuel-table',
  templateUrl: './attendance-fuel-table.component.html',
  styleUrls: ['./attendance-fuel-table.component.css']
})
export class AttendanceFuelTableComponent implements OnInit {

  data = []
  constructor(private fuelStationService: FuelStationService) {
  }

  ngOnInit(): void {
    this.getCustomer()
  }

  getCustomer() {
    this.fuelStationService.getFuelStockIn(JSON.parse(localStorage.getItem('user')).id).subscribe(res => {
      this.data = res
      console.log(this.data)
    })
  }

}
