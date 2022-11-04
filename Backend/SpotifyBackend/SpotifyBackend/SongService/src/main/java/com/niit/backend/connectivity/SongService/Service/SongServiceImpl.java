package com.niit.backend.connectivity.SongService.Service;

import com.niit.backend.connectivity.SongService.Domain.PlayList;
import com.niit.backend.connectivity.SongService.Domain.Song;
import com.niit.backend.connectivity.SongService.Domain.User;
import com.niit.backend.connectivity.SongService.Exceptions.UserAlreadyExistException;
import com.niit.backend.connectivity.SongService.Exceptions.UserNotFoundException;
import com.niit.backend.connectivity.SongService.Producer.Producer;
import com.niit.backend.connectivity.SongService.Proxy.UserProxy;
import com.niit.backend.connectivity.SongService.Repository.MyPlayListRepository;
import com.niit.backend.connectivity.SongService.Repository.PlaylistRepository;
import com.niit.backend.connectivity.SongService.Repository.SongsRepository;
import com.niit.backend.connectivity.SongService.rabbitmq.Domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class SongServiceImpl implements SongService{
    Clip clip;
    AudioInputStream audioInputStream;
    String file_P;
    long currentFrame;
    String status;
    static int count1=0;
    @Autowired
    private UserProxy userProxy;
    @Autowired
    private SongsRepository songsRepository;
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private MyPlayListRepository myPlayListRepository;
    Play playAudio=new Play();

    @Autowired
    private Producer producer;
    public SongServiceImpl(SongsRepository songsRepository,UserProxy userProxy,PlaylistRepository playlistRepository,MyPlayListRepository myPlayListRepository){
        this.songsRepository = songsRepository;
        this.userProxy = userProxy;
        this.playlistRepository = playlistRepository;
        this.myPlayListRepository = myPlayListRepository;
    }

    @Override
    public User userRegister(User user) throws UserAlreadyExistException {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        if (songsRepository.findById(user.getEmail()).isPresent()){
            throw new UserAlreadyExistException();
        }else {
            songsRepository.save(user);
            System.out.println("Data Going to Save to RabbitMQ Server...!!!");
            producer.sendMessageToRabbitMq(userDTO);
        }
        return user;
    }

    @Override
    public PlayList savePlayList(Song list,int playListId,String email) {
      PlayList playList = myPlayListRepository.findByEmail(email);
//      if (playList.getSongList()==null){
//          playList.setSongList(Arrays.asList(list));
//      }else {
//          List<Song> lists = playList.getSongList();
//          lists.add(list);
//          playList.setSongList(lists);
//      }

        return myPlayListRepository.save(playList);
    }

    @Override
    public User saveUserPlayListToList(PlayList list, String email) throws UserNotFoundException {
        if (songsRepository.findById(email).isEmpty()){
           throw new UserNotFoundException();
        }else {
            User user = songsRepository.findByEmail(email);
            if (user.getPlayLists() == null) {
                user.setPlayLists(Arrays.asList(list));
            } else {
                List<PlayList> lists = user.getPlayLists();
                lists.add(list);
                user.setPlayLists(lists);
            }

            return songsRepository.save(user);
        }
    }

    @Override
    public User getUser(String email) throws UserNotFoundException {
        if (songsRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }else {
            User user = songsRepository.findByEmail(email);
            System.out.println("inside service");
            return user;
        }
    }

    @Override
    public Song saveSong(Song song,byte[] bytes) {
        song.setUrl(bytes);
        playlistRepository.save(song);
        return song;
    }

    @Override
    public Song playSong(String songName) {
      Song song1= playlistRepository.findBySongName(songName);
        if (song1.getSongName().equals(songName)) {
            System.out.println("Here******************************");
            String f=song1.getFile_p();
            playAudio.readAudio(f,1);
        }
        return song1;
    }

    @Override
    public void stopSong(String songName) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Song song1= playlistRepository.findBySongName(songName);
    playAudio.playAudio1( song1.getFile_p(),1);
        playAudio.stop();
    }

    @Override
    public void pauseSong() {
        playAudio.pause();
    }

    @Override
    public void restartSong() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playAudio.restart();
    }

    @Override
    public void nextSong() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playAudio.next();
    }

//    public void readAudio(String audioSrcFile, int count) {
//
//        System.out.println(audioSrcFile);
//        System.out.println(count);
//        int c = 0;
//        count = count - 1;
//        try {
//
//            playAudio.playAudio1(audioSrcFile, count);
//
//            playAudio.play();
//            Scanner scanner = new Scanner(System.in);
//            while (true) {
//                System.out.println("1. pause");
//                System.out.println("2. resume");
//                System.out.println("3. restart");
//                System.out.println("4. Next");
//                // System.out.println("5. Exit to play");
//
//                if (c == 4) {
//                    break;
//                }
//
//                if (scanner.hasNextInt()) {
//                    c = scanner.nextInt();
//                } else {
//                    break;
//                }
//                switch (c) {
//                    case 1:
//                        playAudio.pause();
//                        break;
//                    case 2:
//                        playAudio.resumeAudio();
//                        break;
//                    case 3:
//                        playAudio.restart();
//                        break;
//                    case 4:
//                        playAudio.stop();
//                        break;
//
//
//                }
//            }
//        } catch (Exception ex) {
//            System.out.println("Error with playing sound." + ex);
//        }
//    }
//    public boolean playAudio(String songId) {
//        System.out.println("called");
//        Optional<Song> song=playlistRepository.findById(songId);
//
//        readAudio(song.get().getFile_p(),1);
//        return true;
//    }
//
//    public boolean stopAudio(){
//
//        playAudio.stop();
//        return true;
//    }


    @Override
    public List<Song> getAllSongs() {
        return playlistRepository.findAll();
    }
}
