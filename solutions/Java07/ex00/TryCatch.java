public class TryCatch {
    public static String parse(String s) {
        try {
            return String.valueOf(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return "Error: invalid input";
        }
    }
}
