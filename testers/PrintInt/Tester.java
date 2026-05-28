public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static String cap(int n) {
        java.io.ByteArrayOutputStream b = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(b));
        try { PrintInt.printint(n); } finally { System.setOut(OUT); }
        return b.toString().trim().replace("\r\n", "\n");
    }

    static void chk(String n, String e, String g) {
        t++; if (e.equals(g)) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        chk("printint(42)",  "42",  cap(42));
        chk("printint(-1)",  "-1",  cap(-1));
        chk("printint(0)",   "0",   cap(0));
        chk("printint(100)", "100", cap(100));

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
