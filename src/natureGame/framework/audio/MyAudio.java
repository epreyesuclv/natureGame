package natureGame.framework.audio;

public class MyAudio implements Audio {

    @Override
    public Music newMusic(String filename) {
        return new MyMusic(filename);
    }

    @Override
    public Sound newSound(String filename) {
        return null;
    }
}
