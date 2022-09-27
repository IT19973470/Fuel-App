import { Component, OnInit } from '@angular/core';
import {FuelPumperService} from '../../../_service/fuel-pumper.service';

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
  vehicleType: string;
  type: string;
  date: any;



  constructor(private fuelPumperService: FuelPumperService) { }

  ngOnInit(): void {
    // this.getAllVehicles();
  }

  getAllVehicles(){
    this.fuelPumperService.getAllVehicleDetails().subscribe(data => {
      this.vehicles = data;
      this.totalVehicleCount = this.vehicles.length;
      for(var i of this.vehicles){
        this.totalFuelAmount = this.totalFuelAmount+ this.vehicles[i].fuelPumped;
        console.log("//////")
      }
      console.log(this.totalFuelAmount + "11111111")
    })
  }

  getVehiclesByType(type: string){
    this.fuelPumperService.getVehicleDetailsByType(type).subscribe(data => {
      this.vehicles = data;
      this.totalVehicleCount = this.vehicles.length;
      for(var i of this.vehicles){
        this.totalFuelAmount += this.vehicles[i].fuelPumped;
      }

      console.log(data)
    })
  }

  searchByType(){
    if(this.vehicleType == 'All'){
      this.getAllVehicles();
    }else if(this.vehicleType == 'Car'){
      this.getVehiclesByType(this.vehicleType);
    }else if(this.vehicleType == 'Van'){
      this.getVehiclesByType(this.vehicleType);
    }else if(this.vehicleType == 'Bike'){
      this.getVehiclesByType(this.vehicleType);
    }else if(this.vehicleType == 'Bus'){
      this.getVehiclesByType(this.vehicleType);
    }else if(this.vehicleType == 'Three Wheeler'){
      this.getVehiclesByType(this.vehicleType);
    }else if(this.vehicleType == 'Lorry'){
      this.getVehiclesByType(this.vehicleType);
    }

  }

  searchByDate(){
    this.fuelPumperService.getVehicleDetailsByDate(this.date).subscribe(data => {
      this.vehicles = data;
    })
  }

}
