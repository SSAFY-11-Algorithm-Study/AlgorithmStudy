// 해결 방법을 알고 나서 문제를 풀 때, String이 아닌 StringBuilder로 진행했는데 에러가 발생했다
// dfs 인자로 StringBuilder를 넣었는데 이미 완성이 되고나서도 계속해서 append가 발생한 점이 문제였다
// 간단하게 String으로 하니 해결이 되었다

import java.util.ArrayList;
import java.util.Collections;

class Solution {
    static ArrayList<String> results; 
    static boolean[] visited;
    static int total;

    public String[] solution(String[][] tickets) {
        String[] answer = {};
        total = tickets.length;
        results = new ArrayList<>();
        visited = new boolean[total];

        findWay("ICN", 0, "ICN", tickets);

        Collections.sort(results); // 정렬
        answer=results.get(0).split(" "); // 띄어쓰기로 자르면 매우 쉽다
        return answer;
    }

    private static void findWay(String curPos, int curCnt, String route, String[][] tickets) {
        if (curCnt == total) {
            results.add(route.toString());
            return;
        }

        for (int i = 0; i < total; i++) {
            if (!visited[i] && tickets[i][0].equals(curPos)) {
                visited[i] = true;
                findWay(tickets[i][1], curCnt + 1, route + " "+tickets[i][1], tickets);
                visited[i] = false;
            }
        }
    }
}
