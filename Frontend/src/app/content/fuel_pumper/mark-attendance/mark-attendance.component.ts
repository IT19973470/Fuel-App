import {Component, OnInit} from "@angular/core"
import {FuelPumperService} from "../../../_service/fuel-pumper.service"
import {DatePipe} from "@angular/common"
import {NotifierService} from "angular-notifier"

@Component({
  selector: "app-mark-attendance",
  templateUrl: "./mark-attendance.component.html",
  styleUrls: ["./mark-attendance.component.css"]
})
export class MarkAttendanceComponent implements OnInit {

  fuelPumperAttendance
  attendance = []
  attendanceId

  constructor(private fuelPumperS: FuelPumperService, private datePipe: DatePipe, private notifierService: NotifierService) {
    this.fuelPumperAttendance = fuelPumperS.newFuelPumperAttendance()
  }

  ngOnInit(): void {
    this.getCurDate()
    this.getAttendance()
  }

  addFuelPumperAttendance() {
    this.fuelPumperAttendance.fuelPumper.nic = JSON.parse(localStorage.getItem("user")).id
    console.log(this.fuelPumperAttendance)
    this.fuelPumperS.addFuelPumperAttendance(this.fuelPumperAttendance).subscribe(data => {

      this.notifierService.notify("success", "Marked Successfully")
      localStorage.setItem("fuelPumperAttendance", data.id)
      // console.log(data)
    })
  }

  markTimeOutAttendance() {
    // console.log(localStorage.getItem("fuelPumperAttendance"))
    this.fuelPumperAttendance.fuelPumper.nic = JSON.parse(localStorage.getItem("user")).id
    this.fuelPumperAttendance.id = JSON.parse(localStorage.getItem("fuelPumperAttendance"))
    // this.fuelPumperAttendance.id = this.attendanceId
    console.log(this.fuelPumperAttendance)
    this.fuelPumperS.markTimeOutAttendance(this.fuelPumperAttendance).subscribe(data => {

      this.notifierService.notify("success", "Marked Successfully")

    })
  }

  getAttendance() {
    this.fuelPumperAttendance.id = JSON.parse(localStorage.getItem("user")).id
    this.fuelPumperS.getAttendance().subscribe(data => {
      this.attendance = data
      for (var i in data) {
        if (data[i].fuelPumper.nic == this.fuelPumperAttendance.id) {
          this.attendanceId = data[i].id
        }
      }
      console.log(data)
    })
  }

  getName() {
    return JSON.parse(localStorage.getItem("user")).fuelPumper.name
  }

  getCurDate() {
    this.fuelPumperAttendance.markedAt = this.datePipe.transform(new Date(), "yyyy-MM-dd")
  }
}
