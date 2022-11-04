package com.niit.backend.connectivity.SongService.Service;

import com.niit.backend.connectivity.SongService.Domain.PlayList;
import com.niit.backend.connectivity.SongService.Domain.Song;
import com.niit.backend.connectivity.SongService.Domain.User;
import com.niit.backend.connectivity.SongService.Exceptions.UserAlreadyExistException;
import com.niit.backend.connectivity.SongService.Exceptions.UserNotFoundException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

public interface SongService {
    public User userRegister(User user) throws UserAlreadyExistException;
    public PlayList savePlayList(Song list,int playListId,String email);
    public User saveUserPlayListToList(PlayList list, String email) throws UserNotFoundException;
    public User getUser(String email) throws UserNotFoundException;

    public Song saveSong(Song song,byte[] bytes);

    public Song playSong(String songName);
    public void stopSong(String songName) throws UnsupportedAudioFileException, LineUnavailableException, IOException;
    public void pauseSong();
    public void restartSong() throws UnsupportedAudioFileException, LineUnavailableException, IOException;
    public void nextSong() throws UnsupportedAudioFileException, LineUnavailableException, IOException;
    public List<Song> getAllSongs();
}
