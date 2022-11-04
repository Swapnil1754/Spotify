import { Component, OnInit } from '@angular/core';
import { LoginService } from '../Services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  public loggedIn=false;

  constructor(private login:LoginService) { }

  ngOnInit(): void {
    this.login.isLoggedIn();
    this.loggedIn=this.login.isLoggedIn()
  }
  // getUser(){
  //   this.login.
  // }
  logOut(){
    alert("Logged Out Successfully...!!!")
    this.login.toLoggedOut()
    location.reload();
  }

}
