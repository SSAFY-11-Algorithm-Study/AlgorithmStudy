class Solution {
    public int solution(String word) {
        int answer = 0;
        String aeiou = "AEIOU";
        int[] th = {781, 156, 31, 6, 1}; // 각 자리가 바뀌는데 {781, 156, 31, 6, 1} 만큼이 필요
        for(int i = 0; i < word.length(); i++) {
            int n = aeiou.indexOf(word.charAt(i));
            answer += th[i] * n;
        }
        return answer + word.length();
    }
}
