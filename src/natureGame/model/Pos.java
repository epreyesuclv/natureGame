package natureGame.model;
/**
 * clase utilizada para facilitar el calculo en la clase World
 * guarda una posision y una referencia
 */

//lis
public class Pos {
    private int x;
    private int y;
    private int refer;

    public Pos(int x, int y, int refer) {
        this.x = x;
        this.y = y;
        this.refer = refer;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRefer() {
        return refer;
    }

    public void setRefer(int refer) {
        this.refer = refer;
    }


    public boolean equals(Pos o) {
        Pos pos = o;
        return x == pos.x &&
                y == pos.y &&
                refer == pos.refer;
    }
}
