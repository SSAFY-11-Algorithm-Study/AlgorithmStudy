import java.util.*;
class Solution {
    
    static boolean[][] tree;
    static boolean[] visited;
    static int towerCnt;
    static int n1;
    public int solution(int n, int[][] wires) {
        int answer = 9999;
        n1 = n;
        for(int i=0; i<n-1; i++){ //차례대로 하나씩 전선을 끊어봄
            tree = new boolean[n+1][n+1]; //송전탑 번호는 1번부터 시작
            for(int j=0; j<n-1; j++){
                if(i==j) continue;
                int s = wires[j][0];
                int e = wires[j][1];
                tree[s][e]=true;
                tree[e][s]=true;
            }//트리 생성 완료
            //나눠진 두 그룹의 송전탑 갯수 차이 구하기
            visited= new boolean[n+1];
            int[] towers = new int[2];
            int idx=0;
            for(int j=1; j<=n; j++){
                if(!visited[j]){
                    towerCnt=0;
                    dfs(j);
                    towers[idx++]=towerCnt;
                }
            }
            int diff = Math.abs(towers[0] - towers[1]);
            answer = Math.min(answer, diff);
        }
        return answer;
    }
    
    public void dfs(int x){
        visited[x]=true;
        towerCnt++;
        for(int i=1; i<=n1; i++){
            if(!visited[i] && tree[x][i]){
                dfs(i);
            }
        }
    }
}
