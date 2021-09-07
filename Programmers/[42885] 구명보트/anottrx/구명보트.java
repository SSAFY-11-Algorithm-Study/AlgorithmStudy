import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people); // 정렬
        
        int start = 0; // 시작 (앞에서 뒤로 이동)
        int end = people.length - 1; // 끝 (뒤에서 앞으로 이동)
        while(true) {
            if(people[start]+people[end] <= limit) { 
                start++; // 두명 더해서 limit 이하라면 start+1
            }
            end--; // limit 이상이하 상관 없이 무조건 end-1
            answer++;
            if(start > end) { 
                break;
            } else if(start==end) { // 마지막에 같다면 횟수 1 추가
                answer++;
                break;
            }
        }
        
        return answer;
    }
}
