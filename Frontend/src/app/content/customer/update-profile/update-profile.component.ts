import {Component, OnInit} from '@angular/core';
import {CustomerService} from '../../../_service/customer.service';
import {Router} from '@angular/router';
import {UserService} from "../../../_service/user.service";

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  customer;
  otp = 0;
  otpEntered;
  otpReceived = false;
  districts = [];
  places
  districtPlaces = []
  vehicleTypes = []

  constructor(private customerS: CustomerService, private router: Router, private userS: UserService) {
    this.customer = this.customerS.customer;
    this.userS.placesFound.subscribe(() => {
      this.getPlaces(this.customer.fuelStationPlace.district)
    })
  }

  ngOnInit(): void {
    this.customer = this.customerS.customer;
    // console.log(this.customer)
    this.setDistricts()
    this.getVehicleTypes()
    this.getPlaces(this.customer.fuelStationPlace.district)
  }

  updateCustomer() {
    this.customerS.updateCustomer(this.customer).subscribe(customer => {
      localStorage.clear()
      this.router.navigate(['/login']);
    });
  }

  sendOTP() {
    this.customerS.sendOTP(this.customer.appUser.email, this.customer.appUser.contactNumber).subscribe(otp => {
      this.otpReceived = true;
      this.otp = otp.otp;
      console.log(this.otp);
    });
  }

  setDistricts() {
    this.districts = this.userS.districts
    this.getPlaces(this.customer.fuelStationPlace.district)
    // this.getPlaces(this.customer.fuelStationPlace.district)
  }

  getPlaces(district) {
    this.districtPlaces = this.userS.getPlaces(district)
  }

  getVehicleTypes() {
    this.userS.getVehicleTypes().subscribe(vehicleTypes => {
      this.vehicleTypes = vehicleTypes;
    })
  }
}
