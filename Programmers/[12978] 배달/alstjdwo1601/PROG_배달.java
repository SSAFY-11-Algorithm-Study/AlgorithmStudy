package week19;

import java.util.*;

//프로그래머스에서는 구현간단한 플로이드가 좋은거같음
class Solution {
    static final int INF = 500001;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        //인접배열만들기 
        int [][] map = new int [N+1][N+1];
     
        //인접배열 초기화
        for(int i = 1 ; i <= N ; i++){
            for(int j = 1; j <= N ; j++){
                map[i][j] = INF;
                
                if(i==j) map[i][j] = 0;
            }
        }
        
        //간선정보 저장하기
        for(int i = 0 ; i < road.length;  i ++){
            int start = road[i][0];
            int end = road[i][1];
            int weight = road[i][2];
            
            //이 조건 빠뜨리지 말자 중복된 값이 들어올수도있음
            if(map[start][end] > weight){
                map[start][end] = weight;
                map[end][start] = weight;
            }
        }
        
        
        //플로이드 와샬 알고리즘
        for (int k = 1; k <= N; k++) {                           
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
        
        
        //K 이하인 거리배열값이 정답
        for (int i = 1; i <= N; i++) {
            if(map[1][i] <=K) 
                answer ++;
        }

        return answer;
    }
}