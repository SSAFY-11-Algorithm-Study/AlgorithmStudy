import java.util.*;

class Solution {
    
    static int INF = 102;

    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = INF;
            }
        }
        for (int i = 0; i < results.length; i++) {
            dist[results[i][0]][results[i][1]] = 1; // 승리
            dist[results[i][1]][results[i][0]] = -1; // 패배
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i != j && dist[i][k] < INF && dist[k][j] < INF && dist[i][k] * dist[k][j] > 0) {
                        // i번 선수와 j번 선수의 승패를 계산하려는 경우 
                        // -> i번 선수와 k번 선수, k번 선수와 j번 선수 각각 기록이 존재하고
                        // 그 두 기록 모두 동일한 결과를 가져야 한다 (모두 승리했거나 모두 패배했거나)
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            boolean isKnown = true;
            for (int j = 1; j <= n; j++) {
                if (i != j && dist[i][j] == INF) { // 하나라도 INF값이 존재한다면 등수가 결정 안 난 것
                    isKnown = false;
                    break;
                }
            }
            if (isKnown) {
                answer++;
            }
        }
        return answer;
    }
}
