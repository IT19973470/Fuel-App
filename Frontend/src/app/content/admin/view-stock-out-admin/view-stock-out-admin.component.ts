import {Component, OnInit} from '@angular/core';
import {FuelAdminService} from "../../../_service/fuel-admin.service";
import {Router} from "@angular/router";
import {Result} from "../../fuel_pumper/result";

@Component({
  selector: 'app-view-stock-out-admin',
  templateUrl: './view-stock-out-admin.component.html',
  styleUrls: ['./view-stock-out-admin.component.css']
})
export class ViewStockOutAdminComponent implements OnInit {

  data = []
  fuelTypes = [];
  fuelType;
  result: Result = new Result();
  destinations = new Set();
  selectedDestination = ''
  dataTable=[]

  constructor(private fuelAdminService: FuelAdminService, private router: Router) {
    // this.data = this.fuelAdminService.newAddFuelStock();
    // this.destination = fuelAdminService.newAddFuelStock().stockFrom;
    // console.log(this.destination + "]]]")
    this.fuelType = this.fuelAdminService.newFuelType();
  }

  ngOnInit(): void {
    this.getStockOut();
    this.getAllFuelTypes();
  }

  getStockOut() {
    this.fuelAdminService.getFuelAdminStockOut().subscribe(res => {
      this.data = res
      this.dataTable=res
      for (let fuelStation of res) {
        this.destinations.add(fuelStation.fuelStation.address)
      }
    })
  }

  searchAddress() {
    this.dataTable = this.data.filter(fuelStation => {
      return fuelStation.fuelStation.address == this.selectedDestination
    })
  }

  updateStockOut(d) {
    this.fuelAdminService.fuelOut = d;
    this.router.navigate(['/update-stockOut']);
    // });
  }

  deleteStockOut(id: string) {
    this.fuelAdminService.deleteStockOut(id).subscribe(data => {
      this.getStockOut();
    });
  }

  getAllFuelTypes() {
    this.fuelAdminService.getFuelTypes().subscribe(data => {
      this.fuelTypes = data;
    })
  }

  getStockOutListByFuelType(type) {
    this.fuelAdminService.getStockOutListByFuelType(type).subscribe(data => {
      this.data = data;
      console.log(">>>",type)
      console.log(this.data)
      this.getAllFuelTypes();
    });

  }

  downloadReport() {
    this.fuelAdminService.stockOutReport().subscribe(data => {
      this.result = data;
      let base64String = this.result.response;
      // @ts-ignore
      this.downloadPdf(base64String, "Stocks Out Details Report");
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
