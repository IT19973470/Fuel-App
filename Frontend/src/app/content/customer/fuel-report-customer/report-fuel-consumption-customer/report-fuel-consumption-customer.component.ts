import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../../../_service/customer.service";
import {UserService} from "../../../../_service/user.service";

@Component({
  selector: 'app-report-fuel-consumption-customer',
  templateUrl: './report-fuel-consumption-customer.component.html',
  styleUrls: ['./report-fuel-consumption-customer.component.css']
})
export class ReportFuelConsumptionCustomerComponent implements OnInit {

  chartOptions;
  totalFuelPumped = 0;
  totalFuelConsumed = 0;

  constructor(private customerS: CustomerService, private userS: UserService) {
  }

  ngOnInit(): void {
    this.chartOptions = this.fillChart()
    this.getFuelConsumptionsM()
  }

  getFuelConsumptionsM() {
    this.customerS.getFuelConsumptionsM(JSON.parse(localStorage.getItem('user')).customer.vehicle.vehicleNumber).subscribe(fuel => {
      console.log(fuel)
      let fuelArr = []
      let data1 = []
      let data2 = []
      let data3 = []
      for (let consumption of fuel.fuelConsumptions) {
        this.totalFuelPumped += consumption.fuelPumped
        this.totalFuelConsumed += consumption.fuelConsumed
        data1.push(consumption.fuelPumped)
        data2.push(consumption.fuelConsumed)
        data3.push(consumption.fuelPumped - consumption.fuelConsumed)
      }
      fuelArr.push(
        {
          name: 'Fuel Pumped',
          data: data1
        }
      )
      fuelArr.push(
        {
          name: 'Fuel Consumed',
          data: data2
        }
      )
      fuelArr.push(
        {
          name: 'Fuel Remain',
          data: data3
        }
      )
      console.log(fuelArr)
      this.chartOptions.series = fuelArr
    })
  }

  fillChart() {
    return {
      series: [
        {
          name: "Total",
          data: [0, 0, 0, 0, 0, 0, 0],
          color: '#0c8dc0'

        },
        {
          name: "Completed",
          data: [0, 0, 0, 0, 0, 0, 0],
          color: '#018002'
        },
        // {
        //   name: "Cancelled",
        //   data: [0, 0, 0, 0, 0, 0, 0],
        //   color: '#ff0a03'
        // },
        // {
        //   name: "Pending",
        //   data: [0, 0, 0, 0, 0, 0, 0],
        //   color: '#d29302'
        // }
      ],
      chart: {
        type: "bar",
        height: 350
      },
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: "35%",
          endingShape: "rounded"
        }
      },
      dataLabels: {
        enabled: false
      },
      stroke: {
        show: true,
        width: 2,
        colors: ["transparent"]
      },
      xaxis: {
        categories: [
          "Week 1",
          "Week 2",
          "Week 3",
          "Week 4"
        ]
      },
      yaxis: {
        title: {
          text: "Pumped Litres"
        }
      },
      fill: {
        opacity: 1
      },
      tooltip: {
        y: {
          formatter: function (val) {
            return val + ' L';
          }
        }
      }
    };
  }
}
