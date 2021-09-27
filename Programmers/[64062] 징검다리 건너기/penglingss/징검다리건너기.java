// 이진 탐색을 하며 최대 몇명이 건널 수 있는지 검사한다.

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int min = 1;
        int max = 200000000;
        
        while(min <= max) {
            int mid = (min + max) / 2;
            int cnt = 0;
            for(int i = 0; i < stones.length; i++) {
                if(stones[i] - mid <= 0) cnt++;
                else cnt = 0;
                
                if(cnt == k) break; // 더 이상 밟을 수 없는 돌의 갯수가 k개라면 
            }
            if(cnt == k) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
            
        return min;
    }
}
