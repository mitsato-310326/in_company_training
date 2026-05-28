public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static void chk(String n, double e, double g) {
        t++; if (Math.abs(e - g) < 1e-9) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        chk("returndivide(7,2)",  3.5,  DivideReturn.returndivide(7, 2));
        chk("returndivide(10,4)", 2.5,  DivideReturn.returndivide(10, 4));
        chk("returndivide(6,3)",  2.0,  DivideReturn.returndivide(6, 3));
        chk("returndivide(1,4)",  0.25, DivideReturn.returndivide(1, 4));

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
