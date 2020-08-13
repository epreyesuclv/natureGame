package natureGame.framework.fileIO;


import natureGame.MyGame;

import java.io.*;

public class MyFileIO implements FileIO {
    MyGame game;

    public MyFileIO(MyGame game) {
        this.game = game;
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return null;
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(fileName);
    }

    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(fileName);
    }

}
