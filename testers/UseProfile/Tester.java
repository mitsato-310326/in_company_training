public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static void chk(String n, Object e, Object g) {
        t++; if (e.equals(g)) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        Profile r1 = UseProfile.create("Taro", 25);
        chk("create(\"Taro\",25).getName()", "Taro", r1.getName());
        chk("create(\"Taro\",25).getAge()",  25,     r1.getAge());

        Profile r2 = UseProfile.create("Hanako", 30);
        chk("create(\"Hanako\",30).getName()", "Hanako", r2.getName());
        chk("create(\"Hanako\",30).getAge()",  30,       r2.getAge());

        Profile r3 = UseProfile.create("Bob", 0);
        chk("create(\"Bob\",0).getName()", "Bob", r3.getName());
        chk("create(\"Bob\",0).getAge()",  0,     r3.getAge());

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
