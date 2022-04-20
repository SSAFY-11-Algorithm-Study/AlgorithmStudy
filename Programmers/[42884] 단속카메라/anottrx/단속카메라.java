// 정렬까지는 생각했지만 해결방법은 생각하지 못해 검색으로 해결했다
// 오래 걸린 이유는 정렬할 때 적당히 -1 또는 0만 리턴시켰기 때문이다. 앞으로는 제대로 정렬해야겠다

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, new Comparator<int[]>() { // 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        answer = 1; // 기본적으로 답은 1
        int curFin = routes[0][1]; // 가장 먼저 나가는 차량 위치를 저장
        for (int i = 1; i < routes.length; i++) {
            if (routes[i][0] <= curFin && routes[i][1] >= curFin) {
            } else { // 사이 범위에 없다면
                curFin = routes[i][1]; // 위치 재설정
                answer++; // 답 1 증가
            }
        }
        return answer;
    }
}
