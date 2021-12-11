import java.util.*;

//간선 정보가 주어짐 - 크루스칼 사용

class Solution {
    
    static int parents[];
    static int N;
    public int solution(int n, int[][] costs) {
        N = n;
        parents = new int[n];
        
        //1. 간선 sort
        Arrays.sort(costs, new Comparator<int[]>(){ //2차원 배열 정렬 시, <int[]>(정렬할 대상이 배열 안에 있는 배열등이니까)
            public int compare(int[] o1, int[] o2){ //비용을 기준으로 오름차순 정렬
                return o1[2] - o2[2];
            }
        });
        
        make();
        
        int idx=0, answer=0, cnt=0;
        while(true){
            if(cnt==n-1) break; //MST 완성되었다면 중지
            
            //2. 가장 비용이 적은 간선 순서대로 선택
            int[] cur = costs[idx++];
            int a = cur[0]; int b = cur[1]; int cost = cur[2];
            if(union(a,b)){
                //MST에 더하기
                answer += cost;
                cnt++;
            }
        }    
        return answer;
    }
    
    public void make(){//단위집합 만들기
        for(int i=0; i<N; i++){
            parents[i] = -1;
        }
    }
    
    public int find(int a){//a의 부모 찾아주기
        if(parents[a] == -1)
            return a;
        else
            return parents[a] = find(parents[a]); //a의 부모 올바르게 설정 후 리턴
    }
    
    public boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot==bRoot) //이미 같은 무리이므로 union 불가
            return false;
        parents[bRoot] = aRoot; //union후 true 리턴 (union해주는 직업 빼먹어서 처음에 틀림)
        return true;
    }
}
