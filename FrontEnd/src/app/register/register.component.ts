import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Router } from '@angular/router';
import { map, Observable } from 'rxjs';
import { LoginService } from '../Services/login.service';
import { reg } from '../reg';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  email:any;password:any;
  regForm:FormGroup;Log:FormGroup;
  reg1: reg=new reg("","","")

  constructor(private login:LoginService,private router:Router,private breakpointObserver:BreakpointObserver) { 
    this.regForm=new FormGroup({
      email: new FormControl('', [Validators.required,Validators.minLength(10),Validators.maxLength(50),custom5]),
     password: new FormControl('', [Validators.required,Validators.minLength(8),Validators.maxLength(20),custom]),
     userName: new FormControl('', [Validators.required,Validators.minLength(8),Validators.maxLength(20)])
    });
    this.Log=new FormGroup({email:new FormControl('',[]),
    password:new FormControl('',),
  })
  }
  registerfun()
  {
    alert("Register Successful");
    const regData = this.regForm.value;
    console.log(regData)
  this.login.store(regData).subscribe(() => {
    location.reload();
    window.location.href="/login";
  });
  }

  ngOnInit(): void {
  }
  
  checkBeforeExit():any
{
  if(this.email!=null || this.password!=null)
  {
  return confirm("DO you want to exit")
  }
  else
  {
    return true
  }
}
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      
    );
    
    submitted=false;
  fun1()
  {
    this.submitted=true;
  }

}
export function custom5(control:AbstractControl){
  let testemail="^[A-Za-z0-9+_.-]+@(.+)$";
  if(control.value&&control.value.match(testemail)){
    return null
  }else{
    return {myError5:false}
  }
}
export function custom(control:AbstractControl){
let test="^[A-Za-z0-9+_.-]+@$";
if(control.value&&control.value.match(test)){
  return null
}else{
  
  return {myError6:false}
}
}

export function custom2(this: any, control:AbstractControl){
  let x=control.get('password')?.value;
 //let x=this.Log.get('password')?.value;
  if(control.value===x){
    return null
  }else{
    
    return{myError7:false}
  }
}
