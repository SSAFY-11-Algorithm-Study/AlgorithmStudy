class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        for (int x = 1; x <= (yellow / 2) + 1; x++) {
            // 노란색 격자의 가로(x), 세로(y) 정하기
            if (yellow % x != 0) {
                continue;
            }
            int y = yellow / x;

            // 개수 비교하기
            int totalExpect = 4 + 2 * x + 2 * y;
            if (totalExpect == brown) {
                answer = new int[] { y + 2, x + 2 };
                break;
            }
        }
        return answer;
    }
}
