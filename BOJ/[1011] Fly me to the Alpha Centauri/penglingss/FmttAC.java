import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int distance = y - x;
            int max = (int)Math.sqrt(distance);

            int res = 0;

            if (max == Math.sqrt(distance)) res = max * 2 - 1;
            else if (distance <= max * max + max) res = max * 2;
            else res = max * 2 + 1;

            System.out.println(res);
        }
    }
}
