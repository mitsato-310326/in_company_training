public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static void chk(String n, int e, int g) {
        t++; if (e == g) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        chk("sumargs([3,5])",     8, SumArgs.sumargs(new String[]{"3", "5"}));
        chk("sumargs([1,2,3])",   6, SumArgs.sumargs(new String[]{"1", "2", "3"}));
        chk("sumargs([10,-3,2])", 9, SumArgs.sumargs(new String[]{"10", "-3", "2"}));
        chk("sumargs([0])",       0, SumArgs.sumargs(new String[]{"0"}));

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
