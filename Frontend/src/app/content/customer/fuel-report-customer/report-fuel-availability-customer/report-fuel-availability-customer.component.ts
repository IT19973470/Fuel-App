import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../../../_service/customer.service";
import {UserService} from "../../../../_service/user.service";

@Component({
  selector: 'app-report-fuel-availability-customer',
  templateUrl: './report-fuel-availability-customer.component.html',
  styleUrls: ['./report-fuel-availability-customer.component.css']
})
export class ReportFuelAvailabilityCustomerComponent implements OnInit {

  district;
  place;
  districts = [];
  places
  districtPlaces = []
  chartOptions;
  selectedFuelType;

  fuelTypes = []
  fuelStations = [];

  total = {
    name: 'Working Hrs',
    data: [],
    color: '#0c8dc0'
  }
  // total2 = {
  //   name: 'Vehicle Count',
  //   data: [],
  //   color: '#018002'
  // }

  constructor(private customerS: CustomerService, private userS: UserService) {
    userS.setPlace.subscribe((place: any) => {
      this.place = place.id;
      // this.getFuelTypes()
    })
    userS.setDistrictPlaces.subscribe(districtPlaces => {
      this.districtPlaces = districtPlaces;
    })
  }

  ngOnInit(): void {
    this.fillChart()
    this.getFuelTypes()
    // console.log(this.selectedFuelType)
  }

  getFuelAvailabilityM() {
    // console.log(this.selectedFuelType)
    this.fuelStations = []
    this.customerS.getFuelAvailabilityM(this.place).subscribe(fuelStations => {
      if (fuelStations.length > 0) {
        let fuel;
        let fuelStationObj;
        let chartOptions;
        let data;
        let index;
        let i = 0;
        let fuelTypes = []
        for (let fuelStock of fuelStations[0].fuelReports[0].fuelStocks) {
          fuelTypes.push(fuelStock.fuelType)
          if (this.selectedFuelType == fuelStock.fuelTypeId) {
            index = i
          }
          i++
        }
        fuelStations = this.sortByFuel(fuelStations, index)
        console.log(fuelStations)
        for (let fuelStation of fuelStations) {
          fuel = []
          chartOptions = this.fillChart()
          for (let fuelType of fuelTypes) {
            data = []
            for (let fuelReport of fuelStation.fuelReports) {
              for (let fuelStock of fuelReport.fuelStocks) {
                if (fuelStock.fuelType == fuelType) {
                  data.push(fuelStock.quantity)
                }
              }
            }
            fuel.push(
              {
                name: fuelType,
                data: data
              }
            )
          }
          chartOptions.series = fuel
          fuelStationObj = {
            fuelStation: fuelStation.fuelStationStr,
            chartOptions: chartOptions
          }
          this.fuelStations.push(fuelStationObj)
        }
      }
    })
  }

  sortByFuel(fuelStations, index) {
    if (index !== undefined) {
      let fuelStationsArr = []
      for (let fuelStation of fuelStations) {
        fuelStationsArr.push({
          quantity: fuelStation.fuelReports[3].fuelStocks[index].quantity,
          fuelStation: fuelStation
        })
      }
      fuelStationsArr.sort((a, b) => {
        return b.quantity - a.quantity;
      });
      fuelStations = []
      for (let fuelStation of fuelStationsArr) {
        fuelStations.push(fuelStation.fuelStation)
      }
    }
    return fuelStations
  }

  reorderGraph() {
    // this.selectedFuelType = val;
    this.getFuelAvailabilityM()
  }

  setDistricts() {
    this.districts = this.userS.districts
    this.district = JSON.parse(localStorage.getItem('user')).customer.fuelStationPlace.district
    // console.log(this.district)
    if (this.userS.place !== undefined) {
      this.districtPlaces = this.userS.districtPlaces;
      this.place = this.userS.place.id
      this.getFuelAvailabilityM()
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
      this.selectedFuelType = fuelTypes[0].id
      this.setDistricts()
      // this.getFuelAvailabilityM()
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
        {
          name: "Cancelled",
          data: [0, 0, 0, 0, 0, 0, 0],
          color: '#ff0a03'
        },
        {
          name: "Pending",
          data: [0, 0, 0, 0, 0, 0, 0],
          color: '#d29302'
        }
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
          text: "Available Litres"
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
