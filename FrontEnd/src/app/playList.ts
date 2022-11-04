import { Songs } from "./songs";

export class PlayList{
    playListId: number;
        playListName:string;
        songList:Songs[];
    constructor(){
        this.playListId=0;
        this.playListName='';
        this.songList=[];
    }
}