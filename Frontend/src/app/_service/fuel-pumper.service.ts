import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Result} from "../content/fuel_pumper/result";


@Injectable({
  providedIn: 'root'
})
export class FuelPumperService {

  private baseURL = environment.backend_url;

  constructor(private http: HttpClient) {
  }

  addCustomerFuel(customerFuel): Observable<any> {
    console.log(customerFuel)
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

  markTimeOutAttendance(fuelPumperAttendance): Observable<any> {
    console.log(fuelPumperAttendance)
    return this.http.put<any>(environment.backend_url + '/fuelPumper/markTimeOutAttendance/'+fuelPumperAttendance.id, fuelPumperAttendance);
  }

  getAttendance(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuelPumper/getAttendance');
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

  getVehicleCountAndFuelAmount(vehicleType: string): Observable<any> {
    return this.http.get<any>(`${this.baseURL+"/fuelPumper/getVehicleCountAndFuelAmount"}/${vehicleType}`);
  }

  getAllVehicleDetailsReport():Observable<Result>{
    return this.http.get<Result>(`${this.baseURL+"/fuelPumper/allVehicleDetailsReport"}`);
  }

  getAllVehicleTypes(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuelPumper/getAllVehicleTypes');
  }

  getAllFuelRecord(startDate:string,endDate:string):Observable<any>{
    return this.http.get<any>(`${this.baseURL+"/fuelPumper/getAllFuelRecord"}/${startDate}/${endDate}`);
  }

  getAllFuelRecordChart(startDate:string,endDate:string):Observable<any>{
    return this.http.get<any>(`${this.baseURL+"/fuelPumper/getAllFuelRecordChart"}/${startDate}/${endDate}`);
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
      timeIn: Date,
      timeOut: '',
      fuelPumper: {
        nic: ''
      }
    };
  }

  newVehicleType(){
    return{
      id:'',
      name: '',
      fuelOrder: ''
    }
  }
}
