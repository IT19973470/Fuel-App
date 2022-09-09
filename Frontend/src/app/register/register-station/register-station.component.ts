import {Component, OnInit} from '@angular/core';
import {FuelStationService} from "../../_service/fuel-station.service";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-register-station',
  templateUrl: './register-station.component.html',
  styleUrls: ['./register-station.component.css']
})
export class RegisterStationComponent implements OnInit {

  fuelStation

  constructor(private fuelStationS: FuelStationService, private router: Router) {
    this.fuelStation = fuelStationS.newFuelStation()
  }

  ngOnInit(): void {
  }

  addFuelStation() {
    this.fuelStation.appUser.userType = 'fuelStation'
    this.fuelStation.appUser.id = this.fuelStation.id
    this.fuelStationS.addFuelStation(this.fuelStation).subscribe(fuelStation => {
      this.router.navigate(['/login'])
    })
  }
}
