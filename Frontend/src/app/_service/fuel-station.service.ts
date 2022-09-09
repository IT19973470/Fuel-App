import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FuelStationService {

  constructor(private http: HttpClient) {
  }

  addFuelStation(fuelStation): Observable<any> {
    return this.http.post<any>(environment.backend_url + "/fuel_station/addFuelStation", fuelStation);
  }

  addFuelStock(fuelStock): Observable<any> {
    return this.http.post<any>(environment.backend_url + "/fuel_station/addFuelStock", fuelStock);
  }

  newFuelStation() {
    return {
      id: '',
      name: '',
      address: '',
      contactNumber: '',
      chassisNumber: '',
      appUser: {
        email: '',
        password: ''
      }
    }
  }

  newFuelStock() {
    return {
      id: '',
      fuelType: '',
      amount: 0,
      driver: '',
      availability: false,
      vehicleNumber: '',
      actualArrival: '',
      fuelStation: {
        id: ''
      }
    }
  }
}
