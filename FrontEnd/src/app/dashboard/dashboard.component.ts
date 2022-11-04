import { HttpParams } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatCard } from '@angular/material/card';
import { MatDialog } from '@angular/material/dialog';
import { PlayerComponent } from '../player/player.component';
import { LoginService } from '../Services/login.service';
import { Songs } from '../songs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
songName:string='';
  public loggedIn=false;
  // songForm:FormGroup
  constructor(private login:LoginService,public dialog:MatDialog) {
    // this.songForm = new FormGroup({
    //   songName: new FormControl('',[]),
    //   artist: new FormControl('',[]),
    //   genre: new FormControl('',[])
    // })
   }
  ngOnInit(): void {
    this.login.isLoggedIn();
    this.loggedIn=this.login.isLoggedIn()
    this.getEmail()
    this.getAllSongs();
  }
  logOut(){
    alert("Logged Out Successfully...!!!")
    this.login.toLoggedOut()
    location.reload();
  }
  data1:any;
  getEmail(){
     let e = this.login.getEmail();
     this.data1=e;
   console.log("Finally",this.data1);
  }
  data2:any;
  getAllUsers(){
    console.log("Finally1",this.data1);
    this.login.getList().subscribe((x:any)=>{
      this.data2=x;
      console.log("ok",this.data2);
    })
  }
 
  data0:any;
  song:Songs[]=[];
  getAllSongs(){
    this.login.getAllSong().subscribe((x)=>{
      console.log("My songs", x);
      this.song=x;
      console.log("play Song",this.song);
      this.song.filter((y:{songName:any | null | undefined})=>{
        this.data0=y.songName;
      })
      
    })
  }
  mysong:any;
  openDialog(songName:any) {
    console.log(songName);
    localStorage.setItem("songName",songName)
    
    const dialogRef = this.dialog.open(PlayerComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

}
