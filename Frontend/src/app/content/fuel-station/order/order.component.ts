import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FuelStationService} from "../../../_service/fuel-station.service";
import {FuelPumperService} from "../../../_service/fuel-pumper.service";
import {ConfirmBoxService} from "../../../_service/confirm-box.service";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  fuelAdmin = [];
  chatyou=[];
  c ={
    chatId:1,
    chaterName:"",
    msg:""
  }
  you
  constructor(private router: Router, private fuelStationS: FuelStationService,  private confirmBox: ConfirmBoxService) {
    this.c.chaterName = localStorage.getItem('user') !== null ? JSON.parse(localStorage.getItem('user') as string)['email'] : ''
    console.log(this.c.chaterName)
    // this.startTimer();
  }

  interval:any;

  // startTimer() {
  //   this.interval = setInterval(() => {
  //     this.getAllChats()
  //   },1000)
  // }
  // getAllChats() {
  //   this.fuelStationS.getAllChats().subscribe(data => {
  //     this.chatyou=data
  //     this.you=this.chatyou[0]
  //     // console.log(data)
  //
  //   })
  // }

  ngOnInit(): void {
    this.getAdminDetails()
  }

  getAdminDetails() {
    this.fuelStationS.getFuelAdmin().subscribe((d) => {
      this.fuelAdmin=d
      // console.log(d)
    })
  }

  onSubmit() {

      this.fuelStationS.addChat(this.c).subscribe( data => {
        console.log(data);
      });
  }
}
