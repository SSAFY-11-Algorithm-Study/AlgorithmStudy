import java.util.*;

class Solution {
    static int[][] d = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    numberOfArea++; // 영역 1 증가
                    int count = color(i, j, m, n, picture[i][j], picture, visited);
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, count); // 가장 큰 영역
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    static int color(int x, int y, int m, int n, int colorNum, int[][] picture, boolean[][] visited) {
        int count = 1; // 시작하는 영약
        visited[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { x, y });

        while (!q.isEmpty()) {
            int cur[] = q.poll();
            for (int i = 0; i < 4; i++) {
                int dx = cur[0] + d[i][0];
                int dy = cur[1] + d[i][1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && !visited[dx][dy] && picture[dx][dy] == colorNum) {
                    count++;
                    visited[dx][dy] = true;
                    q.offer(new int[] { dx, dy });
                }
            }
        }
        return count;
    }
}
