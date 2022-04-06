import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import static javax.sound.sampled.AudioSystem.*;

public class Music{
    private long backgroudposition;
    private Clip clip;

    public Music(String musictype) {
        //初始化加载音乐，但是还没有开始播放
        File fileSource = null;
        try {
            switch(musictype){
                case "backgroud":
                    fileSource = new File("music/backgroud.wav");
                    break;
                case "gameover":
                    fileSource = new File("music/gameover.wav");
                    break;
                case "eat":
                    fileSource = new File("music/eat.wav");
                    break;
            }
            AudioInputStream audioInputStream = getAudioInputStream(fileSource);
            this.clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            this.backgroudposition = clip.getMicrosecondPosition();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    //控制音乐的播放
    public void playMusic(){
        clip.setMicrosecondPosition(backgroudposition);
        clip.start();
    }

    //控制音乐的停止
    public void pauseMusic(){
        backgroudposition = clip.getMicrosecondPosition();
        clip.stop();
    }
}
