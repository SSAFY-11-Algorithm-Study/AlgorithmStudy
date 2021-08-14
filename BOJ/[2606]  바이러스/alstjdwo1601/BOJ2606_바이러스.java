package week3;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2606_바이러스 {
    static int nodeList[][]; // 인접배열
    static boolean dfsVisited[]; //노드 방문 여부
    static boolean bfsVisited[];
    static int nodeCount;
    static int edgeCount;
    static int cnt ;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        nodeCount = sc.nextInt();
        nodeList = new int[nodeCount + 1][nodeCount + 1];
        dfsVisited = new boolean[nodeCount + 1];
        bfsVisited = new boolean[nodeCount + 1];

        edgeCount = sc.nextInt();
        cnt = 0;
        

        //맵 세팅 (인덱스 1부터 세팅함)
        for (int i = 1; i <= edgeCount; i++) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            nodeList[node1][node2] = nodeList[node2][node1] = 1;
        }
        
        //시작은 1번 컴퓨터부터
        int startNode = 1;
        
        //탐색 시작
        dfs(startNode);
        System.out.println(cnt);
        
    }

    static void dfs(int currentNode) {
        if (dfsVisited[currentNode] == true) 
            return;
        
        dfsVisited[currentNode] = true;
        //System.out.print(currentNode + " ");
        
        for (int j = 1; j <= nodeCount; j++) {
            //연결되고 방문 안된 노드 탐색 (낮은 노드부터 방문됨)
            if (nodeList[currentNode][j] == 1 && !dfsVisited[j]) { 
                dfs(j);
                cnt++;
            }
            
        }
    }
}
