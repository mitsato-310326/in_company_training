public class SumArgs {
    public static int sumargs(String[] args) {
        int sum = 0;
        for (String s : args) {
            sum += Integer.parseInt(s);
        }
        return sum;
    }
}
