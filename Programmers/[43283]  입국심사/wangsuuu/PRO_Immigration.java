import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long min = 1;
        //long max = Long.MAX_VALUE; -> 이걸로 하면 테케 6, 9번이 틀렸습니다 뜸..
        
        Arrays.sort(times);
        long max = n * (long)times[times.length - 1]; //최대로 걸릴 수 있는 시간 = 배열의 최댓값 * n
        long mid = 0;
        
        while(min <= max){
            mid = (min+max)/2;
            
            long res = 0;
            for(int i=0; i<times.length; i++){
                res += mid/times[i];
                if(res >= n)
                    break;
            }
            
            if(res >= n){ //수를 줄여야 함 (최솟값을 구해야 하니까 여기 등호 붙임)
                 max = mid - 1;
            } else { // 수를 넓혀야 함
                min = mid + 1;
            }
        }
        return min;
    }
}
