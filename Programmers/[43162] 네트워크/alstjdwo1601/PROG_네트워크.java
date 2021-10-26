package week14;

import java.util.*;
class Solution {
    public static boolean [] visited;
    public static int answer;
    public int solution(int n, int[][] computers) {
        
        visited = new boolean[n];
        
        for(int i = 0 ; i < computers.length ; i ++){
            if(!visited[i]){
                //dfs 호출할때마다 네트워크 하나씩 추가됨
                answer++;
                dfs(computers, i);
            }
        }
            
        return answer;
    }
    
    
    public static void dfs(int [][] computers, int node){
        visited[node] = true;
        
        for(int i = 0 ; i < computers.length; i ++){
            if(computers[node][i]==1 && !visited[i]){
                dfs(computers, i);
            }
        }     
    }
}
