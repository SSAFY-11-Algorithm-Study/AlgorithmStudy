import java.util.HashMap;
import java.util.Scanner;

public class boj9375 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int N = sc.nextInt();
            sc.nextLine();
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < N; i++) {
                String[] clothes = sc.nextLine().split(" ");
                map.put(clothes[1], map.getOrDefault(clothes[1], 0) + 1);
            }
            int answer = 1;
            for (int v : map.values()) {
                answer *= (v + 1);
            }
            System.out.println(answer - 1);

        }
    }
}
