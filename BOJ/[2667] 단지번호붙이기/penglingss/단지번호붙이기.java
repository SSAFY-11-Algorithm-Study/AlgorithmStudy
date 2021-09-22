import java.util.*;

public class Main {
    static int N, aptCnt;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static PriorityQueue<Integer> answerList;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        answerList = new PriorityQueue<>((n1, n2) -> n1 - n2);

        sc.nextLine();
        for (int i = 0; i < N; i++) {
            char[] arr = sc.nextLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = arr[j] - '0';
            }
        }

        cntMap();

        System.out.println(aptCnt);
        while (!answerList.isEmpty()) {
            System.out.println(answerList.poll());
        }
    }

    private static void cntMap() {
        aptCnt = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    q.add(new int[]{i, j});
                    visit[i][j] = true;
                    aptCnt++;
                    int cnt = 1;
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int x = cur[0];
                        int y = cur[1];
                        for (int k = 0; k < 4; k++) {
                            int nextX = x + dx[k];
                            int nextY = y + dy[k];

                            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
                                if (map[nextX][nextY] == 1 && !visit[nextX][nextY]) {
                                    visit[nextX][nextY] = true;
                                    q.add(new int[]{nextX, nextY});
                                    cnt++;
                                }
                            }
                        }
                    }
                    answerList.add(cnt);
                }
            }
        }
    }
}
