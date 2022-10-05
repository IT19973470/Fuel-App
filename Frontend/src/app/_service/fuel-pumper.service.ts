import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FuelPumperService {

  private baseURL = environment.backend_url;

  constructor(private http: HttpClient) {
  }

  addCustomerFuel(customerFuel): Observable<any> {
    return this.http.post<any>(environment.backend_url + '/fuelPumper/addCustomerFuel', customerFuel);
  }

  deleteCustomerFuel(vehicle, fuelStation): Observable<any> {
    return this.http.delete<any>(environment.backend_url + '/fuelPumper/deleteCustomerFuel/' + vehicle + '/' + fuelStation);
  }

  addFuelPumper(fuelPumper): Observable<any> {
    return this.http.post<any>(environment.backend_url + '/fuelPumper/addFuelPumper', fuelPumper);
  }

  addFuelPumperAttendance(fuelPumperAttendance): Observable<any> {
    return this.http.post<any>(environment.backend_url + '/fuelPumper/addFuelPumperAttendance', fuelPumperAttendance);
  }

  getAllVehicleDetails(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuelPumper/getAllVehicleDetails');
  }

  getVehicleDetailsByType(vehicleType: string): Observable<any> {
    return this.http.get<any>(`${this.baseURL + '/fuelPumper/getVehicleDetailsByType'}/${vehicleType}`);
  }


  getVehicleDetailsByDate(date: any): Observable<any> {
    return this.http.get<any>(`${this.baseURL + "/fuelPumper/getVehicleDetailsByDate"}/${date}`);

  }

  getVehicleDetailsByTypeAndDate(vehicleType: string, date: any): Observable<any> {

    return this.http.get<any>(`${this.baseURL + '/fuelPumper/getVehicleDetailsByDate'}/${vehicleType}/${date}`);

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
      },
      fuelStation: {
        id: ''
      }
    };
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
    };
  }
}
