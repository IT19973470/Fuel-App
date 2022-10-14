import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";
import {Result} from "../../fuel_pumper/result";

@Component({
  selector: 'app-view-report',
  templateUrl: './view-report.component.html',
  styleUrls: ['./view-report.component.css']
})
export class ViewReportComponent implements OnInit {
  result: Result=new Result();
  constructor(private fuelAdminService: FuelAdminService) { }

  ngOnInit(): void {
  }

  StockInPieChart() {
    this.fuelAdminService.StockInPieChart().subscribe(data => {
      this.result = data;
      let base64String = this.result.response;
      // @ts-ignore
      this.downloadPetrolPdf(base64String, "Total Stock In Report");
    })
  }

  downloadPetrolPdf(base64String: string, fileName: string) {
    const source = `data:application/pdf;base64,${base64String}`;
    const link = document.createElement("a");
    link.href = source;
    link.download = `${fileName}.pdf`
    link.click();
  }

  StockOutPieChart() {
    this.fuelAdminService.StockOutPieChart().subscribe(data => {
      this.result = data;
      let base64String = this.result.response;
      // @ts-ignore
      this.downloadDieselPdf(base64String, "Total Stock Out Report");
    })
  }

  downloadDieselPdf(base64String: string, fileName: string) {
    const source = `data:application/pdf;base64,${base64String}`;
    const link = document.createElement("a");
    link.href = source;
    link.download = `${fileName}.pdf`
    link.click();
  }
}
