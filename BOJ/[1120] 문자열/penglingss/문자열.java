import java.util.Scanner;

public class boj1120 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();

        int dif = A.length();
        for (int i = 0; i <= B.length() - A.length(); i++) {
            dif = Math.min(dif, getDif(A, B, i));
        }
        System.out.println(dif);
    }

    public static int getDif(String A, String B, int startIdx) {
        int result = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(startIdx + i)) result++;
        }
        return result;
    }
}
