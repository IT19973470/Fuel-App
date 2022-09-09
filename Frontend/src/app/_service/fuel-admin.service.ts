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
    }
  }
}
