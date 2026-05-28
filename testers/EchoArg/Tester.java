public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static String cap(String[] args) {
        java.io.ByteArrayOutputStream b = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(b));
        try { EchoArg.echoarg(args); } finally { System.setOut(OUT); }
        return b.toString().trim().replace("\r\n", "\n");
    }

    static void chk(String n, String e, String g) {
        t++; if (e.equals(g)) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        chk("echoarg([hello])",   "hello",   cap(new String[]{"hello"}));
        chk("echoarg([Java])",    "Java",    cap(new String[]{"Java"}));
        chk("echoarg([abc, xyz])", "abc",    cap(new String[]{"abc", "xyz"}));

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
