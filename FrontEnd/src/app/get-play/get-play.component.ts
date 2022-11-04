import { Component, OnInit } from '@angular/core';
import { PlayList } from '../playList';
import { LoginService } from '../Services/login.service';
import { Songs } from '../songs';

@Component({
  selector: 'app-get-play',
  templateUrl: './get-play.component.html',
  styleUrls: ['./get-play.component.css']
})

export class GetPlayComponent implements OnInit {
  public loggedIn=false;
  constructor(private login:LoginService) { }
    playlist:PlayList[]=[];
    playListId:any;
    playListName:any;
    songList:Songs[]=[];
  ngOnInit(): void {
    this.getPlayList();
    this.login.isLoggedIn();
    this.loggedIn=this.login.isLoggedIn()
  }
  myData:any;
  myData1:any;
  getPlayList(){
    this.login.getList().subscribe((result: any)=>{
      this.myData=result.playLists;
      })
    }
    logOut(){
      alert("Logged Out Successfully...!!!")
      this.login.toLoggedOut()
      location.reload();
    }
}




















//   this.playlist=result;
    //   this.playlist.forEach(x=>{
    //     this.playListId=x.playListId;
    //     this.playListName=x.playListName;
    //     this.songList=x.songList;
    //   })
    //   console.log("success",result);
    //   this.myData=result;
    //   console.log("myData is",this.myData);
      
    // },
    // error=>{
    //   console.log('I am in Error');
    //   console.log(error.error.playLists);

    //   //this.playList=<PlayList>error.error.playLists;
