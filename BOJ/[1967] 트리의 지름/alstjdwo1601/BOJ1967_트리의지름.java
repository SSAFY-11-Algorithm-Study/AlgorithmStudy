package week16;

import java.util.ArrayList;
import java.util.Scanner;

//아이디어 참고했음   https://moonsbeen.tistory.com/101
//트리 특성상 각 정점에서 가장 먼곳으로의 경로는 다 겹치게 되어있으므로
//첫 dfs로 가장먼 점을 고르고 , 거기서 다시 dfs로 가장 먼 점을 고르면 그게 최장거리
public class BOJ1967_트리의지름 {
	
	static class Node {
        int next;
        int value;

        public Node(int next, int value) {
            this.next = next;
            this.value = value;
        }
    }
	
	static int N,node;
	static int max= 0;
	static ArrayList<Node>[] nodeList;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		nodeList = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) 
            nodeList[i] = new ArrayList<>();
        
		for(int i = 0 ;  i < N-1 ; i ++) {
			int parent = sc.nextInt();
			int child = sc.nextInt();
			int value = sc.nextInt();
			nodeList[parent].add(new Node(child,value));
			nodeList[child].add(new Node(parent,value));
		}
		
		//임의의 노드(1)에서 부터 가장 먼 노드를 찾는다. 
		//이때 찾은 노드를 node에 저장한다.
        visited = new boolean[N + 1];
        dfs(1, 0);
        
        //node에서 부터 가장 먼 노트까지의 거리를 구한다.
        visited = new boolean[N + 1];
        dfs(node, 0);
        
        System.out.println(max);
		
	}
	
	public static void dfs(int cur  , int cost) {
		visited[cur] = true;
		//System.out.println("무야호");
		//누적값이 max넘어가면 갱신하고 그때의 위치 저장
        if(cost > max) {
        	System.out.println("무야호");
            max = cost;
            node = cur;
        }
        
        
        for(int i = 0; i < nodeList[cur].size(); i++) {
            Node node = nodeList[cur].get(i);
            
            //cost를 계속 누적
            if(visited[node.next] == false) {
                dfs(node.next, node.value + cost);
                visited[node.next] = true;
            }
        }
        
    }

}
