import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../_service/customer.service";
import {Router} from "@angular/router";
import {LoginService} from "../../login/login.service";

@Component({
  selector: 'app-register-customer',
  templateUrl: './register-customer.component.html',
  styleUrls: ['./register-customer.component.css']
})
export class RegisterCustomerComponent implements OnInit {

  customer;
  otp = 0
  otpEntered;
  otpReceived = false;
  districts = [];
  places
  districtPlaces = []

  constructor(private customerS: CustomerService, private router: Router, private loginS: LoginService) {
    this.customer = this.customerS.newCustomer()
  }

  ngOnInit(): void {
    this.setDistricts()
  }

  addCustomer() {
    this.customer.appUser.userType = 'customer'
    this.customer.appUser.id = this.customer.nic
    this.customerS.addCustomer(this.customer).subscribe(customer => {
      this.router.navigate(['/login'])
    })
  }

  sendOTP() {
    this.customerS.sendOTP(this.customer.appUser.email, this.customer.appUser.contactNumber).subscribe(otp => {
      this.otpReceived = true
      this.otp = otp.otp
      console.log(this.otp)
    })
  }

  setDistricts() {
    this.districts = this.loginS.districts
  }

  getPlaces(district) {
    this.districtPlaces = this.loginS.getPlaces(district)
  }
}
