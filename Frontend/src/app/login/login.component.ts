import {Component, OnInit} from '@angular/core';
import {LoginService} from './login.service';
import {Router} from '@angular/router';
import {NavbarService} from '../navbar/navbar.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user;

  constructor(private loginS: LoginService, private router: Router, private navBarS: NavbarService) {
    this.user = this.newUser();
  }

  ngOnInit(): void {
  }

  login() {
    this.loginS.login(this.user).subscribe(user => {
      if (user !== null) {
        localStorage.setItem('user', JSON.stringify(user));
        if (user.userType === 'customer') {
          this.router.navigate(['/my_profile']);
        } else if (user.userType === 'fuelStation') {
          this.router.navigate(['/register_pumper']);
        } else if (user.userType === 'fuelPumper') {
          this.router.navigate(['/update_quota']);
        } else if (user.userType === 'fuelAdmin') {
          this.router.navigate(['/fuel_availability_admin']);
        }
        this.navBarS.navBar.next();
      } else {

      }
    });
  }

  newUser() {
    return {
      email: '',
      password: ''
    };
  }
}
