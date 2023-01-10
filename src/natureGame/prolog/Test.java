package natureGame.prolog;

public class Test {
    private PrologQuery queryTool;

    public Test() {
        this.queryTool = PrologQuery.getInstance();
    }

    public boolean runTest() {
        boolean valid = checkassert()
                && checkmapa()
                && checkGetByRefer()
                && checkGetsLivings()
                && checkGetsDeath();
        if (valid) System.out.println("Everything was fine");
        else System.out.println("somthing was wrong");
        queryTool.deleteMapa();
        return valid;
    }

    private boolean checkassert() {

        queryTool.addElementMapa(0, 0, 2, "mapa2");
        queryTool.addElementMapa(0, 1, 1, "mapa2");
        queryTool.addElementMapa(1, 0, 0, "mapa2");
        queryTool.addElementMapa(1, 1, 7, "mapa2");

        queryTool.addElementMapa(0, 0, 0, "mapa1");
        queryTool.addElementMapa(0, 1, 5, "mapa1");
        queryTool.addElementMapa(1, 0, 3, "mapa1");
        queryTool.addElementMapa(1, 1, 0, "mapa1");
        return true;
    }

    private boolean checkmapa() {
        int solution = queryTool.getElementMapa(0, 0, "mapa1");
        System.out.println("checking if " + 0 + " is equal to " + solution);
        return 0 == solution;
    }

    private boolean checkGetsLivings() {
        int solution = queryTool.getAmountLivings();
        System.out.println("checking if " + 3 + " is equal to " + solution);
        return 3 == solution;
    }

    private boolean checkGetsDeath() {
        int solution = queryTool.getAmountDeath();
        System.out.println("checking if " + 1 + " is equal to " + solution);
        return 1 == solution;

    }

    private boolean checkGetByRefer() {
        int solution = queryTool.getAmountByRefer(3);
        System.out.println("checking if " + 1 + " is equal to " + solution);
        return 1 == solution;

    }
}
