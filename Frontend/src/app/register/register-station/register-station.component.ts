import {Component, OnInit} from '@angular/core';
import {FuelStationService} from "../../_service/fuel-station.service";
import {Route, Router} from "@angular/router";
import {LoginService} from "../../login/login.service";
import {UserService} from "../../_service/user.service";


@Component({
  selector: 'app-register-station',
  templateUrl: './register-station.component.html',
  styleUrls: ['./register-station.component.css']
})
export class RegisterStationComponent implements OnInit {


  fuelStation
  districts = [];
  places
  districtPlaces = []

  constructor(private fuelStationS: FuelStationService, private router: Router, private userS: UserService) {
    this.fuelStation = fuelStationS.newFuelStation()

  }

  ngOnInit(): void {
    this.setDistricts()
  }

  addFuelStation() {
    this.fuelStation.appUser.userType = 'fuelStation';
    this.fuelStation.appUser.id = this.fuelStation.id;
    this.fuelStationS.addFuelStation(this.fuelStation).subscribe(fuelStation => {
      this.router.navigate(['/login']);
    });
  }

  setDistricts() {
    this.districts = this.userS.districts
  }

  getPlaces(district) {
    this.districtPlaces = this.userS.getPlaces(district)
  }
}
