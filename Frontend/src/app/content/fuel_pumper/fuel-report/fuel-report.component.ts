import {assertPlatform, Component, OnInit} from '@angular/core';
import {FuelPumperService} from "../../../_service/fuel-pumper.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-fuel-report',
  templateUrl: './fuel-report.component.html',
  styleUrls: ['./fuel-report.component.css']
})
export class FuelReportComponent implements OnInit {

  chartOptions;
  application;
  startDate;
  endDate;
  totalPetrol95FuelAmount: number = 0;
  totalPetrol92FuelAmount: number = 0;
  totalDieselFuelAmount: number = 0;
  petrol95;
  petrol92;
  superDiesel;
  diesel;
  totalSuperDieselFuelAmount: number = 0;
  deliveryItemDetails = [];
  weeklyDeliveries = [];
  applicationData = [];
  applicationDataChart = [];
  fuelType;

  obj = {
    pd: {
      deliveries: 0,
      completed: 0,
      cancelled: 0
    },
    id: {
      deliveries: 0,
      completed: 0,
      cancelled: 0
    },
    pid: {
      deliveries: 0,
      completed: 0,
      cancelled: 0
    }
  }


  constructor(private fuelPumperService: FuelPumperService, private router: Router) {
  }

  ngOnInit(): void {
    this.fillChart();
  }

  getDataToReport() {
    this.getAllFuelRecord();
    this.getAllFuelRecordChart();
  }

  getAllFuelRecord() {
    this.fuelPumperService.getAllFuelRecord(this.startDate, this.endDate).subscribe(data => {
      this.applicationData = data;
      // this.chartdata(data)
      // console.log(this.applicationData)
      // for (var i of data) {
      //   if(i.fuelType.name == 'Petrol 95'){
      //     this.totalPetrol95FuelAmount = this.totalPetrol95FuelAmount + i.fuelPumped;
      //   }if(i.fuelType.name == 'Petrol 92'){
      //     this.totalPetrol92FuelAmount = this.totalPetrol92FuelAmount + i.fuelPumped;
      //   }if(i.fuelType.name == 'Super Diesel'){
      //     this.totalSuperDieselFuelAmount = this.totalSuperDieselFuelAmount + i.fuelPumped;
      //   }else {
      //     this.totalDieselFuelAmount = this.totalDieselFuelAmount + i.fuelPumped;
      //   }
      // }
      // console.log(this.totalPetrolFuelAmount, this.totalPetrolFuelAmount+"........")
    })
  }

  getAllFuelRecordChart() {
    this.fuelPumperService.getAllFuelRecordChart(this.startDate, this.endDate).subscribe(data => {
      this.applicationDataChart = data;
      this.chartdata(data)
      // console.log(data)
    })
  }

  chartdata(application) {
    let fuelArr = []
    let fuels = new Set()
    let dates = new Set()
    let datesArr = []
    // console.log(application)
    for (let pump of application) {
      dates.add(pump.pumpedAtDate)
      fuels.add(pump.fuelType.name)
    }
    for (let date of dates) {
      // console.log(date)
      datesArr.push(
        application.filter(dateObj => {
          // console.log(dateObj)
          return date == dateObj.pumpedAtDate
        })
      )
    }
    // console.log(datesArr)

    for (let date of datesArr) {
      let fuelArrObj = []
      for (let fuel of fuels) {
        let amount = 0
        for (let fuelObj of date) {
          if (fuel == fuelObj.fuelType.name) {
            amount += fuelObj.fuelPumped
          }
        }
        // console.log(date[0])
        fuelArrObj.push({
          date: date[0].pumpedAtDate,
          name: fuel,
          amount: amount
        })
        // console.log(fuelArrObj[0].name)
        // this.chartOptions.series.push(fuelArrObj[date].name);
      }
      fuelArr.push(fuelArrObj)
    }
    console.log(fuelArr)

    for (let fuelA of fuelArr) {
      for (let nFuel of fuelA) {
        console.log(nFuel.date)
        this.chartOptions.xaxis.categories.push(nFuel.date)
        if(nFuel.name == 'Petrol 95'){
          this.type1.data.push(nFuel.amount);
        }else if(nFuel.name == 'Petrol 92'){
          this.type2.data.push(nFuel.amount);
        }else if(nFuel.name == 'Super Diesel'){
          this.type3.data.push(nFuel.amount);
        }else if(nFuel.name == 'Diesel'){
          this.type4.data.push(nFuel.amount);
        }

        // this.chartOptions.series.push(nFuel.name)
      }
      // console.log(i.date)
      // this.chartOptions.xaxis.categories.push(r.date);
      // this.chartOptions.series.push(r.name);
    }
    this.chartOptions.series = [this.type1, this.type2, this.type3, this.type4]

    // let arr = [];
    // for (let data of fuelArr) {
    //   let name = arr.find(n => {
    //     return n == data.pumpedAtDate;
    //   });
    //
    // }
  }
  // let datesArr = [];
  // for (let data of application){
  //
  //   let date = datesArr.find(date1 => {
  //     return date1 == data.pumpedAtDate;
  //   });
  //
  //   console.log(date + "..." + data.pumpedAtDate)
  //   if(date === undefined){
  //     datesArr.push(data.pumpedAtDate);
  //   }
  //
  //   // this.chartOptions.xaxis.categories = datesArr
  //
  //   // this.chartOptions.xaxis.categories.push(data.pumpedAtDate)
  //   // this.chartOptions.xaxis.categories.push(''+data.fuelPumpedAt)
  //   // this.type1.data.push(data.quantity);
  //   // console.log(data.fuelPumpedAt)
  //
  //   if(data.fuelType.name == 'Petrol 95') {
  //     this.type1.data.push(data.fuelPumped);
  //   }
  //   else if(data.fuelType.name == 'Petrol 92'){
  //     this.type2.data.push(data.fuelPumped);
  //   }
  //   else if(data.fuelType.name == 'Super Diesel'){
  //     this.type3.data.push(data.fuelPumped);
  //   }
  //   else{
  //     this.type4.data.push(data.fuelPumped);
  //   }
  // this.total2.data.push(data.p)
  // console.log(data.fuelPumperAttendance.timeOut.substring(0,2)-data.fuelPumperAttendance.timeIn.substring(0,2))


  // }

  // for(let i of datesArr){
  //   console.log(i)
  //   this.chartOptions.xaxis.categories.push(i);
  // }
  //
  // this.chartOptions.series=[this.type1, this.type2, this.type3, this.type4];
  // this.chartOptions.series=[this.type2];
  // this.chartOptions.series=[this.type3];
  // this.chartOptions.series=[this.type4];
  // this.chartOptions.series.push(this.type1)
  // this.total.data.push(Math.floor(data.fuelPumperAttendance.timeIn)-Math.floor(data.fuelPumperAttendance.timeOut))
  // console.log(Math.floor(data.fuelPumperAttendance.timeIn.now()))
  // console.log( this.total.data)
  //  this.chartOptions.xaxis.categories.push(this.pumpers)
  //    this.chartOptions.series.push(this.pumpers,this.total)
  // this.chartOptions.xaxis.categories.push(data.fuelPumperAttendance.fuelPumper.name)


  type1 = {
    name: 'Petrol 95',
    data: [],
    color: '#0c8dc0'
  }
  type2 = {
    name: 'Petrol 92',
    data: [],
    color: '#018002'
  }

  type3 = {
    name: 'Super Diesel',
    data: [],
    color: '#38049f'
  }

  type4 = {
    name: 'Diesel',
    data: [],
    color: '#e5c706'
  }

  fillChart() {
    this.chartOptions = {
      series: [],
      chart: {
        type: "bar",
        height: 350
      },
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: "20%",
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
        title: {
          text: "Date"
        },
        categories: []
      },
      yaxis: {
        title: {
          text: "Fuel Amount"
        }
      },
      fill: {
        opacity: 1
      },
      tooltip: {
        y: {
          formatter: function (val) {
            return " " + val + " L";
          }
        }
      }
    };
  }

}
