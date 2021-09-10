import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] rank = new int[N];
        long dif = 0;

        for (int i = 0; i < N; i++) {
            rank[i] = sc.nextInt();
        }
        
        Arrays.sort(rank);

        for (int i = 0; i < N; i++) {
            dif += Math.abs(rank[i] - (i + 1));
        }
        
        System.out.println(dif);
    }
}
