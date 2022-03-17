class Solution {
    public int solution(int n) {
        int answer = 0;
        int cnt = change(n);
        while (true) {
            n++;
            int cnt2 = change(n);
            if (cnt == cnt2) {
                answer = n;
                break;
            }
        }
        return answer;
    }

    public static int change(int n) { // 이진법으로 변환 후 1의 개수 세기
        int cnt = 1;
        String result = Integer.toBinaryString(n); 
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '1') {
                cnt++;
            }
        }
        return cnt;
    }
}
