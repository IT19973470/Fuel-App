import {Component, OnInit} from '@angular/core';
import {FuelStationService} from "../../../_service/fuel-station.service";

@Component({
  selector: 'app-fuel-availability',
  templateUrl: './fuel-availability.component.html',
  styleUrls: ['./fuel-availability.component.css']
})
export class FuelAvailabilityComponent implements OnInit {

  fuelStock
  fuelStocks = [];

  constructor(private fuelStationS: FuelStationService) {
    this.fuelStock = fuelStationS.newFuelStock()
  }

  ngOnInit(): void {
    this.getFuelStock()
  }

  addFuelStock() {
    this.fuelStock.fuelStation.id = JSON.parse(localStorage.getItem('user')).id
    this.fuelStationS.addFuelStock(this.fuelStock).subscribe(() => {

    })
  }

  getFuelStock() {
    this.fuelStationS.getFuelStock(JSON.parse(localStorage.getItem('user')).id).subscribe(fuelStocks => {
      // console.log(fuelStock)
      this.fuelStocks = fuelStocks
    })
  }
}
