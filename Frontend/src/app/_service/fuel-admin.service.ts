import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FuelAdminService {

  constructor(private http: HttpClient) {
  }

  addFuelAdmin(fuelAdmin): Observable<any> {
    return this.http.post<any>(environment.backend_url + "/fuel_admin/addFuelAdmin", fuelAdmin);
  }
  addFuelAdminStockIn(fuelAdmin): Observable<any> {
    console.log(fuelAdmin);
    return this.http.post<any>(environment.backend_url + "/fuel_admin/addFuelStock", fuelAdmin);
  }
  addFuelAdminStockOut(fuelAdmin): Observable<any> {
    console.log(fuelAdmin);
    return this.http.post<any>(environment.backend_url + "/fuel_admin/outFuelStock", fuelAdmin);
  }
  getFuelAdminStockIn(): Observable<any> {
    return this.http.get<any>(environment.backend_url + "/fuel_admin/getFuelStockIn");
  }

  newFuelAdmin() {
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
  newAddFuelStock() {
    return {
      id:'',
      fuelType:"",
      stockFrom:"",
      amount:"",
      time:"",
      date:""
    }
  }
  newOutFuelStock() {
    return {
      id: "",
      fuelType: "",
      date: "",
      time: "",
      amount: "",
      vehicleNumber: "",
      driverName: "",
      number: "",
      fuelStation: {
        id:""
      }
    }
  }

}
