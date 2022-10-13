import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  customer;

  constructor(private http: HttpClient) {
  }

  addCustomer(customer): Observable<any> {
    return this.http.post<any>(environment.backend_url + '/customer/addCustomer', customer);
  }

  updateCustomer(customer): Observable<any> {
    return this.http.put<any>(environment.backend_url + '/customer/updateCustomer/' + customer.nic, customer);
  }

  deleteCustomer(id): Observable<any> {
    return this.http.delete<any>(environment.backend_url + '/customer/deleteCustomer/' + id);
  }

  getCustomer(email, contactNumber): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/customer/getCustomer/' + email + '/' + contactNumber);
  }

  getCustomerByVehicle(vehicle): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/customer/getCustomerByVehicle/' + vehicle);
  }

  getPumpedAmounts(id): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/customer/getPumpedAmounts/' + id);
  }

  sendOTP(email, contactNumber): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/customer/sendOTP/' + email + '/' + contactNumber);
  }

  getFuelAvailability(place, orderBy): Observable<any> {
    return this.http.get<any>(environment.backend_url + "/customer/fuelAvailability/" + place + "/" + orderBy);
  }

  addFuelConsumption(fuelConsumption): Observable<any> {
    return this.http.post<any>(environment.backend_url + '/customer/addFuelConsumption', fuelConsumption);
  }

  deleteFuelConsumption(id): Observable<any> {
    return this.http.delete<any>(environment.backend_url + '/customer/deleteFuelConsumption/' + id);
  }

  getFuelConsumptions(id): Observable<any> {
    return this.http.get<any>(environment.backend_url + "/customer/getFuelConsumptions/" + id);
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
      vehicle: {
        vehicleType: {
          id: ''
        },
        chassisNumber: '',
        vehicleNumber: '',
        fuelType: '',
      },
      quota: 0,
      appUser: {
        email: '',
        contactNumber: '',
        password: ''
      }
    };
  }
}
