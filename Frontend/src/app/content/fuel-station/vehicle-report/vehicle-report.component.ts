import { Component, OnInit } from '@angular/core';
import {FuelStationService} from "../../../_service/fuel-station.service"
import {Router} from "@angular/router"
import html2canvas from "html2canvas"
import {jsPDF} from "jspdf"
import {FuelPumperService} from "../../../_service/fuel-pumper.service"

@Component({
  selector: 'app-vehicle-report',
  templateUrl: './vehicle-report.component.html',
  styleUrls: ['./vehicle-report.component.css']
})
export class VehicleReportComponent implements OnInit {
 data =[]
  types=[]
  type;
  constructor(private fuelStationS: FuelStationService, private router: Router) {
    this.getType()
  }
  ngOnInit(): void {
    this.getVehicleReport();
    this.getVehicleType();
  }

  getVehicleReport(){
    this.fuelStationS.getVehicleReport(JSON.parse(localStorage.getItem('user') as string)['id']).subscribe((data) => {
      console.log(data)
      this.data=data;
    })

  }

  getVehicleType(){
    this.fuelStationS.getAllVehicleTypes().subscribe((types) => {
      console.log(types)
      this.types = types
    })
  }

  getType(){
    console.log(this.type)
    if(this.type===undefined){
      this.ngOnInit()
    }
    else{
      this.fuelStationS.getTypes(this.type,JSON.parse(localStorage.getItem('user') as string)['id']).subscribe((data) => {
        this.data = data
      })
    }
  }

  sendToPdf() {
    let data = document.getElementById('pdf');  //Id of the table
    html2canvas(data).then(canvas => {
      // Few necessary setting options
      let imgWidth = 305;
      // let pageHeight = 350;
      let imgHeight = canvas.height * imgWidth / canvas.width;
      let heightLeft = imgHeight;

      const contentDataURL = canvas.toDataURL('image/png')
      let pdf = new jsPDF('l', 'mm', 'a4'); // A4 size page of PDF
      let position = 10;
      pdf.addImage(contentDataURL, 'PNG', 10, position, imgWidth, imgHeight)
      pdf.save('VehicleReport.pdf'); // Generated PDF
    });
  }

  result
  downloadReport(){
    this.fuelStationS.getAllVehicleDetailsReport().subscribe(data => {
      this.result = data;
      let base64String = this.result.response;
      // @ts-ignore
      this.downloadPdf(base64String, "Vehicle Details Report");
    })
  }

  downloadPdf(base64String: string, fileName: string) {
    const source = `data:application/pdf;base64,${base64String}`;
    const link = document.createElement("a");
    link.href = source;
    link.download = `${fileName}.pdf`
    link.click();
  }



}


