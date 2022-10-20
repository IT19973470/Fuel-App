import {Component, OnInit} from "@angular/core"
import {FuelPumperService} from "../../../_service/fuel-pumper.service"
import {DatePipe} from "@angular/common"
import {NotifierService} from "angular-notifier"
import {AlertBoxService} from "../../../alert-box/alert-box.service";

@Component({
  selector: "app-mark-attendance",
  templateUrl: "./mark-attendance.component.html",
  styleUrls: ["./mark-attendance.component.css"]
})
export class MarkAttendanceComponent implements OnInit {

  fuelPumperAttendance
  attendance = []
  attendanceId
  alertBox = {
    alert: false,
    msg: '',
    value: ''
  };




  constructor(private fuelPumperS: FuelPumperService, private datePipe: DatePipe, private notifierService: NotifierService, private alertService: AlertBoxService) {
    this.fuelPumperAttendance = fuelPumperS.newFuelPumperAttendance()
  }

  ngOnInit(): void {
    this.getCurDate()
    this.getAttendance()
  }

  addFuelPumperAttendance() {
    this.alertBox.alert = true;
    this.alertBox.msg = 'Do you want to mark time in?';
    this.alertService.reply.observers = [];
    this.fuelPumperAttendance.fuelPumper.nic = JSON.parse(localStorage.getItem("user")).id;
    this.fuelPumperS.addFuelPumperAttendance(this.fuelPumperAttendance).subscribe(data => {
      this.alertService.reply.subscribe(reply => {
        if (reply) {
          this.notifierService.notify("success", "Marked Successfully")
          localStorage.setItem("fuelPumperAttendance", data.id)
        }
        this.alertBox.alert = false;
      })

      // console.log(data)
    })
  }

  markTimeOutAttendance() {
    this.alertBox.alert = true;
    this.alertBox.msg = 'Do you want to mark time out?';
    this.alertService.reply.observers = [];
    // console.log(localStorage.getItem("fuelPumperAttendance"))
    this.fuelPumperAttendance.fuelPumper = JSON.parse(localStorage.getItem("user"));
    // this.fuelPumperAttendance.id = JSON.parse(localStorage.getItem("fuelPumperAttendance"));
    console.log(this.fuelPumperAttendance.fuelPumper)
    this.fuelPumperAttendance.id = this.attendanceId;
    console.log(this.fuelPumperAttendance.id + "[[[");
    this.alertService.reply.subscribe(reply => {
      if (reply) {
        this.fuelPumperS.markTimeOutAttendance(this.fuelPumperAttendance).subscribe(data => {

          this.notifierService.notify("success", "Marked Successfully")

        })
      }
      this.alertBox.alert = false;
    })

  }



  getAttendance() {
    this.fuelPumperAttendance.id = JSON.parse(localStorage.getItem("user")).id
    this.fuelPumperS.getAttendance().subscribe(data => {
      this.attendance = data
      console.log(this.fuelPumperAttendance.id + "......")
      console.log(data)
      for (var i in data) {
        if (data[i].fuelPumper.nic == this.fuelPumperAttendance.id  && data[i].markedAt == this.fuelPumperAttendance.markedAt) {
          this.attendanceId = data[i].id;
          console.log(this.attendanceId);
// =======
//   getAttendance(){
//     this.fuelPumperAttendance.id = JSON.parse(localStorage.getItem('user')).id;
//     console.log(this.fuelPumperAttendance)
//     this.fuelPumperS.getAttendance().subscribe(data =>{
//       this.attendance = data;
//       console.log(this.attendance)
//       for(var i in data){
//         if(data[i].fuelPumper.nic == this.fuelPumperAttendance.id){
//           this.attendanceId = data[i].id;
// >>>>>>> Stashed changes
        }
      }
      console.log(this.attendanceId)
    })
  }

  getName() {
    return JSON.parse(localStorage.getItem("user")).fuelPumper.name
  }

  getCurDate() {
    this.fuelPumperAttendance.markedAt = this.datePipe.transform(new Date(), "yyyy-MM-dd")
  }
}
