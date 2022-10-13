import {Component, OnInit} from '@angular/core';
import {CustomerService} from '../../../_service/customer.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  customer;


  constructor(private customerS: CustomerService, private router: Router) {
    this.customer = this.customerS.newCustomer();
  }

  ngOnInit(): void {
    // console.log(this.customer)
    // this.customerS.getCustomer(JSON.parse(localStorage.getItem('user')).email, JSON.parse(localStorage.getItem('user')).contactNumber).subscribe(customer => {
    //   this.customer = customer;
    //   console.log(this.customer)
    this.getCustomer();
    // });
  }

  getCustomer() {
    this.customerS.getCustomerByVehicle(JSON.parse(localStorage.getItem('user')).customer.vehicle.vehicleNumber).subscribe(customer => {
      this.customer = customer;
    });
  }

  updateCustomer() {
    this.customerS.customer = this.customer;
    this.router.navigate(['/update_profile']);
  }

  deleteCustomer() {
    this.customerS.deleteCustomer(JSON.parse(localStorage.getItem('user')).id).subscribe(() => {
      this.router.navigate(['/login']);
    })
  }

}
