export class Songs{
songName:string;
artist:string;
genre:string;
file_p:any;
url:any;
checked?:boolean;
    constructor(){
        this.songName='';
        this.artist='';
        this.genre='';
        this.file_p='';
        this.url='';
    }
}