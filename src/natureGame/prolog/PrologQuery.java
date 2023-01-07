package natureGame.prolog;


import org.jpl7.Query;
import org.jpl7.Term;

import java.util.Map;

public class PrologQuery {

    public static int getElementMapa(int x, int y, String mapa) {
        String buildquery = mapa + "(" + x + "," + y + ",P).";
        Query q = new Query(buildquery);
        q.open();
        Map<String, Term> m = q.getSolution();
        if (m == null) {
            return 0;
        }
        int result = Integer.parseInt(m.get("P").toString());
        q.close();
        return result;
    }

    public static void addElementMapa(int x, int y, int refer, String mapa) {
        String build = "assert(" + mapa + "(" + x + "," + y + "," + refer + ")).";
        Query q = new Query(build);
        q.open();
        q.getSolution();
        q.close();
    }

    public static void eliminateElementMapa(int x, int y, String mapa) {
        String build = "retract(" + mapa + "(" + x + "," + y + ",_)).";
        Query q = new Query(build);
        q.open();
        q.getSolution();
        q.close();
    }

}
