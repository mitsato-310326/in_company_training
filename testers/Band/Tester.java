public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static void chk(String n, boolean r) {
        t++; if (r) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n);
    }

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
        // {0,1,2,3} -> Donkey, Dog, Cat, Chicken
        Bremen[] band = Band.formBand(new int[]{0, 1, 2, 3});
        chk("length == 4",         band.length == 4);
        chk("band[0] is Donkey",   band[0] instanceof Donkey);
        chk("band[1] is Dog",      band[1] instanceof Dog);
        chk("band[2] is Cat",      band[2] instanceof Cat);
        chk("band[3] is Chicken",  band[3] instanceof Chicken);
        chk("all instanceof Bremen",
            band[0] instanceof Bremen && band[1] instanceof Bremen &&
            band[2] instanceof Bremen && band[3] instanceof Bremen);
        chk("Donkey implements Walk", band[0] instanceof Walk);
        chk("Dog implements Walk",    band[1] instanceof Walk);
        chk("Cat implements Walk",    band[2] instanceof Walk);
        chkOut("Donkey.perform()",    "IA",                () -> band[0].perform());
        chkOut("Dog.perform()",       "Bow-wow",           () -> band[1].perform());
        chkOut("Cat.perform()",       "Meow",              () -> band[2].perform());
        chkOut("Chicken.perform()",   "Cock-a-doodle-doo", () -> band[3].perform());

        // {3,0} -> Chicken, Donkey
        Bremen[] band2 = Band.formBand(new int[]{3, 0});
        chk("band2 length == 2",    band2.length == 2);
        chk("band2[0] is Chicken",  band2[0] instanceof Chicken);
        chk("band2[1] is Donkey",   band2[1] instanceof Donkey);

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
