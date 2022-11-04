import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PlayList } from '../playList';
import { reg } from '../reg';
import { Songs } from '../songs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  isLogin=false;
  data2:any;
  url="http://localhost:9000/api/v1/login";
  url1="http://localhost:9000/api/v2";
  url2="http://localhost:9000/api/v2/ok";
  url3="http://localhost:9000/api/v2/stop";
  constructor(private httpclient:HttpClient) { }
  
  store(regData:reg): Observable<reg>{
    return this.httpclient.post<reg>('http://localhost:9000/api/v1/register', regData,{responseType:'text' as 'json'});
  }
  loginUser(Token: string){
    console.log("Yes");
    
    localStorage.setItem("Token",Token)
    return true;
  }
  //to check user log in
  isLoggedIn(){
    let token = localStorage.getItem("Token")
    if(token===undefined || token==='' || token==null){
      console.log("Logged Out");
      
      return false;
    }else{
      console.log("Logged In");
      
      return true;
    }
  }
  //to logout user
  toLoggedOut(){
    localStorage.removeItem("Token")
    return true;
  }
  //to get token
  getToken(){
    return localStorage.getItem("Token");
  }
  check(regData:reg):Observable<reg> {
    console.log(regData)
    return this.httpclient.post<reg>(`${this.url}`,regData);
  }
  check1():Observable<reg> {
    return this.httpclient.get<reg>('http://localhost:9000/api/v1/users');
  }
  setEmail(email:string){
     localStorage.setItem("Email",email)
     alert(email)
     return true;
  }
  getEmail(){
    return localStorage.getItem("Email")
  }
  setplaylistid(playListId:any){
    localStorage.setItem("playListId",playListId)
    console.log("playListId",playListId);
    return true;
  }
  getplayListId(){
    return localStorage.getItem("playListId")
  }
  getList():Observable<any>{
     this.data2 = this.getEmail();
    return this.httpclient.get<any>(`${this.url1}/${this.data2}`)
  }
  addList(data:reg):Observable<reg>{
    this.data2=this.getEmail();
    return this.httpclient.put<reg>(`${this.url1}/${this.data2}`,data)
  }
  spotyfyregister(data:reg):Observable<reg>{
    this.data2=this.getEmail();
    return this.httpclient.post<reg>(`${this.url1}/register`,data,{responseType:'text' as 'json'})
  }
  getAllSong(){
    return this.httpclient.get<Songs[]>(`${this.url1}/songs`)
  }
  play(song:any):Observable<any>{
    let mySong=localStorage.getItem("songName")

    return this.httpclient.get<any>(`${this.url2}/${mySong}`)
  }
  stop():Observable<any>{
    let mySong=localStorage.getItem("songName")
    return this.httpclient.get<any>(`${this.url3}/${mySong}`)
  }
  pause():Observable<any>{
    return this.httpclient.get<any>(`${this.url2}/pause`)
  }
  restart():Observable<any>{
    return this.httpclient.get<any>(`{this.url2}/restart`)
  }
  next():Observable<any>{
    return this.httpclient.get<any>(`${this.url2}/next`)
  }
}
