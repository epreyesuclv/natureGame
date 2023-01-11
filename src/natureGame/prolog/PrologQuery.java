package natureGame.prolog;

import org.jpl7.Query;
import org.jpl7.Term;

import java.util.Map;

public class PrologQuery {
    private static PrologQuery conn;

    private PrologQuery() {
        String consulta = "consult('src/natureGame/prolog/database.pl').";
        Query q = new Query(consulta);
        if (!q.hasSolution()) {
            System.out.println("No se pudo cargar la database");
        }
        q.close();
    }

    public static PrologQuery getInstance() {
        if (conn == null) conn = new PrologQuery();
        return conn;

    }

    public int getElementMapa(int x, int y, String mapa) {
        Map<String, Term> m = execute(mapa + "(" + x + "," + y + ",P).");
        if (m == null) {
            addElementMapa(x, y, 0, mapa);
            return 0;
        }
        int result = Integer.parseInt(m.get("P").toString());
        return result;
    }

    public void addElementMapa(int x, int y, int refer, String mapa) {
        execute("assertz(" + mapa + "(" + x + "," + y + "," + refer + ")).");
    }

    public void eliminateElementMapa(int x, int y, String mapa) {
        execute("retractall(" + mapa + "(" + x + "," + y + ",_)).");
    }

    public int getAmountLivings() {
        Map<String, Term> m = execute("getAllLivings(I).");
        if (m == null) return 0;
        int result = Integer.parseInt(m.get("I").toString());

        return result;
    }

    private Map<String, Term> execute(String build) {
        long time = System.nanoTime();
        Query q = new Query(build);
        q.open();
        Map<String, Term> m = q.getSolution();
        q.close();
        long currentTime = System.nanoTime() - time;
        System.out.println("[PrologQuery] executing query: " + build + " result : " + (m == null ? "null" : m.toString()) + " with time : " + currentTime);
        return m;
    }

    public int getAmountDeath() {
        Map<String, Term> m = execute("getAllDeath(I).");
        if (m == null) return 0;
        int result = Integer.parseInt(m.get("I").toString());

        return result;
    }

    public int getAmountByRefer(int r) {
        String build;
        if (r == 2)
            build = "getAllPlants(I).";
        else
            build = "getAllByRefer(" + r + ",I).";
        Map<String, Term> m = execute(build);
        if (m == null) return 0;
        int result = Integer.parseInt(m.get("I").toString());
        return result;
    }

    public void addDeath(int r) {
        String build = "assert(death(" + r + ")).";
        execute(build);
    }

    public int getAllDeathByRefer(int r) {
        String build = "getAllDeathByRefer(" + r + ",I).";
        Map<String, Term> m = execute(build);
        if (m == null) return 0;
        int result = Integer.parseInt(m.get("I").toString());
        return result;
    }

    void deleteMapa() {
        execute("retractall(mapa1(_,_,_)).");
        execute("retractall(mapa2(_,_,_)).");
    }

}
