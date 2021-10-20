import java.util.Scanner;

public class Main {
    static int N, M, r, c, d, count;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        r = sc.nextInt();
        c = sc.nextInt();
        d = sc.nextInt();
        count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        clean(r, c, d);
        System.out.println(count + 1);

    }

    private static void clean(int vr, int vc, int vd) {
        map[vr][vc] = -1;

        for(int i = 0; i < 4; i++) {
            vd -= 1;
            if(vd == -1) vd = 3;

            int nextR = vr + dr[vd];
            int nextC = vc + dc[vd];
            if(nextR >= 0 && nextC >= 0 && nextR < N && nextC < M) {
                if(map[nextR][nextC] == 0) {
                    count++;
                    clean(nextR, nextC, vd);
                    return;
                }
            }
        }

        int newD = (vd + 2) % 4;
        int newR = vr + dr[newD];
        int newC = vc + dc[newD];
        if(newR >= 0 && newC >= 0 && newR < N && newC < M && map[newR][newC] != 1) {
            clean(newR, newC, vd);
        }
    }
}
