package natureGame.framework.audio;

public interface Music {

    public void play();

    public void dispose();

    public void stop();

    public void pause();

    public boolean isPlaying();

    public boolean isStoped();

    public boolean isLooping();

    public void setVollume();

    public void setLooping();
}
