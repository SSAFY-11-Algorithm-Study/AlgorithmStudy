import java.util.HashMap;
import java.util.Scanner;

public class boj2578 {
    static boolean[][] visit;
    static int count;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        visit = new boolean[5][5];
        count = 0;
        HashMap<Integer, int[]> map = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map.put(sc.nextInt(), new int[]{i, j});
            }
        }

        int answer = 0;
        while (count < 3) {
            answer++;
            int num = sc.nextInt();
            int[] index = map.get(num);
            visit[index[0]][index[1]] = true;
            chk(index[0], index[1]);
        }

        System.out.println(answer);

    }

    private static void chk(int idx1, int idx2) {
        int cnt = 2;
        for (int i = 0; i < 5; i++) { // 같은 행 검사
            if (!visit[idx1][i]) {
                cnt--;
                break;
            }
        }
        for (int i = 0; i < 5; i++) { // 같은 열 검사
            if (!visit[i][idx2]) {
                cnt--;
                break;
            }
        }
        if (idx1 == idx2) { // 0,0 ~ 4,4 대각선
            for (int i = 0; i < 5; i++) {
                if (!visit[i][i]) {
                    break;
                }
                if (i == 4) cnt++;
            }
        }
        if (idx1 + idx2 == 4) { // 0,4 ~ 4,0 대각선
            for (int i = 0; i < 5; i++) {
                if (!visit[i][4 - i]) {
                    break;
                }
                if (i == 4) cnt++;
            }
        }
        count += cnt;
    }
}
