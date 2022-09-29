import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

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
  places
  districtPlaces = []

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
    })
  }

  getPlaces(district) {
    this.districtPlaces = []
    this.districtPlaces = this.places.filter(place => {
      return place.district == district
    })
    console.log(this.districtPlaces)
    return this.districtPlaces;
  }
}
