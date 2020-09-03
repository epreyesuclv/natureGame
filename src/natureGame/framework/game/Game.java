package natureGame.framework.game;


import natureGame.framework.audio.Audio;
import natureGame.framework.fileIO.FileIO;
import natureGame.framework.graphics.Graphics;
import natureGame.framework.screen.Screen;

/**
 * interface principal del framework
 **/
public interface Game {
    Graphics getGraphics();

    Audio getAudio();

    FileIO getFileIO();

    void setScreen(Screen screen);

    Screen getCurrentScreen();

    //cambia el tamanho
    void changeSize(int x, int y);
}
