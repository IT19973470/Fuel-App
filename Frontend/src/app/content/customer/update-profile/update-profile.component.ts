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
  }

  ngOnInit(): void {
    this.setDistricts()
    this.getVehicleTypes()
    this.getPlaces(this.customer.fuelStationPlace.district)
  }

  updateCustomer() {
    this.customerS.updateCustomer(this.customer).subscribe(customer => {
      this.router.navigate(['/my_profile']);
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
