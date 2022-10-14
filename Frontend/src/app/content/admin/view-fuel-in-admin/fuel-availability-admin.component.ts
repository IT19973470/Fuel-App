import { Component, OnInit } from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";
import {Router} from "@angular/router";
import {FuelPumperService} from '../../../_service/fuel-pumper.service';
import {Result} from "../../fuel_pumper/result";

@Component({
  selector: 'app-fuel-availability-admin',
  templateUrl: './fuel-availability-admin.component.html',
  styleUrls: ['./fuel-availability-admin.component.css']
})
export class FuelAvailabilityAdminComponent implements OnInit {

  // data = []
  data;
  fuelTypes = [];
  fuelType;
  destination;
  d;
  destinations =[
    "India",
    "Russia",
    "China",
    "Pakistan"
  ];
  result: Result=new Result();

  constructor(private fuelAdminService: FuelAdminService, private router: Router,private fuelPumperService: FuelPumperService) {
    this.data = this.fuelAdminService.newAddFuelStock();
    this.destination = fuelAdminService.newAddFuelStock().stockFrom;
    console.log(this.destination + "]]]")
    this.fuelType = this.fuelAdminService.newFuelType();
  }

  ngOnInit(): void {
    this.d = (document.getElementById('dest') as HTMLFontElement)['value'];
    this.getStockIn();
    this.getAllFuelTypes();
  }

  getAllFuelTypes(){
    this.fuelAdminService.getFuelTypes().subscribe(data => {
      this.fuelTypes = data;
      console.log(this.fuelTypes)
    })
  }

  getStockInListByFuelType(type){
    console.log(type)
    this.fuelAdminService.getStockInListByFuelType(type).subscribe(data => {
        this.data = data;
      this.getAllFuelTypes();
    })
  }

  getStockInByStockFrom(d){
    // var d = (document.getElementById('dest') as HTMLFontElement)['value'];
    console.log(this.d + ".................")
    this.fuelAdminService.getStockInByStockFrom(d).subscribe(data => {
      this.data = data
    })
  }

  getStockIn() {
    this.fuelAdminService.getFuelAdminStockIn().subscribe(res => {
      // console.log(res);
      this.data = res;
      // this.fuelAdminService.fuelIn = res;
      // console.log(this.data);
    })
  }


  updateStockIn(d) {
    console.log(this.fuelAdminService.fuelIn)

    this.fuelAdminService.fuelIn = d;
      this.router.navigate(['/update-stockIn']);
    // });
  }

  deleteStockIn(id: string){
    this.fuelAdminService.deleteStockIn(id).subscribe(data => {
      this.getStockIn();
    })
  }

  downloadReport() {
    this.fuelAdminService.stockInReport().subscribe(data => {
      this.result = data;
      let base64String = this.result.response;
      // @ts-ignore
      this.downloadPdf(base64String, "Stocks in Details Report");
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
