import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FuelAdminService {

  fuelIn;
  fuelOut;

  constructor(private http: HttpClient) {
  }

  addFuelAdmin(fuelAdmin): Observable<any> {
    return this.http.post<any>(environment.backend_url + '/fuel_admin/addFuelAdmin', fuelAdmin);
  }
  addFuelAdminStockIn(fuelAdmin): Observable<any> {
    console.log(fuelAdmin);
    return this.http.post<any>(environment.backend_url + '/fuel_admin/addFuelStock', fuelAdmin);
  }
  addFuelAdminStockOut(fuelAdmin): Observable<any> {
    console.log(fuelAdmin);
    return this.http.post<any>(environment.backend_url + '/fuel_admin/outFuelStock', fuelAdmin);
  }
  getFuelAdminStockIn(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/fuel_admin/getFuelStockIn');
  }
  getFuelAdminStockOut(): Observable<any> {
    return this.http.get<any>(environment.backend_url + "/fuel_admin/getFuelStockOut");
  }

  getFuelStations(): Observable<any> {
    return this.http.get<any>(environment.backend_url + "/fuel_admin/getFuelStation");
  }

  updateStockIn(fuelIn): Observable<any> {
    return this.http.put<any>(environment.backend_url + "/fuel_admin/updatefuelStockIn/" + fuelIn.id, fuelIn);
  }

  updateStockOut(fuelOut): Observable<any> {
    return this.http.put<any>(environment.backend_url + "/fuel_admin/updatefuelStockOut/" + fuelOut.id, fuelOut);
  }

  deleteStockIn(fuelIn): Observable<any> {
    return this.http.delete<any>(environment.backend_url + '/fuelPumper/deleteStockIn/' + fuelIn.id);
  }



  newFuelAdmin() {
    return {
      nic: '',
      name: '',
      address: '',
      appUser: {
        email: '',
        password: '',
        contactNumber: '',
      }
    };
  }
  newAddFuelStock() {
    return {
      id: '',
      fuelType: '',
      stockFrom: '',
      amount: '',
      time: '',
      date: ''
    };
  }
  newOutFuelStock() {
    return {
      id: '',
      fuelType: '',
      date: '',
      time: '',
      amount: '',
      vehicleNumber: '',
      driverName: '',
      number: '',
      fuelStation: {
        id: ''
      }
    };
  }

}
