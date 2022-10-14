import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../../login/login.service";
import {CustomerService} from "../../../_service/customer.service";
import {UserService} from "../../../_service/user.service";
import {DomSanitizer} from "@angular/platform-browser";

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
  selectedFuelType = '';
  selectedOrderBy = ''

  fuelTypes = []

  constructor(private userS: UserService, private customerS: CustomerService, private domSanitizer: DomSanitizer) {
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
    this.getFuelTypes()
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

  getFuelTypes() {
    this.userS.getFuelTypes().subscribe(fuelTypes => {
      this.fuelTypes = fuelTypes;
      // this.selectedFuelType = fuelTypes[0].id
      // this.setDistricts()
      // this.getFuelAvailabilityM()
    })
  }

  transform(url) {
    return this.domSanitizer.bypassSecurityTrustResourceUrl(url);
  }

  getWholeNumber(val) {
    return Math.floor(val)
  }

  reOrder() {
    console.log(this.fuelAvailabilities)
    let fuelStationsArr = []
    for (let fuelStation of this.fuelAvailabilities) {
      let quantity;
      if (this.selectedOrderBy == 'stock') {
        quantity = fuelStation.availableStock[this.selectedFuelType].quantity
      } else if (this.selectedOrderBy == 'hour') {
        quantity = fuelStation.fuelSupplyPerHour[this.selectedFuelType].quantity
      } else if (this.selectedOrderBy == 'car') {
        quantity = fuelStation.availableVehicles[0].vehicles[this.selectedFuelType].quantity
      } else if (this.selectedOrderBy == 'bike') {
        quantity = fuelStation.availableVehicles[1].vehicles[this.selectedFuelType].quantity
      } else if (this.selectedOrderBy == 'wheel') {
        quantity = fuelStation.availableVehicles[2].vehicles[this.selectedFuelType].quantity
      } else if (this.selectedOrderBy == 'bus') {
        quantity = fuelStation.availableVehicles[3].vehicles[this.selectedFuelType].quantity
      }
      fuelStationsArr.push({
        quantity: quantity,
        fuelStation: fuelStation
      })
    }
    fuelStationsArr.sort((a, b) => {
      return b.quantity - a.quantity;
    });
    this.fuelAvailabilities = []
    for (let fuelStation of fuelStationsArr) {
      this.fuelAvailabilities.push(fuelStation.fuelStation)
    }
  }
}
