public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static String cap(String s) {
        java.io.ByteArrayOutputStream b = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(b));
        try { PrintString.printstring(s); } finally { System.setOut(OUT); }
        return b.toString().trim().replace("\r\n", "\n");
    }

    static void chk(String n, String e, String g) {
        t++; if (e.equals(g)) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        chk("printstring(\"Hello\")", "Hello", cap("Hello"));
        chk("printstring(\"Java\")",  "Java",  cap("Java"));
        chk("printstring(\"12345\")", "12345", cap("12345"));

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
