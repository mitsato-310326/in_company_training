public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static void chk(String n, boolean e, boolean g) {
        t++; if (e == g) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        chk("primecheck(2)",  true,  PrimeCheck.primecheck(2));
        chk("primecheck(7)",  true,  PrimeCheck.primecheck(7));
        chk("primecheck(13)", true,  PrimeCheck.primecheck(13));
        chk("primecheck(1)",  false, PrimeCheck.primecheck(1));
        chk("primecheck(4)",  false, PrimeCheck.primecheck(4));
        chk("primecheck(9)",  false, PrimeCheck.primecheck(9));

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
