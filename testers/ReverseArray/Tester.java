public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static String cap(int[] arr) {
        java.io.ByteArrayOutputStream b = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(b));
        try { ReverseArray.reversearray(arr); } finally { System.setOut(OUT); }
        return b.toString().trim().replace("\r\n", "\n");
    }

    static void chk(String n, String e, String g) {
        t++; if (e.equals(g)) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        chk("reversearray([1,2,3,4,5])", "5, 4, 3, 2, 1", cap(new int[]{1,2,3,4,5}));
        chk("reversearray([1])",         "1",              cap(new int[]{1}));
        chk("reversearray([3,1,2])",     "2, 1, 3",        cap(new int[]{3,1,2}));
        chk("reversearray([10,20])",     "20, 10",         cap(new int[]{10,20}));

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
