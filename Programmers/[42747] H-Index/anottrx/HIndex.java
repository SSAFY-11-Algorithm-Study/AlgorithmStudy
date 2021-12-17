class Solution {
    public int solution(int[] citations) {
        int answer = 0;

        // citations 정렬할 필요 없다
        int cur = citations[citations.length - 1]; // 답은 citations 배열 안에 없을 수도 있다
        finding: for (int i = cur; i >= 0; i--) {
            int cnt = 0;
            for (int j = citations.length - 1; j >= 0; j--) {
                if (citations[j] >= cur) {
                    cnt++;
                }
                if (cnt >= cur && cur >= (citations.length - cnt + 1)) {
                    answer = cur; // 최댓값
                    break finding;
                }
            }
            cur--;
        }

        return answer;
    }
}
