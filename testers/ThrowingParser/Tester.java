public class Tester {
    static final java.io.PrintStream OUT = System.out;
    static int p = 0, t = 0;

    public static void main(String[] args) {
        // 有効な文字列 → 整数を返す
        try {
            int r = ThrowingParser.parse("42");
            t++;
            if (r == 42) { p++; OUT.println("OK parse(\"42\") == 42"); }
            else OUT.println("KO parse(\"42\"): expected [42] got [" + r + "]");
        } catch (NumberFormatException e) {
            t++; OUT.println("KO parse(\"42\"): expected [42] got [NumberFormatException]");
        }

        try {
            int r = ThrowingParser.parse("-7");
            t++;
            if (r == -7) { p++; OUT.println("OK parse(\"-7\") == -7"); }
            else OUT.println("KO parse(\"-7\"): expected [-7] got [" + r + "]");
        } catch (NumberFormatException e) {
            t++; OUT.println("KO parse(\"-7\"): expected [-7] got [NumberFormatException]");
        }

        // 無効な文字列 → NumberFormatExceptionをthrow (内部でcatchしない)
        try {
            ThrowingParser.parse("abc");
            t++; OUT.println("KO parse(\"abc\"): expected [NumberFormatException] got [no exception]");
        } catch (NumberFormatException e) {
            t++; p++; OUT.println("OK parse(\"abc\") throws NumberFormatException");
        }

        try {
            ThrowingParser.parse("3.14");
            t++; OUT.println("KO parse(\"3.14\"): expected [NumberFormatException] got [no exception]");
        } catch (NumberFormatException e) {
            t++; p++; OUT.println("OK parse(\"3.14\") throws NumberFormatException");
        }

        OUT.println(p + "/" + t + " passed");
        System.exit(p == t ? 0 : 1);
    }
}
