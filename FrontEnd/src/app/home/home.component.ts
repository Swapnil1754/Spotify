import { Component, OnInit } from '@angular/core';
import { LoginService } from '../Services/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public loggedIn=false;
  constructor(private login:LoginService) { }

  ngOnInit(): void {
    this.login.isLoggedIn();
    this.loggedIn=this.login.isLoggedIn()
  }
  logOut(){
    alert("Logged Out Successfully...!!!")
    this.login.toLoggedOut()
    location.reload();
  }
  data:any;
  getAllUsers(){
    this.login.check1().subscribe((x)=>{
      this.data=x;
      console.log(x);
      
    })
  }
}
