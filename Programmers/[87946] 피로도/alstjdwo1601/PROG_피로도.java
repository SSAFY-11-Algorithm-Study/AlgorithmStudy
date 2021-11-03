package week15;

import java.util.*;
class Solution1 {
    static int answer = 0;
    static int size= 0;
    static int [] selected;
    static boolean [] visited;
    public int solution(int k, int[][] dungeons) {
        size = dungeons.length; // 던전의 갯수를 의미
        
        //순열을 위한 배열들 - 던전 최대갯수가 8개라 가능할거라고 생각했음
        selected= new int[size];
        visited= new boolean[size];
        
        dfs(0, selected, dungeons, k); //dfs말고 그다음 함수가 이 인자들을 다써서 미리 넘김
        
        
        return answer;
    }
    
    //던전 가는 순서를 순열로(순서 상관o)
    public static void dfs(int depth, int [] selected, int [][] dungeons , int k){
        if(depth == size){
            //System.out.println(Arrays.toString(selected));
            
            //selected는 던전 가는 순서 담겨있고 이 배열가지고 던전 입장
            answer = Math.max(answer ,gotoDungeon(selected , dungeons , k));
            
            return;
        }
        
        for(int i = 0 ; i < size; i ++){
            if(!visited[i]){
                visited[i] = true;
                selected[depth] = i;
                dfs(depth+1, selected, dungeons, k);
                visited[i] = false;
            }
        }
    }
    
    public static int gotoDungeon(int [] selected, int [][] dungeons , int k){
        int dungeonCnt = 0;
        
        int idx = 0;
        for(int i = 0 ; i < size; i ++){
            for(int j = 0 ; j < size; j++ ){
                //현재 가야하는 던전순서이고, 현재 피로도가 필요피로도보다 같거나 크면 입장
                if(selected[j] == idx && k >= dungeons[j][0]){
                    dungeonCnt ++; //입장한 던전 갯수 증가
                    k -= dungeons[j][1]; //피로도 감소
                    idx++; //던전 순서 다음것으로 넘김
                    break;
                }
            }
        }
        
        
        
        return dungeonCnt;
    }
    
    
    
    
}