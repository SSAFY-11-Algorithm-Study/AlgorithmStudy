import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int idx = people.length - 1;
        
        Arrays.sort(people);
        System.out.println(people[0]);
        
        for(int i = 0; i <= idx; i++) {
            while(idx > i && people[i] + people[idx--] > limit) {
                answer++;
            }
            answer++;
        }
        
        return answer;
    }
}
