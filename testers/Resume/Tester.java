public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static void chk(String n, String e, String g) {
        t++; if (e.equals(g)) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        // default constructor
        Resume r0 = new Resume();
        chk("default.getName()", "Unknown", r0.getName());
        chk("default.getAge()",  "0",       String.valueOf(r0.getAge()));
        chk("default.getJob()", "Unknown",  r0.getJob());

        // full constructor
        Resume r1 = new Resume("Yamada Taro", 25, "Engineer");
        chk("r1.getName()", "Yamada Taro", r1.getName());
        chk("r1.getAge()",  "25",          String.valueOf(r1.getAge()));
        chk("r1.getJob()",  "Engineer",    r1.getJob());

        // setter/getter
        Resume r2 = new Resume();
        r2.setName("Sato Hanako");
        r2.setAge(30);
        r2.setJob("Designer");
        chk("r2.getName()", "Sato Hanako", r2.getName());
        chk("r2.getAge()",  "30",          String.valueOf(r2.getAge()));
        chk("r2.getJob()",  "Designer",    r2.getJob());

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
