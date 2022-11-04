package com.niit.backend.connectivity.SongService.Service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Play {
    Clip clip;
    AudioInputStream audioInputStream;
    String file_P;
    long currentFrame;
    String status;
    static int count1=0;
    public Play(){

    }
    public void playAudio1(String file_P,int count) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        count1=count;
        this.file_P=file_P;
        audioInputStream= AudioSystem.getAudioInputStream(new File(file_P).getAbsoluteFile());
        clip=AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(count1);
    }
    public void playFile(){
        clip.start();
        status="play";
    }
    public void pause(){
        if (status.equals("paused")) {
            System.out.println("audio is already paused");
            return;
        }
        currentFrame = clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }
    public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (status.equals("play")) {
            System.out.println("Audio is already being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        playFile();
    }
    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        playFile();
    }
    public void next() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        jump(10);
    }
    public void jump(long c) {
        if (c>0 && c<clip.getMicrosecondLength()){
            clip.stop();
            clip.close();
            currentFrame=c;
            clip.setMicrosecondPosition(c);
            this.playFile();
        }
    }
    public void stop() {
        currentFrame = 0L;
        clip.stop();
        clip.close();
        System.out.println("Inside Play Class");
    }
    public void exit(){
        String[] p={};
        currentFrame = 0L;
        clip.stop();
        clip.close();
        //main(p);
    }
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(file_P).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void readAudio(String audioSrcFile,int count) {
        Play playAudio = new Play();
        int choice = 0;
        count = count-1;
        try {

            playAudio.playAudio1(audioSrcFile,count);

            playAudio.playFile();
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. pause");
            System.out.println("2. resume");
            System.out.println("3. restart");
            System.out.println("4. stop");
            System.out.println("5. next");
            System.out.println("6. Exit");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
            switch (choice) {
                case 1:
                    playAudio.pause();
                    break;
                case 2:
                    playAudio.resumeAudio();
                    break;
                case 3:
                    playAudio.restart();
                    break;
                case 4:
                    playAudio.stop();
                    break;
                case 5:
                    playAudio.next();
                    break;
                case 6:
                    playAudio.exit();
                    break;
                default:
                    System.out.println("Invalid Input...!!!");
            }
        } catch (Exception ex) {
            System.out.println("Error with playing sound." + ex);
        }
    }

}
