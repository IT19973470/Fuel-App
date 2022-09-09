import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {

  isModalTableDetails = {
    text: '',
    openTable: false,
    foundLetter: ''
  };

  constructor() { }

  ngOnInit(): void {
  }

  isTrueOrFalseDetails(reply) {
    this.isModalTableDetails.openTable = reply;
  }

  getUser() {
    return JSON.parse(localStorage.getItem('user'))
  }
}
