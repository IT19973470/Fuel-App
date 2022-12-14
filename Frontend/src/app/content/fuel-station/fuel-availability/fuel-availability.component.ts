import {Component, OnInit} from '@angular/core';
import {FuelStationService} from '../../../_service/fuel-station.service';
import {UserService} from "../../../_service/user.service";
import {NotifierService} from "angular-notifier"


@Component({
  selector: 'app-fuel-availability',
  templateUrl: './fuel-availability.component.html',
  styleUrls: ['./fuel-availability.component.css']
})
export class FuelAvailabilityComponent implements OnInit {

  fuelStock;
  fuelStocks = [];
  fuelTypes = []
  fuelStockNext


  constructor(private fuelStationS: FuelStationService, private userS: UserService,private notifierService:NotifierService) {
    this.fuelStock = fuelStationS.newFuelStock()
    this.fuelStockNext = fuelStationS.newFuelStock()
  }

  ngOnInit(): void {
    this.getFuelTypes()
    this.getFuelStock()
  }

  getFuelTypes() {
    this.userS.getFuelTypes().subscribe(fuelTypes => {
      this.fuelTypes = fuelTypes;
    })
  }

  addFuelStock() {
    this.fuelStock.fuelStation.id = JSON.parse(localStorage.getItem('user')).id;
    this.fuelStationS.addFuelStock(this.fuelStock).subscribe((deliveryDetail) => {
      this.getFuelStock()
      this.notifierService.notify("success", "Stock added successfully");
    }, (err) => {
      this.notifierService.notify("error", "Stock added fail");
    })
  }

  nextFuelStock() {
    this.fuelStockNext.fuelStation.id = JSON.parse(localStorage.getItem('user')).id
    this.fuelStationS.addNextFuelStock(this.fuelStockNext).subscribe(() => {
      this.getFuelStock()
      this.notifierService.notify("success", "Stock added successfully");
    },(err) => {
      this.notifierService.notify("error", "Stock added fail");
    })
  }

  getFuelStock() {
    this.fuelStationS.getFuelStock(JSON.parse(localStorage.getItem('user')).id).subscribe(fuelStocks => {
      console.log(fuelStocks)
      this.fuelStocks = fuelStocks
    })
  }
}
