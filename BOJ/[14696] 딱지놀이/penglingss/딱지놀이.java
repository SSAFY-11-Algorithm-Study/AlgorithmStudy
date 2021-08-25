import java.util.Scanner;

public class boj14696 {
    static int N;
    static int[] as, bs;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int n = 1; n <= N; n++) {
            int aCnt = sc.nextInt();
            as = new int[5];
            for (int i = 0; i < aCnt; i++) {
                as[sc.nextInt()]++;
            }

            int bCnt = sc.nextInt();
            bs = new int[5];
            for (int i = 0; i < bCnt; i++) {
                bs[sc.nextInt()]++;
            }
            System.out.println(winner(as, bs, 4)); // 별갯수부터 비교
        }
    }

    private static char winner(int[] as, int[] bs, int cnt) {
        if(cnt == 0) return 'D'; // 마지막까지 가면 비긴것
        if (as[cnt] != bs[cnt]) {
            return as[cnt] > bs[cnt] ? 'A' : 'B';
        }
        return winner(as, bs, cnt - 1);
    }
}
