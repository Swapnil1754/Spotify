import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { PlayList } from '../playList';
import { CheckService } from '../Services/check.service';
import { LoginService } from '../Services/login.service';
import { Songs } from '../songs';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit {
  playForm:FormGroup;
  checked: boolean=false;
  tempArray: Songs | undefined;

  constructor(private formbuilder:FormBuilder,private login:LoginService,private checked1:CheckService) { 
  
    this.playForm = this.formbuilder.group({
      playListId: ['',Validators.required],
      playListName: ['',Validators.required],
      songList: this.formbuilder.array([
        // this.formbuilder.group({
        //   songName: ['',Validators.required],
        //   artist: ['',Validators.required],
        //   genre: ['',Validators.required]
        // })
      ])
    });
    // this.playForm=formbuilder.group({
    //   songList: new FormArray([])
    // });
  
  }
  ngOnInit(): void {
   this.getAllSongs();
  }
  getControls() {
    return (this.playForm.get('songList') as FormArray).controls;
  }
  data1:any
  addData(){
    const data=this.playForm.value;
    this.login.addList(data).subscribe((x)=>{
      this.data1=x;
      alert("Your Playlist Added Successfully...!!!")
      console.log(this.data1);
      location.reload();
      
    })
  }
  getPlaylistId(){

  }
  song:Songs[]=[]
  getAllSongs(){
    this.login.getAllSong().subscribe((x)=>{
      console.log("My songs", x);
      this.song=x;
    })
  }
  isChecked:boolean=false;
  
  toList(pass:any,event:MatCheckboxChange){
    console.log("Song data",pass);
    const songList = this.playForm.controls['songList'] as FormArray;
    console.log("Checked value",event.checked);
    if(event.checked && pass.songName!==''){
      this.tempArray=pass;
      //  this.tempArray.push(pass)
      songList.push(new FormControl(this.tempArray))
      
      console.log("Pass",pass.songName);
      
    }else{
      // const index = songList.controls.findIndex(x=>x.value===pass.value);
      // songList.removeAt(index);
      location.reload();
    }
    // songList.push(new FormControl(this.tempArray))
    console.log("temp1",this.tempArray);
    // this.data1=this.playForm.value;
    console.log("Final",this.data1)
    
  }

  getData(){
    console.log("getData",this.tempArray);
    // const songList = this.playForm.controls['songList'] as FormArray;
    // songList.push(new FormControl(this.tempArray))
    this.data1=this.playForm.value;
    console.log("Final1",this.data1)
    this.login.addList(this.data1).subscribe((x)=>{
      
    })
    alert("Your PlayList Updated Successfully...!!!!")
    window.location.href="/dashboard";
  }

  // get songList(){
  //   return this.playForm.controls["songList"] as FormArray;
  // }
  // addsongList(){
  //   const songForm = this.formbuilder.group({
  //     songName: [''],
  //         artist: [''],
  //         genre: ['']
  //   });
  //   this.songList.push(songForm);
  // }
  getback(){
    window.location.href="/dashboard";
  }
}
