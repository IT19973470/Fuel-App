import { Component, OnInit } from '@angular/core';
import {FuelStationService} from "../../../_service/fuel-station.service"
import {Router} from "@angular/router"

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

  chartOptions;
  application;

  deliveryItemDetails = [];
  weeklyDeliveries = [];
  applicationdata=[]

  obj= {
    pd:{
      deliveries:0,
      completed: 0,
      cancelled: 0
    },
    id:{
      deliveries:0,
      completed: 0,
      cancelled: 0
    },
    pid: {
      deliveries: 0,
      completed: 0,
      cancelled: 0
    }
  }

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
  // total3={
  //   name:'Not-Completed',
  //   data: [],
  //   color: '#ff0a03'
  // }

pumpers=[]


  constructor(private fuelStationS: FuelStationService,private router: Router) {
     this.fillChart();

  }

  getAllApplication() {
    this.fuelStationS.getAttendence().subscribe((application) => {
      // console.log(application)
      this.applicationdata = application;
      this.chartdata(application)
      // this.setvalues(application)
    })
  }
 chartdata(application){
    for (let data of application){
      this.pumpers.push(data.fuelPumperAttendance.fuelPumper.name)
       this.chartOptions.xaxis.categories.push(data.fuelPumperAttendance.fuelPumper.name)
      console.log(data.fuelPumperAttendance.timeIn.substring(0,2))
      console.log(data.fuelPumperAttendance.timeOut.substring(0,2))
      this.total.data.push(data.fuelPumperAttendance.timeOut.substring(0,2)-data.fuelPumperAttendance.timeIn.substring(0,2))
        // console.log(data.fuelPumperAttendance.timeOut.substring(0,2)-data.fuelPumperAttendance.timeIn.substring(0,2))

    }
   this.chartOptions.series.push(this.total)
   // this.total.data.push(Math.floor(data.fuelPumperAttendance.timeIn)-Math.floor(data.fuelPumperAttendance.timeOut))
  // console.log(Math.floor(data.fuelPumperAttendance.timeIn.now()))
   // console.log( this.total.data)
 //  this.chartOptions.xaxis.categories.push(this.pumpers)
 //    this.chartOptions.series.push(this.pumpers,this.total)
   // this.chartOptions.xaxis.categories.push(data.fuelPumperAttendance.fuelPumper.name)
}

  setvalues(application){
    //  console.log(application)
    this.applicationdata=application
    //console.log(this.applicationdata)

    let ApplicationReq;
    let itemReq=0;
    let itemcountCom=0;
    let passcount=0;
    let passcountCom=0;
    let itemPass=0;
    let itempasscountCom=0;

    for(let x=0; x<=this.applicationdata.length-1; x++){
      ApplicationReq=this.applicationdata[x]
      if(ApplicationReq.type=="P")
      {
        passcount++

        if(ApplicationReq.tokenDTO==null){
          console.log(ApplicationReq)
          passcountCom++

        }
      }
      if(ApplicationReq.type=="I")
      {
        itemReq++
        if(ApplicationReq.tokenDTO==null){
          itemcountCom++
        }
      }
      if(ApplicationReq.type=="IP"){
        itemPass++
        if(ApplicationReq.tokenDTO==null){
          itempasscountCom++
        }
      }


    }
    // console.log(passcountCom)
    // console.log(itemcountCom)
    // console.log(itempasscountCom)
    this.obj.pd.deliveries=passcount
    this.obj.pd.completed=passcount-passcountCom
    this.obj.pd.cancelled=passcountCom
    this.obj.id.deliveries=itemReq
    this.obj.id.completed=itemReq-itemcountCom
    this.obj.id.cancelled= itemcountCom
    this.obj.pid.deliveries=itemPass
    this.obj.pid.completed=itemPass-itempasscountCom
    this.obj.pid.cancelled= itempasscountCom



    //console.log(this.obj.pd.deliveries)
    this.weeklyDeliveries.push(this.obj)
    // this.total.data.push(passcount,itemReq,itemPass)
    this.total2.data.push(passcount-passcountCom,itemReq-itemcountCom,itemPass-itempasscountCom)
    // this.total3.data.push(passcountCom,itemcountCom,itempasscountCom)
    // this.chartOptions.series.push(this.total,this.total2,this.total3)
    // console.log(this.total)
    // console.log(this.chartOptions)
  }

  ngOnInit(): void {
    this.fillChart();
    this.getAllApplication();
  }

  // sendToPdf() {
  //   let data = document.getElementById('pdf');  //Id of the table
  //   html2canvas(data).then(canvas => {
  //     // Few necessary setting options
  //     let imgWidth = 320;
  //     // let pageHeight = 350;
  //     let imgHeight = canvas.height * imgWidth / canvas.width;
  //     let heightLeft = imgHeight;
  //
  //     const contentDataURL = canvas.toDataURL('image/png')
  //     let pdf = new jsPDF('l', 'mm', 'a4'); // A4 size page of PDF
  //     let position = 10;
  //     pdf.addImage(contentDataURL, 'PNG', 10, position, imgWidth, imgHeight)
  //     pdf.save('MYPdf.pdf'); // Generated PDF
  //   });
  // }

  fillChart() {
    this.chartOptions = {
      series: [
        // {
        //   name:'Attandance',
        //   data: [],
        //   color: '#0c8dc0'
        // },
         {
          name:'A',
          data: ["10","10"],
          color: '#018002'
        },

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

        ]
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
