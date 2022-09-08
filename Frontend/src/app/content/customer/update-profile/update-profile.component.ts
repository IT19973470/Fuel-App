import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../../_service/customer.service";

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  customer

  constructor(private customerS: CustomerService) {
    this.customer = this.customerS.customer
  }

  ngOnInit(): void {
  }

  updateCustomer() {
    this.customerS.updateCustomer(this.customer).subscribe(customer => {

    })
  }
}
