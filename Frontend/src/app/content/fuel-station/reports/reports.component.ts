import { Component, OnInit } from '@angular/core';
import {FuelStationService} from "../../../_service/fuel-station.service"
import {Router} from "@angular/router"
import html2canvas from "html2canvas"
import {jsPDF} from "jspdf"

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

  chartOptions;

  applicationdata=[]


  total= {
    name:'Working Hrs',
    data: [],
    color: '#0c8dc0'
  }
  total2={
    name:'Vehicle Count',
    data: [],
    color: '#018002'
  }
  startDate;
  endDate;

  // total3={
  //   name:'Not-Completed',
  //   data: [],
  //   color: '#ff0a03'
  // }

pumpers=[]


  constructor(private fuelStationS: FuelStationService,private router: Router) {
     this.fillChart();
  }

    getAttendanceByDate(){
      this.fuelStationS.getAttendencBydate(this.startDate, this.endDate).subscribe((data) => {console.log(data)
        this.applicationdata = data;
        this.chartOptions.series.length=0
        this.chartOptions.xaxis.categories.length=0
        this.total2.data=[]
        this.total.data=[]
        this.chartdata(data)})
    }

  getAllApplication() {
    this.fuelStationS.getAttendence().subscribe((application) => {
       console.log(application)
      this.applicationdata = application;
      this.chartdata(application)
      // this.setvalues(application)
    })
  }
 chartdata(application){
    for (let data of application){
      // this.pumpers.push(data.fuelPumperAttendance.fuelPumper.name)
       this.chartOptions.xaxis.categories.push(data.fuelPumperAttendance.fuelPumper.name+':-'+data.fuelPumperAttendance.markedAt)
      console.log(data.fuelPumperAttendance.timeIn.substring(0,2))
      console.log(data.fuelPumperAttendance.timeOut.substring(0,2))
      let count =data.fuelPumperAttendance.timeOut.substring(0,2)-data.fuelPumperAttendance.timeIn.substring(0,2)
      if(count<0)
        count=count*-1;
      this.total.data.push(count)
        // console.log(data.fuelPumperAttendance.timeOut.substring(0,2)-data.fuelPumperAttendance.timeIn.substring(0,2))
      this.total2.data.push(data.countdata)
    }
   // this.chartOptions.series.push(this.total)
   this.chartOptions.series=[this.total2,this.total]
   // this.total.data.push(Math.floor(data.fuelPumperAttendance.timeIn)-Math.floor(data.fuelPumperAttendance.timeOut))
  // console.log(Math.floor(data.fuelPumperAttendance.timeIn.now()))
   // console.log( this.total.data)
 //  this.chartOptions.xaxis.categories.push(this.pumpers)
 //    this.chartOptions.series.push(this.pumpers,this.total)
   // this.chartOptions.xaxis.categories.push(data.fuelPumperAttendance.fuelPumper.name)
}



  ngOnInit(): void {
    this.fillChart();
    this.getAllApplication();
  }

  sendToPdf() {
    let data = document.getElementById('pdf');  //Id of the table
    html2canvas(data).then(canvas => {
      // Few necessary setting options
      let imgWidth = 275;
      // let pageHeight = 350;
      let imgHeight = canvas.height * imgWidth / canvas.width;
      let heightLeft = imgHeight;

      const contentDataURL = canvas.toDataURL('image/png')
      let pdf = new jsPDF('l', 'mm', 'a4'); // A4 size page of PDF
      let position = 10;
      pdf.addImage(contentDataURL, 'PNG', 10, position, imgWidth, imgHeight)
      pdf.save('pumpers.pdf'); // Generated PDF
    });
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
        categories: []
      },
      yaxis: {
        title: {
          text: "Count"
        }
      },
      fill: {
        opacity: 1
      },
      tooltip: {
        y: {
          formatter: function (val) {
            return "$ " + val + " thousands";
          }
        }
      }
    };
  }
}
