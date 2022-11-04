import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../Services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  regForm:FormGroup;
  Log:FormGroup;
  constructor(private login:LoginService,private router:Router) {
    this.regForm=new FormGroup({
      email: new FormControl('', [Validators.required,Validators.minLength(10),Validators.maxLength(50),custom5]),
     password: new FormControl('', [Validators.required,Validators.minLength(5),Validators.maxLength(20)])
    });
    this.Log=new FormGroup({email:new FormControl('',[Validators.required]),
    password:new FormControl('',)
  })
   }
   data:any;
   check1()
 {
  const regData = this.regForm.value;
  if(regData.email!='' && regData.password!=''){
   this.login.check(regData).subscribe((x:any)=>{
     this.data=x;
     console.log(this.data);
     this.login.loginUser(x.Token)
     this.login.isLoggedIn();
        alert("Login Success..!!!");
        window.location.href="/dashboard";
        this.login.setEmail(regData.email);
        console.log(regData.email)
        return this.login.isLogin=true;
   },error=>{
     alert("Incorrect Email Or Password...!!! Try Again...!!!")
   })
  }
 }
 ngOnInit(){
  this.login.check1().subscribe(x=>console.log(x));
  this.login.isLoggedIn();
 }

}
export function custom5(control:AbstractControl){
  let testemail="^[A-Za-z0-9+_.-]+@(.+)$";
  if(control.value&&control.value.match(testemail)){
    console.log(1)
    return null
  }else{
    console.log(2)
    return {myError5:false}
  }
}
