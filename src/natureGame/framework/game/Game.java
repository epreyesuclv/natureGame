package natureGame.framework.game;


import natureGame.framework.graphics.Graphics;
import natureGame.framework.audio.Audio;
import natureGame.framework.fileIO.FileIO;
import natureGame.framework.input.Input;
import natureGame.framework.screen.Screen;

public interface Game {
    Graphics getGraphics();

    Audio getAudio();

    FileIO getFileIO();

    Input getInput();

    void setScreen(Screen screen);

    Screen getCurrentScreen();

    void changeSize(int x, int y);
}
