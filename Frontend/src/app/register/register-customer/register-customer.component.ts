import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../_service/customer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register-customer',
  templateUrl: './register-customer.component.html',
  styleUrls: ['./register-customer.component.css']
})
export class RegisterCustomerComponent implements OnInit {

  customer;

  constructor(private customerS: CustomerService, private router: Router) {
    this.customer = this.customerS.newCustomer()
  }

  ngOnInit(): void {
  }

  addCustomer() {
    this.customerS.addCustomer(this.customer).subscribe(customer => {
      this.router.navigate(['/login'])
    })
  }
}
