package week2;

import java.util.*;

class Solution {
    public static int answer = 0;
    public static boolean [] visited;
    public int solution(int[] numbers, int target) {
        
        visited = new boolean[numbers.length];
        Arrays.fill(visited,false);
        
        //dfs 시작
        //depth+1 이 처음sum에 더해져야 해서 -1로 시작
        dfs( numbers, -1, 0, target);
        
        return answer;
    }
    
    public void dfs(int[] numbers , int depth, int sum , int target){
        
        if(depth == numbers.length-1){ //깊이 끝까지 갔는데
            if(sum == target) {       //타겟이랑 sum이 같으면
                answer++; // 정답인 경우만 카운팅 
                return;
            }
            
        }
        
        //아직 깊이 끝아니면 재귀인데 두가지 경우 다돌리고 합산
        else if(depth < numbers.length-1){
            dfs(numbers  , depth+1 , sum + numbers[depth+1] , target ); 
            dfs(numbers  , depth+1 , sum - numbers[depth+1] , target );
        }
    }
            
}