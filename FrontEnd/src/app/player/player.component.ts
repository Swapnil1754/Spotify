import { Component, OnInit } from '@angular/core';
import {ThemePalette} from '@angular/material/core';
import { ProgressSpinnerMode } from '@angular/material/progress-spinner';
import { LoginService } from '../Services/login.service';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {
  public loggedIn=false;
  songName:any;
  color: ThemePalette = 'primary';
  mode: ProgressSpinnerMode = 'determinate';
  value = 0;
  constructor(private login:LoginService) { }

  ngOnInit(): void {
    
  }
  mySong:any;
  play(){
    console.log("Play");
    this.mySong=localStorage.getItem("songName");
    console.log("SongName",this.mySong);
    
    alert(this.songName)
    this.login.play(this.mySong).subscribe((x)=>{

    })
  }
  pause(){
    this.login.pause().subscribe((x)=>{
      console.log("Paused");
      
    })
  }
  stop(){
    this.login.stop().subscribe((x)=>{
      console.log("Stopped");
      
    })
  }
  restart(){
    this.login.restart().subscribe((x)=>{
      console.log("Restarting");
      
    })
  }
  next(){
    this.login.next().subscribe((x)=>{
      console.log("Next Song");
      
    })
  }
  refresh(){
    location.reload();
  }
}
