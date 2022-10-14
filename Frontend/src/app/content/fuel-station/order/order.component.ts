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
  fuelOrder=[]
  order;
  fuelType
  chatyou=[];
  c ={
    chatId:1,
    chaterName:"",
    msg:""
  }

  constructor(private router: Router, private fuelStationS: FuelStationService,  private confirmBox: ConfirmBoxService) {
    this.c.chaterName = localStorage.getItem('user') !== null ? JSON.parse(localStorage.getItem('user') as string)['email'] : ''
    console.log(this.c.chaterName)
    this.order=fuelStationS.order()
    // this.startTimer();
  }

  addorder(){

    this.fuelStationS.getFuelStationDetails(JSON.parse(localStorage.getItem('user') as string)['id']).subscribe((d) => {
      console.log(d)
      this.order.fuelStation.id=d.fuelStation.id;
      this.order.status="pending"
      this.order.date=new Date();
      this.fuelStationS.addOrder(this.order).subscribe();
    })
    // this.order.date=new Date();
    // this.fuelStationS.addOrder(this.order).subscribe()

  //  this.order.fuelStation=localStorage.getItem('user').
  }


   btnupdate=0;
   btnadd=0
  id;
  getDataorder(data){
    this.btnupdate=0
    this.btnadd=1
    this.order.fuelAdmin.nic=data.nic
  }
  getDataorder1(data){
    this.btnupdate=1
    this.btnadd=0
    this.id=data.orderData.id
    console.log(data.nic)
    this.order.fuelType=data.orderData.fuelType
    this.order.amount=data.orderData.amount

    console.log(data)
    this.order.fuelAdmin.nic=data.nic
  }

  updateorder(){
    console.log(this.id)
    this.fuelStationS.updateOrder(this.id,this.order).subscribe()
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
    this.getOrderDetails()
  }
  getOrderDetails(){
    this.fuelStationS.getOrder(JSON.parse(localStorage.getItem('user') as string)['id']).subscribe((d)=>{
      console.log(d)
      this.fuelOrder=d
    })
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
