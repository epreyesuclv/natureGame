package natureGame.framework.graphics;
/**
 * es el root utilizadon en la clase Render,
 * se hizo con el objetivo de poder cambiar
 * el tamanho en tiempo de ejecucion pues los metodos setWidth() y setHegth() son protected
 */

import javafx.scene.layout.AnchorPane;

public class MyRoot extends AnchorPane {
    public MyRoot(int x, int y) {
        changeSize(x, y);

    }

    public void changeSize(int x, int y) {
        setWidth(x);
        setHeight(y);
    }

}
