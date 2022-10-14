import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-fuel-report-customer',
  templateUrl: './fuel-report-customer.component.html',
  styleUrls: ['./fuel-report-customer.component.css']
})
export class FuelReportCustomerComponent implements OnInit {

  registration = 'customer';

  constructor() { }

  ngOnInit(): void {
  }

}
