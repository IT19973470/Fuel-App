import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../../login/login.service";
import {CustomerService} from "../../../_service/customer.service";
import {UserService} from "../../../_service/user.service";

@Component({
  selector: 'app-fuel-availability-customer',
  templateUrl: './fuel-availability-customer.component.html',
  styleUrls: ['./fuel-availability-customer.component.css']
})
export class FuelAvailabilityCustomerComponent implements OnInit {

  district;
  place;
  districts = [];
  places
  districtPlaces = []
  fuelAvailabilities = []

  constructor(private userS: UserService, private customerS: CustomerService) {
    userS.setPlace.subscribe((place: any) => {
      this.place = place.id;
      this.getFuelAvailability()
      // console.log(this.place)
    })
    userS.setDistrictPlaces.subscribe(districtPlaces => {
      this.districtPlaces = districtPlaces;
    })
  }

  ngOnInit(): void {
    this.setDistricts();
  }

  getFuelAvailability() {
    this.customerS.getFuelAvailability(this.place, 'qbc').subscribe(fuelAvailability => {
      console.log(fuelAvailability)
      this.fuelAvailabilities = fuelAvailability
    })
  }

  setDistricts() {
    this.districts = this.userS.districts
    this.district = JSON.parse(localStorage.getItem('user')).customer.fuelStationPlace.district
    // console.log(this.district)
    if (this.userS.place !== undefined) {
      this.districtPlaces = this.userS.districtPlaces;
      this.place = this.userS.place.id
      this.getFuelAvailability()
    }
  }

  getPlaces(district) {
    this.districtPlaces = this.userS.getPlaces(district)
    // if (initVal === 1) {
    //   this.place = JSON.parse(localStorage.getItem('user')).customer.fuelStationPlace.place
    // }
  }
}
