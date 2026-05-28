public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static String cap(int a, int b) {
        java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(buf));
        try { SumOutput.printsum(a, b); } finally { System.setOut(OUT); }
        return buf.toString().trim().replace("\r\n", "\n");
    }

    static void chk(String n, String e, String g) {
        t++; if (e.equals(g)) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        chk("printsum(3,5)",   "8",  cap(3, 5));
        chk("printsum(0,0)",   "0",  cap(0, 0));
        chk("printsum(-1,1)",  "0",  cap(-1, 1));
        chk("printsum(10,20)", "30", cap(10, 20));

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
