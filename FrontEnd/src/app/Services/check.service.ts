import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CheckService {

  constructor() { }
  ischecked:boolean=false;
  redirectURL:string=""

  checkPass(pass: boolean)
  {
  alert(1)
       if(pass=true)
       {
        alert("Hello")
        this.ischecked=true;
       }else{
        alert("wrong")
       }
  }
}
