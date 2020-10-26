package natureGame.framework.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MyMusic implements Music {
    MediaPlayer mediaPlayer;
    volatile boolean isPlaying = false;

    public MyMusic(String filename) {
        mediaPlayer = new MediaPlayer(new Media(new File("src/natureGame/Assets/" + filename).toURI().toString()));
        mediaPlayer.setOnPlaying(new Runnable() {
            @Override
            public void run() {
                isPlaying = true;
            }
        });
        mediaPlayer.setOnStopped(() -> isPlaying = false);
        mediaPlayer.setOnEndOfMedia(() -> isPlaying = false);
    }

    @Override
    public void play() {
        mediaPlayer.play();
    }

    @Override
    public void dispose() {
        mediaPlayer.dispose();
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.setStartTime(Duration.ZERO);
    }

    @Override
    public void pause() {
        mediaPlayer.stop();
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public boolean isStoped() {
        return !isPlaying;
    }

    @Override
    public boolean isLooping() {
        return mediaPlayer.isAutoPlay();
    }

    @Override
    public void setLooping(boolean value) {
        mediaPlayer.setAutoPlay(value);
    }

    @Override
    public void setVollume(double value) {
        mediaPlayer.setVolume(value);
    }
}
