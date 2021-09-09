package week7;
import java.util.*;

public class PROG_구명보트 {
	
	class Solution {
	    public int solution(int[] people, int limit) {
	        int answer = 0;
	        int size = people.length;
	        
	        //정렬하고 시작
	        Arrays.sort(people);
	        
	        int start = 0;
	        int end = size-1;
	        
	        while(true){
	            //맨앞 , 맨뒤를 해보고 안되면 맨뒤를 땡겨서 무게를 낮춰봄
	            if(people[start] + people[end] > limit){
	                answer++;
	                end--;
	                
	            }
	            //limit 보다 같거나 작으면 태울수있으니까 start , end 각각하나씩 줄이고 answer++
	            else{
	                answer++;
	                start++;
	                end--;
	            }
	            
	            //종료
	            if(start>end)
	                break;
	        }
	        
	        
	        return answer;
	    }
	}

}
