import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../Services/login.service';

@Component({
  selector: 'app-spotyfy-register',
  templateUrl: './spotyfy-register.component.html',
  styleUrls: ['./spotyfy-register.component.css']
})
export class SpotyfyRegisterComponent implements OnInit {
regForm:FormGroup;
public loggedIn=false;
  constructor(private login:LoginService,private router:Router,private breakpointObserver:BreakpointObserver) {
    this.regForm=new FormGroup({
      email: new FormControl('', [Validators.required]),
     phoneNo: new FormControl('', [Validators.required]),
     userName: new FormControl('', [Validators.required])
    });
   }

  ngOnInit(): void {
    this.login.isLoggedIn();
    this.loggedIn=this.login.isLoggedIn()
  }
  data1:any;
  registerFun(){
     this.data1=this.regForm.value;
    this.login.spotyfyregister(this.data1).subscribe((x)=>{
      
      console.log(x);
      alert("Signed Up Successfully...!!!")
      window.location.href="/dashboard";
    })
  }
  logOut(){
    alert("Logged Out Successfully...!!!")
    this.login.toLoggedOut()
    location.reload();
    
  }
  getback(){
    window.location.href="/dashboard";
  }
}
