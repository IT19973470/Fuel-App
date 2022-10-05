import {Component, OnInit} from '@angular/core';
import {FuelPumperService} from '../../../_service/fuel-pumper.service';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-mark-attendance',
  templateUrl: './mark-attendance.component.html',
  styleUrls: ['./mark-attendance.component.css']
})
export class MarkAttendanceComponent implements OnInit {

  fuelPumperAttendance;

  constructor(private fuelPumperS: FuelPumperService, private datePipe: DatePipe) {
    this.fuelPumperAttendance = fuelPumperS.newFuelPumperAttendance();
  }

  ngOnInit(): void {
    this.getCurDate();
  }

  addFuelPumperAttendance() {
    this.fuelPumperAttendance.fuelPumper.nic = JSON.parse(localStorage.getItem('user')).id;
    this.fuelPumperS.addFuelPumperAttendance(this.fuelPumperAttendance).subscribe(() => {

    });
  }

  getName() {
    return JSON.parse(localStorage.getItem('user')).fuelPumper.name;
  }

  getCurDate() {
    this.fuelPumperAttendance.markedAt = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  }
}
