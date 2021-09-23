package week8;

//감이 안와서 풀이 참고했습니다

class Solution5 {
    public int solution(String word) {
        int answer = 0;
        
        int len = word.length();
        int size = 5;
        
        char[] ch = {'A', 'E', 'I', 'O', 'U'};
        int[] diff = {781, 156, 31, 6, 1};
        
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < size; ++j) {
                if (ch[j] == word.charAt(i)) {
                    answer += 1 + j * diff[i];
                }
            }
        }
        
        return answer;
    }
}
