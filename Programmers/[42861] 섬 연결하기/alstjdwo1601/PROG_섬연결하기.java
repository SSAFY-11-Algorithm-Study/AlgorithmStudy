package week20;

import java.util.*;
class Solution {
    static class Edge implements Comparable<Edge>{
		int start,end,weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight; //간선이 전부 양수
		}
    }
    
    static int [] parents;
    private static void make(int V){
        parents = new int[V];
        //모든 원소를 자신이 대표자가 되도록
        for(int i = 0 ; i < V ; i ++)
            parents[i] = i;
    }
    
    private static int find(int a){
        if(a == parents[a]) return a; // 자신이 대표자
        
        return parents[a] = find(parents[a]);
    }
    
    private static boolean union(int a , int b){
        int aRoot = find(a);
        int bRoot = find(b);
        
        //같은 집합이면 합칠필요 x
        if(aRoot == bRoot) return false;
        
        //다르면 합침(b 부모를 a부모에 연결)
        parents[bRoot] = aRoot;
        return true;
    }
    
    static Edge [] edgeList;
    static int V;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        //간선 리스트
        edgeList = new Edge[costs.length];
        
        
        for(int i = 0 ; i < costs.length ; i ++){
            for(int j = 0 ; j < costs[i].length ; j++){
                int start = costs[i][0];
                int end = costs[i][1];
                int weight = costs[i][2];
                
                edgeList[i] = new Edge(start,end,weight);
            }
        }
        
        Arrays.sort(edgeList);
        
        V = n;
        make(V);
        
        int cnt = 0;
        for(Edge e : edgeList){
            //합쳐지는거는 계속 weight 더하면서 합침
            if(union(e.start,e.end)){
                answer += e.weight;
                if(++cnt == n-1) break; //mst 완성
            }
        }
        
        return answer;
    }
}