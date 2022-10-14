import {Component, OnInit} from '@angular/core';
import {CustomerService} from '../../../_service/customer.service';
import {Router} from '@angular/router';
import * as htmlToImage from 'html-to-image';
import html2canvas from "html2canvas";
import {jsPDF} from "jspdf";

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  customer;
  displayImg = false;

  constructor(private customerS: CustomerService, private router: Router) {
    this.customer = this.customerS.newCustomer();
  }

  ngOnInit(): void {
    // console.log(this.customer)
    // this.customerS.getCustomer(JSON.parse(localStorage.getItem('user')).email, JSON.parse(localStorage.getItem('user')).contactNumber).subscribe(customer => {
    //   this.customer = customer;
    //   console.log(this.customer)
    this.getCustomer();
    // });
  }

  getCustomer() {
    this.customerS.getCustomerByVehicle(JSON.parse(localStorage.getItem('user')).customer.vehicle.secKey).subscribe(customer => {
      this.customer = customer;
    });
  }

  updateCustomer() {
    this.customerS.customer = this.customer;
    this.router.navigate(['/update_profile']);
  }

  deleteCustomer() {
    this.customerS.deleteCustomer(JSON.parse(localStorage.getItem('user')).id).subscribe(() => {
      this.router.navigate(['/login']);
    })
  }

  regenerateQR() {
    this.customerS.regenerateQR(JSON.parse(localStorage.getItem('user')).customer.vehicle.vehicleNumber).subscribe((vehicle) => {
      let user = JSON.parse(localStorage.getItem('user'))
      user.customer.vehicle = vehicle
      this.customer.vehicle = vehicle
      localStorage.setItem('user', JSON.stringify(user))
      // this.getCustomer()
    })
  }

  generateImage() {
    this.displayImg = true
    let that = this
    var node:any = document.getElementById('qrImg');
    // node.style.display = 'grid';
    htmlToImage.toJpeg(node, {quality: 0.95})
      .then(function (dataUrl) {
        // node.style.display = 'none';
        var link = document.createElement('a');
        link.download = 'my-image-name.jpeg';
        link.href = dataUrl;
        link.click();
      });
    // let data = document.getElementById('qrImg');  //Id of the table
    // data.style.display = 'block';
    // html2canvas(data).then(canvas => {
    //   // Few necessary setting options
    //   let imgWidth = 275;
    //   // let pageHeight = 350;
    //   let imgHeight = canvas.height * imgWidth / canvas.width;
    //   let heightLeft = imgHeight;
    //
    //   const contentDataURL = canvas.toDataURL('image/png')
    //   var link = document.createElement('a');
    //       link.download = 'my-image-name.jpeg';
    //       link.href = contentDataURL;
    //       link.click();
    //   // let pdf = new jsPDF('l', 'mm', 'a4'); // A4 size page of PDF
    //   // let position = 10;
    //   // pdf.addImage(contentDataURL, 'PNG', 10, position, imgWidth, imgHeight)
    //   // pdf.save('MYPdf.pdf'); // Generated PDF
    // });
  }
}
