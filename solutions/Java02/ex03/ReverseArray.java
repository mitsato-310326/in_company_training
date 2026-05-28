public class ReverseArray {
    public static void reversearray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (i < arr.length - 1) sb.append(", ");
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }
}
