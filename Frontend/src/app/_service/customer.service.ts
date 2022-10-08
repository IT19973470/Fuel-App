import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  customer

  constructor(private http: HttpClient) {
  }

  addCustomer(customer): Observable<any> {
    return this.http.post<any>(environment.backend_url + "/customer/addCustomer", customer);
  }

  updateCustomer(customer): Observable<any> {
    return this.http.put<any>(environment.backend_url + "/customer/updateCustomer/" + customer.nic, customer);
  }

  getCustomer(email, contactNumber): Observable<any> {
    return this.http.get<any>(environment.backend_url + "/customer/getCustomer/" + email + "/" + contactNumber);
  }

  getCustomerByVehicle(vehicle): Observable<any> {
    return this.http.get<any>(environment.backend_url + "/customer/getCustomerByVehicle/" + vehicle);
  }

  getPumpedAmounts(id): Observable<any> {
    return this.http.get<any>(environment.backend_url + "/customer/getPumpedAmounts/" + id);
  }

  sendOTP(email, contactNumber): Observable<any> {
    return this.http.get<any>(environment.backend_url + "/customer/sendOTP/" + email + "/" + contactNumber);
  }

  getFuelAvailability(place, orderBy): Observable<any> {
    return this.http.get<any>(environment.backend_url + "/customer/fuelAvailability/" + place + "/" + orderBy);
  }

  newCustomer() {
    return {
      nic: '',
      name: '',
      address: '',
      district: '',
      fuelStationPlace: {
        id: ''
      },
      chassisNumber: '',
      vehicleNumber: '',
      vehicleType: '',
      fuelType: '',
      quota: 0,
      appUser: {
        email: '',
        contactNumber: '',
        password: ''
      }
    }
  }
}
