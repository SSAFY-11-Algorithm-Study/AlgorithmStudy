import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//"최소 시간" - BFS

public class BOJ1697_HideAndSeek {
	
	static int bin, sis;
	static boolean[] visited;
	static int answer = 0;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		bin = sc.nextInt(); //수빈의 위치
		sis = sc.nextInt(); //동생의 위치
		visited = new boolean[100001];
		
		if(bin >= sis) {
			System.out.println(bin-sis);
		} else {
			bfs();
			System.out.println(answer);
		}
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {bin, 0}); //처음 위치와, 그 위치까지 도달하기 위한 최소 시간
		visited[bin]=true;
		
		while(!q.isEmpty()) {
			
			int[] cur = q.poll();
			int pos = cur[0];
			int minTime = cur[1];
			
			if(pos==sis) {
				answer = minTime;
				return;
			}
			
			if(pos>0) { //0 위치에서는 뒤로 못 가므로
				if(!visited[pos-1]) {
					q.add(new int[] {pos-1, minTime+1});
					visited[pos-1] = true;
				}
			}
			if(pos+1 <= 100000 && !visited[pos+1]) {
				q.add(new int[] {pos+1, minTime+1});
				visited[pos+1]= true;
			}
			if(2*pos<=100000 && !visited[2*pos]) {
				q.add(new int[] {2*pos, minTime+1});
				visited[2*pos]= true;
			}
		}
	}
}
