public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static String cap(String[] args) {
        java.io.ByteArrayOutputStream b = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(b));
        try { SumArgs.main(args); } finally { System.setOut(OUT); }
        return b.toString().trim().replace("\r\n", "\n");
    }

    static void chk(String n, String e, String g) {
        t++; if (e.equals(g)) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        chk("main([3,5])",     "8", cap(new String[]{"3", "5"}));
        chk("main([1,2,3])",   "6", cap(new String[]{"1", "2", "3"}));
        chk("main([10,-3,2])", "9", cap(new String[]{"10", "-3", "2"}));
        chk("main([0])",       "0", cap(new String[]{"0"}));

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
