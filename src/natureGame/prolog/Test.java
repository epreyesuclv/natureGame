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
                && checkGetsDeath()
                && checkDeath()
                && checkGetAllDeathByRefer();
        if (valid) System.out.println("Everything was fine");
        else System.out.println("somthing was wrong");
        queryTool.deleteMapa();
        return valid;
    }

    private boolean check(int solution, int mustBe) {
        System.out.println("[Test] checking if " + mustBe + " is equal to " + solution);
        return solution == mustBe;
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
        return check(solution, 0);
    }

    private boolean checkGetsLivings() {
        int solution = queryTool.getAmountLivings();
        return check(solution, 3);
    }

    private boolean checkGetsDeath() {
        int solution = queryTool.getAmountDeath();
        return check(solution, 1);

    }

    private boolean checkGetByRefer() {
        int solution = queryTool.getAmountByRefer(3);
        return check(solution, 1);

    }

    private boolean checkDeath() {
        queryTool.addDeath(3);
        //System.out.println("checking if " + 1 + " is equal to " + solution);
        return true;
    }

    private boolean checkGetAllDeathByRefer() {
        int solution = queryTool.getAllDeathByRefer(3);
        return check(solution, 1);
    }
}
