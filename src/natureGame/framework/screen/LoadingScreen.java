package natureGame.framework.screen;

import natureGame.MyGame;
import natureGame.framework.fileIO.Assets;
import natureGame.framework.game.Game;
import natureGame.framework.graphics.Graphics;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);

    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.atras = g.newPixmap("natureGame/Assets/Images/Otros/atras.png", 90, 90);
        Assets.X = g.newPixmap("natureGame/Assets/Images/Otros/x.png", 90, 90);
        Assets.gamepad = g.newPixmap("natureGame/Assets/Images/Otros/gamepad-console.jpg", 90, 90);
        Assets.configuration = g.newPixmap("natureGame/Assets/Images/Otros/configuration.png", 90, 90);
        Assets.terreno = g.newPixmap("natureGame/Assets/Images/Resources/Terreno/terreno.png", 0);

        Assets.piedra = g.newPixmap("natureGame/Assets/Images/Resources/Piedra/piedra1.png", 0);
        Assets.planta = g.newPixmap("natureGame/Assets/Images/Resources/Planta/Planta2.png", 0);
        Assets.huesos = g.newPixmap("natureGame/Assets/Images/Resources/Huesos/huesos.png", 0);

        Assets.buitre = g.newPixmap("natureGame/Assets/Images/Resources/Buitre/buitre1.png", 0);
        Assets.buitre2 = g.newPixmap("natureGame/Assets/Images/Resources/Buitre/buitre2.png", 0);
        Assets.buitre3 = g.newPixmap("natureGame/Assets/Images/Resources/Buitre/buitre3.png", 0);
        Assets.buitre4 = g.newPixmap("natureGame/Assets/Images/Resources/Buitre/buitre4.png", 0);
        Assets.buitre5 = g.newPixmap("natureGame/Assets/Images/Resources/Buitre/buitre5.png", 0);
        Assets.buitre6 = g.newPixmap("natureGame/Assets/Images/Resources/Buitre/buitre6.png", 0);

        Assets.conejo = g.newPixmap("natureGame/Assets/Images/Resources/Conejo/Conejo1.png", 0);
        Assets.conejo2 = g.newPixmap("natureGame/Assets/Images/Resources/Conejo/Conejo2.png", 0);
        Assets.conejo3 = g.newPixmap("natureGame/Assets/Images/Resources/Conejo/conejo8.png", 0);
        Assets.conejo4 = g.newPixmap("natureGame/Assets/Images/Resources/Conejo/conejov1.png", 0);
        Assets.conejo5 = g.newPixmap("natureGame/Assets/Images/Resources/Conejo/conejov2.png", 0);
        Assets.conejo6 = g.newPixmap("natureGame/Assets/Images/Resources/Conejo/conejov8.png", 0);

        Assets.serpiente = g.newPixmap("natureGame/Assets/Images/Resources/Serpiente/serpiente1.png", 0);
        Assets.serpiente2 = g.newPixmap("natureGame/Assets/Images/Resources/Serpiente/Serpiente2.png", 0);
        Assets.serpiente3 = g.newPixmap("natureGame/Assets/Images/Resources/Serpiente/Serpiente3.png", 0);
        Assets.serpiente4 = g.newPixmap("natureGame/Assets/Images/Resources/Serpiente/serpientev1.png", 0);
        Assets.serpiente5 = g.newPixmap("natureGame/Assets/Images/Resources/Serpiente/serpientev2.png", 0);
        Assets.serpiente6 = g.newPixmap("natureGame/Assets/Images/Resources/Serpiente/serpientev3.png", 0);
        System.out.println("lllll");
        Assets.lechuza = g.newPixmap("natureGame/Assets/Images/Resources/Hedwig/lechuza1.png", 0);
        Assets.lechuza2 = g.newPixmap("natureGame/Assets/Images/Resources/Hedwig/lechuza2.png", 0);
        Assets.lechuza3 = g.newPixmap("natureGame/Assets/Images/Resources/Hedwig/lechuza3.png", 0);
        Assets.lechuza4 = g.newPixmap("natureGame/Assets/Images/Resources/Hedwig/lechuzav1.png", 0);
        Assets.lechuza5 = g.newPixmap("natureGame/Assets/Images/Resources/Hedwig/lechuzav2.png", 0);
        Assets.lechuza6 = g.newPixmap("natureGame/Assets/Images/Resources/Hedwig/lechuzav3.png", 0);
        Assets.load();
        game.setScreen(new MenuScreen((MyGame) game));
    }

    @Override
    public void present(float deltaTime) {
        super.present(deltaTime);
    }
}
