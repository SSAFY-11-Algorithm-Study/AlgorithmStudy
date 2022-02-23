import java.util.Scanner;

public class BOJ16953 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        sc.close();

        int cnt = 0;
        while (true) {
            if (B == A) {
                break;
            } else if (B % 2 == 0 && B != 0) {
                B = B / 2;
                cnt++;
            } else if (B % 10 == 1) {
                B = (B - 1) / 10;
                cnt++;
            } else {
                cnt = -1;
                break;
            }
        }
        if (cnt == -1)
            System.out.println(cnt);
        else
            System.out.println(cnt + 1);
    }
}
