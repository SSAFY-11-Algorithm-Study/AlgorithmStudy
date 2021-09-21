package week9;

import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2667_단지번호붙이기 {
    static int map[][]; // 인접배열
    static boolean visited[][];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int apartNum;
    static int size;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        size = sc.nextInt();
        map = new int [size][size];
        visited = new boolean [size][size];
        
        //맵 정보 받기
        for(int i=0; i<size; i++){
            String input = sc.next();
            for(int j=0; j<size ; j++){
                map[i][j] = input.charAt(j)-'0';
                visited[i][j] = false;
            }
        }
        
        //각 단지 내에 있는 아파트 갯수를 담는 우선순위큐
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        
        int cnt = 0;
        for(int i = 0 ; i < size ; i ++) {
        	for(int j = 0 ; j < size ; j++) {
        		if(map[i][j] == 1 && !visited[i][j]) {
        			apartNum = 0;
        			cnt++; //dfs 부를때마다 단지 수가 증가함
        			dfs(i,j);
        			pq.add(apartNum);
        		}
        	}
        }
        
        System.out.println(cnt); // 총 단지 갯수
        while(!pq.isEmpty())
        	System.out.println(pq.poll()); //각 단지 내에 아파트 갯수
    }

    static void dfs(int x, int y) {
    	//단지 내부의 아파트 갯수 증가시킴
    	apartNum++;
    	visited[x][y] = true;
    	
    	for(int i = 0 ; i < 4 ; i ++) {
    		int nx = x + dx[i];
    		int ny = y + dy[i];
    		
    		if(nx >= 0 && ny >= 0 && nx < size && ny < size) {
    			if(map[nx][ny]==1 && !visited[nx][ny])
    				dfs(nx,ny);
    		}
    	}
    }
}

