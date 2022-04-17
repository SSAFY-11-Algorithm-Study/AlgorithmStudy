// 참고 : https://velog.io/@qodlstjd12/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-Kakao-%EC%96%91%EA%B6%81-%EB%8C%80%ED%9A%8C-Java

class Solution {
    static int ryan[], max;

    public int[] solution(int n, int[] info) {
        int[] answer = {};

        answer = new int[11];
        ryan = new int[11];
        max = 0;
        getRyan(info, 1, n, answer);

        if (max == 0) { // 라이언이 우승할 수 없는 경우(무조건 지거나 비기는 경우)는 [-1] 리턴
            answer = new int[1];
            answer[0] = -1;
        }

        return answer;
    }

    private static void getRyan(int[] info, int cur, int n, int[] answer) {
        if (cur > n) {
            int ryanTotal = 0, apeachTotal = 0;
            for (int i = 0; i < 11; i++) {
                if (info[i] > 0 && info[i] >= ryan[i]) { // 어피치가 이긴 경우
                    apeachTotal += 10 - i;  // 과녁 점수의 개수를 10점부터 0점까지
                } else if (ryan[i] > 0 && ryan[i] > info[i]) { // 라이언이 이긴 경우
                    ryanTotal += 10 - i;
                }
            }
            if (ryanTotal - apeachTotal >= max) {
                for (int i = 0; i < 11; i++) {
                    answer[i] = ryan[i]; // 답 저장
                }
                max = ryanTotal - apeachTotal;
            }
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (ryan[i] <= info[i]) {
                ryan[i]++;
                getRyan(info, cur + 1, n, answer);
                ryan[i]--;
            } else {
                break; // 가지치기
            }
        }
    }
}
