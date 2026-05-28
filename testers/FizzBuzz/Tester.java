public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static String cap(int n) {
        java.io.ByteArrayOutputStream b = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(b));
        try { FizzBuzz.fizzbuzz(n); } finally { System.setOut(OUT); }
        return b.toString().trim().replace("\r\n", "\n");
    }

    static void chk(String n, String e, String g) {
        t++; if (e.equals(g)) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ":\n  expected: " + e.replace("\n", "|") + "\n  got:      " + g.replace("\n", "|"));
    }

    public static void main(String[] args) {
        chk("fizzbuzz(5)",  "1\n2\nFizz\n4\nBuzz", cap(5));
        chk("fizzbuzz(15)", "1\n2\nFizz\n4\nBuzz\nFizz\n7\n8\nFizz\nBuzz\n11\nFizz\n13\n14\nFizzBuzz", cap(15));

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
