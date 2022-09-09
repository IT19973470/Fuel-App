import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FuelStationService} from "../../_service/fuel-station.service";
import {FuelPumperService} from "../../_service/fuel-pumper.service";
import {FuelAdminService} from "../../_service/fuel-admin.service";

@Component({
  selector: 'app-register-fuel-admin',
  templateUrl: './register-fuel-admin.component.html',
  styleUrls: ['./register-fuel-admin.component.css']
})
export class RegisterFuelAdminComponent implements OnInit {

  fuelAdmin;

  constructor(private router: Router, private fuelStationS: FuelStationService, private fuelAdminS: FuelAdminService) {
    this.fuelAdmin = this.fuelAdminS.newFuelAdmin()
  }

  ngOnInit(): void {
  }

  addFuelAdmin() {
    this.fuelAdmin.appUser.userType = 'fuelAdmin'
    this.fuelAdmin.appUser.id = this.fuelAdmin.nic
    this.fuelAdminS.addFuelAdmin(this.fuelAdmin).subscribe(fuelAdmin => {
      this.router.navigate(['/login'])
    })
  }

}
