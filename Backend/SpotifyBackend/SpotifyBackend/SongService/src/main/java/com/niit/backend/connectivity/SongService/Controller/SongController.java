package com.niit.backend.connectivity.SongService.Controller;

import com.google.gson.Gson;
import com.niit.backend.connectivity.SongService.Domain.PlayList;
import com.niit.backend.connectivity.SongService.Domain.Song;
import com.niit.backend.connectivity.SongService.Domain.User;
import com.niit.backend.connectivity.SongService.Exceptions.UserAlreadyExistException;
import com.niit.backend.connectivity.SongService.Exceptions.UserNotFoundException;
import com.niit.backend.connectivity.SongService.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:63531")
@RestController
@RequestMapping("/api/v2")
public class SongController {
    private SongService songService;
    private ResponseEntity responseEntity;
    Gson gson=new Gson();
    @Autowired
    public SongController(SongService songService){
        this.songService = songService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistException {
        try {
            responseEntity = new ResponseEntity(songService.userRegister(user), HttpStatus.CREATED);
        }catch (UserAlreadyExistException e){
            throw new UserAlreadyExistException();
        }
        return responseEntity;
    }
    @PutMapping(path = "/{email}")
    public ResponseEntity<?> saveUserPlayListToList(@RequestBody PlayList list, @PathVariable String email) throws UserNotFoundException{
        System.out.println("Inside PutMapping...*******************************");
        responseEntity = new ResponseEntity(songService.saveUserPlayListToList(list,email),HttpStatus.CREATED);
        return responseEntity;
    }
    @GetMapping(path = "/{email}")
    public ResponseEntity<?> getUser(@PathVariable String email) throws UserNotFoundException{
        System.out.println("hi");
        try {
            System.out.println("M here");
            responseEntity = new ResponseEntity<>(songService.getUser(email),HttpStatus.OK);
        }catch (UserNotFoundException e){
            System.out.println("hello");
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
    @PostMapping("/posting")
    public ResponseEntity<?> addSong(@RequestParam("file")MultipartFile file,@RequestParam("details")String details) throws IOException {
        Song song = gson.fromJson(details,Song.class);
        responseEntity = new ResponseEntity(songService.saveSong(song,file.getBytes()),HttpStatus.CREATED);
        return responseEntity;
    }
    @GetMapping(path = "/songs")
   public ResponseEntity<?> getSongs(){
        responseEntity = new ResponseEntity(songService.getAllSongs(),HttpStatus.OK);
       System.out.println("***********************"+responseEntity);
       return responseEntity;
   }
   @GetMapping("/ok/{songName}")
   public ResponseEntity<?> playSong(@PathVariable String songName){
       System.out.println("Got it*********************************");
        responseEntity=new ResponseEntity(songService.playSong(songName),HttpStatus.OK);
       return responseEntity;
   }
    @GetMapping("/stop/{songName}")
    public ResponseEntity<?> stopSong(@PathVariable String songName) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int choice=4;
        System.out.println(4);

        songService.stopSong(songName);
        System.out.println("888888888888888888888888888888888888888888"+responseEntity);
        return responseEntity;
    }
    @GetMapping("/ok/pause")
    public ResponseEntity<?> pauseSong(){
        songService.pauseSong();
        return responseEntity;
    }
    @GetMapping("/ok/restart")
    public ResponseEntity<?> restartSong() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        songService.restartSong();
        return responseEntity;
    }
    @GetMapping("/ok/next")
    public ResponseEntity<?> nextSong() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        songService.nextSong();
        return responseEntity;
    }
}
