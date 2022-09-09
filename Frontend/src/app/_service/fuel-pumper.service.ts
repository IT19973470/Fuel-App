import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FuelPumperService {

  constructor(private http: HttpClient) {
  }

  addCustomerFuel(customerFuel): Observable<any> {
    return this.http.post<any>(environment.backend_url + "/fuelPumper/addCustomerFuel", customerFuel);
  }

  deleteCustomerFuel(vehicle, fuelStation): Observable<any> {
    return this.http.delete<any>(environment.backend_url + "/fuelPumper/deleteCustomerFuel/" + vehicle + "/" + fuelStation);
  }

  addFuelPumper(fuelPumper): Observable<any> {
    return this.http.post<any>(environment.backend_url + "/fuelPumper/addFuelPumper", fuelPumper);
  }

  addFuelPumperAttendance(fuelPumperAttendance): Observable<any> {
    return this.http.post<any>(environment.backend_url + "/fuelPumper/addFuelPumperAttendance", fuelPumperAttendance);
  }

  newFuelPumper() {
    return {
      nic: '',
      name: '',
      address: '',
      appUser: {
        email: '',
        password: '',
        contactNumber: '',
      }
    }
  }

  newFuelPumperAttendance() {
    return {
      id: '',
      markedAt: '',
      timeIn: '',
      timeOut: '',
      fuelPumper: {
        nic: ''
      }
    }
  }
}
