public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static void chk(String n, Object e, Object g) {
        t++; if (e.equals(g)) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }
    static void chkB(String n, boolean r) {
        t++; if (r) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n);
    }

    public static void main(String[] args) {
        Goomba g = new Goomba();
        chk("initial status",      "walking", g.getStatus());
        chkB("initial isAlive",     g.isAlive());
        g.stomp();
        chk("status after stomp",  "dead",    g.getStatus());
        chkB("isAlive after stomp", !g.isAlive());

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
