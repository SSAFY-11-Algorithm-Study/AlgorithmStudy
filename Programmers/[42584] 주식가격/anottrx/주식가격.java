class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length]; // 답 길이 설정

        for (int i = 0; i < prices.length; i++) {
            int cnt = 0;
            int curPrice = prices[i];
            for (int j = i; j < prices.length - 1; j++) { // 맨마지막 price는 무조건 0초이기 때문에 제외
                if (curPrice > prices[j]) { // 현재 가격보다 낮아지면 끝내기
                    break;
                } else {
                    cnt++;
                }
            }
            answer[i] = cnt;
        }
        return answer;
    }
}
