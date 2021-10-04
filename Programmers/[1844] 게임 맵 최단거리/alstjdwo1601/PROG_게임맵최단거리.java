package week11;

import java.util.*;
class Solution {
    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,-1,0,1};
    static boolean [][] visited;
    static int cnt = 0;
    static class Node{
        int x ;
        int y ;
        int time;
        
        public Node(int x , int y , int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    
    public int solution(int[][] maps) {
        int row = maps.length ; //세로
        int col = maps[0].length; //가로
        
        visited = new boolean[row][col];
        visited[0][0] = true;
       
        
        bfs(maps , row , col);
        return cnt;
    }
    
    public static void bfs(int [][] map , int row , int col){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0,1));
        
        while(!q.isEmpty()){
            Node node = q.poll();
            visited[node.x][node.y] = true;
            cnt = node.time;
            
            for(int i = 0 ; i < 4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                
                //배열안쪽이면서 빈칸이면 
                if(nx>=0 && ny>=0 && nx<row && ny<col){
                    if(map[nx][ny] == 1 && !visited[nx][ny]){
                        visited[nx][ny] = true;
                        q.add(new Node(nx,ny,cnt+1));
                    }
                }
            }
            
            //마지막지점 도착한경우 그때의 cnt ++하고 브레이크
            if(visited[row-1][col-1]){
                cnt++;
                break;
            }
            
        }
        //while문을 break로 나오지않았다면 방문하지못하는 경우임
        if(!visited[row-1][col-1])
            cnt = -1;
    }
}