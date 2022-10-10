import {Component, OnInit} from '@angular/core';
import {FuelPumperService} from '../../../_service/fuel-pumper.service';
import {Result} from "../result";


@Component({
  selector: 'app-vehicle-details',
  templateUrl: './vehicle-details.component.html',
  styleUrls: ['./vehicle-details.component.css']
})
export class VehicleDetailsComponent implements OnInit {


  vehicles = [];
  totalVehicleCount: number;
  totalFuelAmount: number = 0;
  vehiclesByType = [];
  vehiclesByDate = [];
  vehicleType;
  type;
  date: any;
  result: Result=new Result();
  vehicleTypes = [];


  constructor(private fuelPumperService: FuelPumperService) {
    this.vehicleType = this.fuelPumperService.newVehicleType();
  }

  ngOnInit(): void {
    // this.getAllVehicles();
    this.getAllVehicleTypes();
  }

  getAllVehicleTypes(){
    this.fuelPumperService.getAllVehicleTypes().subscribe(data => {
      this.vehicleTypes = data;
      console.log(data)
    })
  }

  getAllVehicles() {
    this.totalFuelAmount = 0;
    this.totalVehicleCount = 0;
    let vehiclesArr = [];
    this.fuelPumperService.getAllVehicleDetails().subscribe(vehicles => {
      for (let vehicleObj of vehicles) {

        let vehicleNumber = vehiclesArr.find(vehicle => {
          return vehicle == vehicleObj.customer.vehicleNumber
        })
        if (vehicleNumber === undefined) {
          vehiclesArr.push(vehicleObj.customer.vehicleNumber)
        }

      }

      this.vehicles = vehicles;

      this.totalVehicleCount = vehiclesArr.length;
      for (var i of this.vehicles) {
        this.totalFuelAmount = this.totalFuelAmount + i.fuelPumped;
      }
    })
  }

  getVehiclesByType(type: string) {
    this.totalFuelAmount = 0;
    let vehiclesArr = [];
    this.fuelPumperService.getVehicleDetailsByType(type).subscribe(vehicles => {
      for (let vehicleObj of vehicles) {

        let vehicleNumber = vehiclesArr.find(vehicle => {
          return vehicle == vehicleObj.customer.vehicleNumber
        })
        if (vehicleNumber === undefined) {
          vehiclesArr.push(vehicleObj.customer.vehicleNumber)
        }

      }
      this.vehicles = vehicles;

      this.totalVehicleCount = vehiclesArr.length;
      for (var i of this.vehicles) {
        this.totalFuelAmount += i.fuelPumped;
      }


    })
  }

  searchByType() {
    if (this.vehicleType == 'All') {
      this.getAllVehicles();
    }else{
      this.getVehiclesByType(this.vehicleType);
    }
  }

  searchByDate() {
    this.totalFuelAmount = 0;
    this.totalVehicleCount = 0;
    let vehiclesArr = [];
    this.fuelPumperService.getVehicleDetailsByDate(this.date).subscribe(vehicles => {
      for (let vehicleObj of vehicles) {

        let vehicleNumber = vehiclesArr.find(vehicle => {
          return vehicle == vehicleObj.customer.vehicleNumber
        })
        if (vehicleNumber === undefined) {
          vehiclesArr.push(vehicleObj.customer.vehicleNumber)
        }

      }
      this.vehicles = vehicles;
      this.totalVehicleCount = vehiclesArr.length;
      for (var i of this.vehicles) {
        this.totalFuelAmount += i.fuelPumped;
      }
    })
  }

  downloadReport(){
    this.fuelPumperService.getAllVehicleDetailsReport().subscribe(data => {
      this.result = data;
      let base64String = this.result.response;
      // @ts-ignore
      this.downloadPdf(base64String, "All Vehicle Details Report");
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
