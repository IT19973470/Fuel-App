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

  addNextFuelStock(fuelStock): Observable<any> {
    return this.http.post<any>(environment.backend_url + "/fuel_station/addNextFuelStock", fuelStock);
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
  getAttendencBydate(startDate:string,endDate:string): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuel_station/getAttendence/'+startDate+"/"+endDate);
  }
  getFuelAdmin(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuel_station/getAdmin/');
  }
  getAllChats(): Observable<any>{
    return this.http.get<any>(environment.backend_url + '/fuel_station/getchat');
  }
  addChat(setItem: any): Observable<any> {
    console.log(setItem);
    return this.http.post(environment.backend_url + '/fuel_station/addChat', setItem);
  }
  getFuelStationDetails(id): Observable<any> {
    console.log(id)
    return this.http.get<any>(environment.backend_url + "/fuel_station/getFuelStation/"+id);
  }
  addOrder(data)   : Observable<any> {
   return this.http.post<any>(environment.backend_url + '/fuel_station/order', data)
  }
  getOrder(id)   : Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuel_station/getorder/'+id)
  }
  updateOrder(id,data): Observable<any> {
    return this.http.put<any>(environment.backend_url + '/fuel_station/updateOrder/'+id, data);
  }
  getVehicleReport(id)   : Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuel_station/getVehicleReport/'+id)
  }
  getAllVehicleTypes(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuel_station/getAllVehicleTypes/');
  }

  deleteOrder(id): Observable<any> {
    return this.http.delete<any>(environment.backend_url + '/fuel_station/deleteOrder/' + id );
  }
  getTypes(type,id): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuel_station/getVehicleReport/' + id+"/"+ type);
  }

  newFuelStation() {
    return {
      id: '',
      name: '',
      address: '',
      district: '',
      latitude: 0,
      longitude: 0,
      fuelStationPlace: {
        id: ''
      },
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
      fuelType: {
        id: ''
      },
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


  order() {
    return {
      id: '',
      fuelType: '',
      location: '',
      amount: '',
      date: '',
      status:'',
      fuelAdmin:{
        nic:''
      },
      fuelStation:{
        id:''
      }
    };
  }



}
