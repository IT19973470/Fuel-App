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

  addCustomerFuel(customerFuel): Observable<any> {
    return this.http.post<any>(environment.backend_url + "/fuel_station/addCustomerFuel", customerFuel);
  }

  deleteCustomerFuel(vehicle, fuelStation): Observable<any> {
    return this.http.delete<any>(environment.backend_url + "/fuel_station/deleteCustomerFuel/" + vehicle + "/" + fuelStation);
  }

  addFuelPumper(fuelPumper): Observable<any> {
    return this.http.post<any>(environment.backend_url + "/fuel_station/addFuelPumper", fuelPumper);
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

  newFuelPumper() {
    return {
      nic: '',
      name: '',
      address: '',
      contactNumber: '',
      appUser: {
        email: '',
        password: ''
      }
    }
  }
}
