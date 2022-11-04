import { Component, OnInit } from '@angular/core';
import { LoginService } from '../Services/login.service';

@Component({
  selector: 'app-user-data',
  templateUrl: './user-data.component.html',
  styleUrls: ['./user-data.component.css']
})
export class UserDataComponent implements OnInit {
  public loggedIn=false;
  constructor(private login:LoginService) { }

  ngOnInit(): void {
    this.getAllUsers()
  }
  data:any
  getAllUsers(){
    this.login.check1().subscribe((x)=>{
      this.data=x;
      console.log(x);
      
    })

}
logOut(){
  alert("Logged Out Successfully...!!!")
  this.login.toLoggedOut()
  location.reload();
}
}
