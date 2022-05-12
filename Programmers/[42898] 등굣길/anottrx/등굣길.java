// x좌표, y좌표가 모두 뒤집힌 문제였습니다.

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int[][] map = new int[n][m];
        map[0][0] = 1;
        for (int i = 0; i < puddles.length; i++) {
            map[puddles[i][1] - 1][puddles[i][0] - 1] = -1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != -1) {
                    if (i > 0 && map[i - 1][j] != -1) {
                        map[i][j] += map[i - 1][j];
                    }
                    if (j > 0 && map[i][j - 1] != -1) {
                        map[i][j] += map[i][j - 1];
                    }
                    map[i][j] %= 1000000007;
                }
            }
        }

        answer = map[n - 1][m - 1] % 1000000007;
        return answer;
    }
}
