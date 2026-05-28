public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static void chkOut(String n, String expected, Runnable r) {
        java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        java.io.PrintStream old = System.out;
        System.setOut(new java.io.PrintStream(buf));
        r.run();
        System.setOut(old);
        String got = buf.toString().trim();
        t++;
        if (expected.equals(got)) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + expected + "] got [" + got + "]");
    }

    public static void main(String[] args) {
        chkOut("helloworld()", "Hello, World!", () -> HelloWorld.helloworld());

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
