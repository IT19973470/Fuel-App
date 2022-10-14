import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from '../../../_service/fuel-admin.service';
import {FuelStationService} from '../../../_service/fuel-station.service';

@Component({
  selector: 'app-attendance-fuel-table',
  templateUrl: './attendance-fuel-table.component.html',
  styleUrls: ['./attendance-fuel-table.component.css']
})
export class AttendanceFuelTableComponent implements OnInit {

  data = [];
  attendance = [];
  constructor(private fuelStationService: FuelStationService) {
  }

  ngOnInit(): void {
    this.getCustomer();
    this.getAtteadance();
  }

  getCustomer() {
    this.fuelStationService.getFuelStockIn(JSON.parse(localStorage.getItem('user')).id).subscribe(res => {
      this.data = res;
      console.log(JSON.parse(localStorage.getItem('user')).id)
      console.log(res);
    });
  }

  getAtteadance() {
    this.fuelStationService.getAttendence().subscribe(attendance => {
       console.log(attendance)
      this.attendance = attendance;
      console.log( this.attendance);
    });
  }
}
