package week21;

import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int size = citations.length;
        int cnt1 = 0;
        
        Integer[] arr = new Integer[size];
        
        for(int i = 0; i < size; i++)
            arr[i] = citations[i];
        
        Arrays.sort(arr, Collections.reverseOrder());
        
        
        for(int j = arr[0] ; j >= 0 ; j --){
            cnt1=0;
            for(int i = 0 ; i< size ; i++){
                if(arr[i] >= j)
                    cnt1++;
            }
            if(j <= cnt1){
                answer = j;
                break;
            } 
        }
        
        
        return answer;
    }
}
