// 블로그 참고했습니다

class Solution {
    static int result;

    public int solution(int N, int number) {
        int answer = 0;
        result = 9;
        dp(N, number, 0, 0);
        answer = (result > 8) ? -1 : result;
        return answer;
    }

    public void dp(int N, int number, int cnt, int num) {
        if (cnt > 8) {
            return;
        }

        if (num == number) {
            result = Math.min(result, cnt);
            return;
        }

        int temp = 0;
        for (int i = 0; i + cnt < 9; i++) {
            temp = temp * 10 + N;
            dp(N, number, cnt + 1 + i, num + temp);
            dp(N, number, cnt + 1 + i, num - temp);
            dp(N, number, cnt + 1 + i, num * temp);
            dp(N, number, cnt + 1 + i, num / temp);
        }
    }
}
