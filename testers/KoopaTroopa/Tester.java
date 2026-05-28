import java.lang.reflect.*;

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

    public static void main(String[] args) throws Exception {
        KoopaTroopa k = new KoopaTroopa();
        chk("initial status",  "walking", k.getStatus());
        chkB("initial isAlive", k.isAlive());

        Field reversed = KoopaTroopa.class.getDeclaredField("reversed");
        reversed.setAccessible(true);
        chkB("reversed initial == false", !(boolean) reversed.get(k));

        k.stomp();
        chk("status after 1st stomp", "stop", k.getStatus());
        chkB("isAlive after stomp",    k.isAlive());
        chkB("reversed after 1st stomp == true", (boolean) reversed.get(k));

        k.stomp();
        chk("status after 2nd stomp", "spin", k.getStatus());

        k.stomp();
        chk("status after 3rd stomp", "stop", k.getStatus());

        k.stomp();
        chk("status after 4th stomp", "spin", k.getStatus());

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
