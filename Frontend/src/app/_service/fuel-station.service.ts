import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FuelStationService {

  constructor(private http: HttpClient) {
  }

  addFuelStation(fuelStation): Observable<any> {
    return this.http.post<any>(environment.backend_url + '/fuel_station/addFuelStation', fuelStation);
  }

  addFuelStock(fuelStock): Observable<any> {
    return this.http.post<any>(environment.backend_url + '/fuel_station/addFuelStock', fuelStock);
  }

  getFuelStock(id): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuel_station/getFuelStock/' + id);
  }
  getFuelStockIn(id): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuel_station/getAvailableStocks/' + id);
  }
  getAttendence(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuel_station/getAttendence/');
  }
  getFuelAdmin(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuel_station/getAdmin/');
  }
  getAllChats(): Observable<any>{
    return this.http.get<any>(environment.backend_url + '/fuel_station/getchat');
  }
  addChat(setItem: any): Observable<any>{
    console.log(setItem);
    return this.http.post(environment.backend_url + '/fuel_station/addChat', setItem);
  }


  newFuelStation() {
    return {
      id: '',
      name: '',
      address: '',
      chassisNumber: '',
      appUser: {
        email: '',
        password: '',
        contactNumber: ''
      }
    };
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
    };
  }
}
