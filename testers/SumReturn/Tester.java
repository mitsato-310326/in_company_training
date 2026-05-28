public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static void chk(String n, int e, int g) {
        t++; if (e == g) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        chk("returnsum(3,5)",     8,   SumReturn.returnsum(3, 5));
        chk("returnsum(0,0)",     0,   SumReturn.returnsum(0, 0));
        chk("returnsum(-1,1)",    0,   SumReturn.returnsum(-1, 1));
        chk("returnsum(100,200)", 300, SumReturn.returnsum(100, 200));

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
