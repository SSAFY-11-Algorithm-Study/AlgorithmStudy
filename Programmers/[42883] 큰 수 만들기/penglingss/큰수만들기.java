class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        int idx = 0;
        for(int i = 0; i < number.length() - k; i++) {
            char max = '0';
            for(int j = idx; j <= i + k; j++) {
                if(max < number.charAt(j)) {
                    idx = j + 1;
                    max = number.charAt(j);
                }
            }
            answer.append(max);
        }
        return answer.toString();
    }
}
