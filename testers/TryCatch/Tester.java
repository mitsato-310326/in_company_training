public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static void chk(String n, String e, String g) {
        t++; if (e.equals(g)) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        chk("parse(\"42\")",   "42",                  TryCatch.parse("42"));
        chk("parse(\"abc\")",  "Error: invalid input", TryCatch.parse("abc"));
        chk("parse(\"-5\")",   "-5",                  TryCatch.parse("-5"));
        chk("parse(\"3.14\")", "Error: invalid input", TryCatch.parse("3.14"));
        chk("parse(\"0\")",    "0",                   TryCatch.parse("0"));

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
