import java.util.Scanner;

public class Main {
    static int N, M, max, answer;
    static int[] trees;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        trees = new int[N];
        max = 1000000000;
        answer = 0;

        for (int i = 0; i < N; i++) {
            trees[i] = sc.nextInt();
        }

        bsearch();

        System.out.println(answer);
    }

    private static void bsearch() {
        int min = 0;
        while (min <= max) {
            int mid = (min + max) / 2;
            long length = cut(mid);
            if (length >= M) {
                answer = Math.max(answer, mid);
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
    }

    private static long cut(int l) {
        long length = 0;
        for (int i = 0; i < N; i++) {
            if(trees[i] <= l) continue;
            length += (trees[i] - l);
        }
        return length;
    }
}
