import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//인터넷 참고해서 풀었습니다 (https://settembre.tistory.com/320)

//BFS(최소 경우의 수, 최단 경로 문제이므로) & DP

public class BOJ16928_LadderAndSnake {

	static int N, M;
	static int[] LadderAndSnake; //index 칸에서 값의 칸으로 이동함.
	static int[] minCnt; //해당 인덱스까지 도달하기 위해 굴려야 하는 주사위의 최소 횟수가 저장 (최단 경로가 저장됨)
	static boolean[] visited; //재방문 해서 최단 경로로 가는 경우는 없으므로, 재방문 X
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //사다리의 수
		M = sc.nextInt(); //뱀의 수
		
		minCnt= new int[101];
		LadderAndSnake = new int[101];
		visited = new boolean[101];
		
		for(int i=0; i<N+M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			LadderAndSnake[start] = end;
		} //입력 완료
		
		bfs();
		System.out.println(minCnt[100]);
		
	}

	private static void bfs() {
		
		Queue<Integer> q = new LinkedList<>();
		
		q.add(1);
		visited[1]=true;
		
		while(!q.isEmpty()) {
			
			int cur = q.poll();
			
			if(cur==100) //100에 도달했다면 종료
				return;
			
			for(int i=1; i<7; i++) { //주사위 1~6까지 던짐
				int newCur = cur + i;
				
				if(newCur>100) //100번 칸을 넘으면 이동할 수 없으므로
					continue;
				
				if(!visited[newCur]) { //말은 증가만 하고, minCnt에는 최단 경로가 들어가므로 재방문은 하지 않음.
					
					minCnt[newCur] = minCnt[cur]+1;
					visited[newCur] = true;
					
					int tmp = LadderAndSnake[newCur];
					if(tmp!=0) { //간 곳에 사다리나 뱀이 있다면
						
						if(visited[tmp]) //동일하게, 타고 이동한 곳이 아직 방문 전이어야만 진행 ->이 부분 없으면 틀림
							continue;
						
						q.add(tmp);
						visited[tmp] = true;
						minCnt[tmp] = minCnt[newCur]; //도착한 칸이 사다리/뱀이라면 (도착 + 이동) 이 횟수 한번.
					}
					else { //간 곳에 사다리나 뱀이 없다면
						q.add(newCur);
					}
				}
			}
		}
	}
	
}
