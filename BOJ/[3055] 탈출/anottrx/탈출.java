import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        Queue<int[]> hedgehog = new LinkedList<>(); // 고슴도치 이동경로
        Queue<int[]> water = new LinkedList<>(); // 물 이동 경로
        int[] beaver = new int[2]; // 최종 목적지
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'S') {
                    hedgehog.add(new int[]{i, j});
                } else if (map[i][j] == 'D') {
                    beaver = new int[]{i, j};
                } else if (map[i][j] == '*') {
                    water.add(new int[]{i, j});
                }
            }
        }

        boolean[][] visited = new boolean[R][C];
        int cnt = 0;
        String answer = "KAKTUS"; // 답

        finding:
        while (!hedgehog.isEmpty()) {
            cnt++; // 시간

            int size = water.size(); // 물부터 이동시키기
            for (int i = 0; i < size; i++) {
                int[] cur = water.poll();
                visited[cur[0]][cur[1]] = true;
                for (int j = 0; j < 4; j++) {
                    int dx = cur[0] + d[j][0];
                    int dy = cur[1] + d[j][1];
                    if (dx >= 0 && dx < R && dy >= 0 && dy < C && map[dx][dy] == '.') {
                        map[dx][dy] = '*';
                        water.add(new int[]{dx, dy});
                    }
                }
            }

            size = hedgehog.size();
            for (int i = 0; i < size; i++) {
                int[] cur = hedgehog.poll();
                map[cur[0]][cur[1]] = '.';
                visited[cur[0]][cur[1]] = true;
                for (int j = 0; j < 4; j++) {
                    int dx = cur[0] + d[j][0];
                    int dy = cur[1] + d[j][1];
                    if (dx >= 0 && dx < R && dy >= 0 && dy < C && !visited[dx][dy]) {
                        if (map[dx][dy] == '.') {
                            map[dx][dy] = 'S';
                            hedgehog.add(new int[]{dx, dy});
                        } else if (map[dx][dy] == 'D') { // 비버 굴에 도착하면 끝내기
                            answer = String.valueOf(cnt);
                            break finding;
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
