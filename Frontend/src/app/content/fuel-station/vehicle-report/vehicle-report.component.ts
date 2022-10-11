import { Component, OnInit } from '@angular/core';
import {FuelStationService} from "../../../_service/fuel-station.service"
import {Router} from "@angular/router"

@Component({
  selector: 'app-vehicle-report',
  templateUrl: './vehicle-report.component.html',
  styleUrls: ['./vehicle-report.component.css']
})
export class VehicleReportComponent implements OnInit {
 data =[]
  constructor(private fuelStationS: FuelStationService,private router: Router) {
  }
  ngOnInit(): void {
    this.getVehicleReport();
  }

  getVehicleReport(){
    this.fuelStationS.getVehicleReport(JSON.parse(localStorage.getItem('user') as string)['id']).subscribe((data) => {
      console.log(data)
      this.data=data;
    })
  }

}
