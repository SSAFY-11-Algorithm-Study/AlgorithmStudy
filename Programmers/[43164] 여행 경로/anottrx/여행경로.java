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

        Collections.sort(results);
        answer=results.get(0).split(" ");
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
