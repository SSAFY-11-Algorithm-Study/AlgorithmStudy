import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);
        int cur = citations[citations.length - 1]; // 답은 citations 배열 안에 없을 수도 있다
        finding: for (int i = cur; i >= 0; i--) {
            int cnt = 0;
            for (int j = citations.length - 1; j >= 0; j--) {
                if (citations[j] >= cur) { // 인용된 논문 횟수 세기
                    cnt++;
                }
                if (cnt >= cur && cur >= (citations.length - cnt + 1)) { // h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용
                    answer = cur; // 최댓값
                    break finding;
                }
            }
            cur--;
        }

        return answer;
    }
}
