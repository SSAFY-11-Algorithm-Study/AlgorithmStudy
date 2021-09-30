package week10;

//자꾸 시간초과나는데 여기서 줄일방법이 생각이 안남..
	import java.util.*;
	class Solution {
	    public int solution(int[] stones, int k) {
	        int answer = 0;
	        
	        int start = 1;
	        int end = 200000;
	        int mid = 0;
	        
	        while(start<=end){
	            //건너는 사람 수를 mid로 택한다
	            mid = (start + end) /2;
	            
	            
	            //연속으로 k개만큼 0이 존재하는지 체크
	            int cnt = 0;
	            for(int i = 0 ; i < stones.length; i ++){
	                int temp = stones[i] - mid;
	                if(temp <= 0)
	                   cnt++;
	                else 
	                    cnt = 0;
	                
	                //연속으로 k개만큼 존재한다면 못건너는 것
	                if(cnt == k)
	                    break;
	            }

	            //브레이크로 나온경우(못건너는경우니까 건널사람을 줄여야됨)
	            if(cnt ==k)
	                end = mid-1;
	            
	            //건널수있는경우엔 start 올려서 
	            else start = mid+1;
	        }
	        
	        return start;
	    }
	}

