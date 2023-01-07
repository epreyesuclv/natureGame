package natureGame.prolog;

import org.jpl7.Query;

public class test {

    public test() {

    }

    public boolean runTest() {
        boolean valid = checkassert() && checkmapa();
        if (valid) System.out.println("Everything was fine");
        else System.out.println("somthing was wrong");
        return valid;
    }

    private boolean checkassert() {
        PrologQuery.addElementMapa(0, 0, 8,"tessst");
        return true;
    }

    private boolean checkmapa() {
        int solution = PrologQuery.getElementMapa(0, 0,"tessst");
        System.out.println("checking if " + 8 + " is equal to " + solution);
        return 8 == solution;
    }
}
