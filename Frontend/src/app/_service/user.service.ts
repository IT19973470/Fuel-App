import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  placesFound = new Subject<any>()

  constructor(private http: HttpClient) {
    this.setDistricts()
  }

  login(user): Observable<any> {
    return this.http.post<any>(environment.backend_url + "/user/login", user);
  }

  getFuelStationPlaces(): Observable<any> {
    return this.http.get<any>(environment.backend_url + "/user/getPlaces");
  }

  districts = [];
  places = []
  districtPlaces = [];
  place;
  setPlace = new Subject()
  setDistrictPlaces = new Subject<[]>()

  setDistricts() {
    this.getFuelStationPlaces().subscribe(places => {
      this.places = places;
      for (let place of places) {
        let district = this.districts.find(districtObj => {
          return districtObj == place.district
        })
        if (district === undefined) {
          this.districts.push(place.district)
        }
      }
      if (JSON.parse(localStorage.getItem('user')) !== null && JSON.parse(localStorage.getItem('user')).customer !== null) {
        this.getPlaces(JSON.parse(localStorage.getItem('user')).customer.fuelStationPlace.district)
        this.setDistrictPlaces.next(places)
        this.districtPlaces = places
        this.setPlace.next(JSON.parse(localStorage.getItem('user')).customer.fuelStationPlace)
        this.place = JSON.parse(localStorage.getItem('user')).customer.fuelStationPlace
        this.placesFound.next()
        // this.place = JSON.parse(localStorage.getItem('user')).customer.fuelStationPlace.place;
      }
    })
  }

  getPlaces(district) {
    this.districtPlaces = []
    this.districtPlaces = this.places.filter(place => {
      return place.district == district
    })
    return this.districtPlaces;
  }

  getFuelTypes(): Observable<any> {
    return this.http.get<any>(environment.backend_url + "/fuelPumper/getFuelTypes");
  }

  getVehicleTypes(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuelPumper/getAllVehicleTypes');
  }
}
