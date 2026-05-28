public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    static void chk(String n, int e, int g) {
        t++; if (e == g) { p++; OUT.println("OK " + n); }
        else OUT.println("KO " + n + ": expected [" + e + "] got [" + g + "]");
    }

    public static void main(String[] args) {
        // 正常値は返す
        try {
            int r = AgeValidator.validate(25);
            chk("validate(25)", 25, r);
        } catch (InvalidAgeException e) {
            t++; OUT.println("KO validate(25): expected [25] got [InvalidAgeException: " + e.getMessage() + "]");
        }

        try {
            int r = AgeValidator.validate(0);
            chk("validate(0)", 0, r);
        } catch (InvalidAgeException e) {
            t++; OUT.println("KO validate(0): expected [0] got [InvalidAgeException]");
        }

        try {
            int r = AgeValidator.validate(150);
            chk("validate(150)", 150, r);
        } catch (InvalidAgeException e) {
            t++; OUT.println("KO validate(150): expected [150] got [InvalidAgeException]");
        }

        // 不正値はInvalidAgeExceptionをthrow
        try {
            AgeValidator.validate(-1);
            t++; OUT.println("KO validate(-1): expected [InvalidAgeException] got [no exception]");
        } catch (InvalidAgeException e) {
            t++; p++; OUT.println("OK validate(-1) throws InvalidAgeException");
        }

        try {
            AgeValidator.validate(200);
            t++; OUT.println("KO validate(200): expected [InvalidAgeException] got [no exception]");
        } catch (InvalidAgeException e) {
            t++; p++; OUT.println("OK validate(200) throws InvalidAgeException");
        }

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
