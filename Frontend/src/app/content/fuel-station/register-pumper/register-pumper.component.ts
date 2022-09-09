import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../../_service/customer.service";
import {Router} from "@angular/router";
import {FuelStationService} from "../../../_service/fuel-station.service";
import {FuelPumperService} from "../../../_service/fuel-pumper.service";

@Component({
  selector: 'app-register-pumper',
  templateUrl: './register-pumper.component.html',
  styleUrls: ['./register-pumper.component.css']
})
export class RegisterPumperComponent implements OnInit {

  fuelPumper;

  constructor(private router: Router, private fuelStationS: FuelStationService, private fuelPumperS: FuelPumperService) {
    this.fuelPumper = this.fuelPumperS.newFuelPumper()
  }

  ngOnInit(): void {
  }

  addFuelPumper() {
    this.fuelPumper.appUser.userType = 'fuelPumper'
    this.fuelPumper.appUser.id = this.fuelPumper.nic
    this.fuelPumperS.addFuelPumper(this.fuelPumper).subscribe(fuelPumper => {
      // this.router.navigate(['/login'])
    })
  }

}
