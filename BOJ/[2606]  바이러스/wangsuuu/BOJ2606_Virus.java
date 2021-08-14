import java.util.Scanner;

public class BOJ2606_Virus {
	
	static int[][] computers;
	static boolean[] visited;
	static int cnt, N;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //총 컴퓨터의 갯수
		int pairNum = sc.nextInt(); //총 쌍의 갯수
		
		//컴퓨터 번호는 1번부터 시작. 
		//컴퓨터 번호 / 컴퓨터와 직접 연결된 컴퓨터 번호이면 1, 아니면 0
		computers = new int[N+1][N+1];
		
		//각 번호를 방문했는지의 여부
		visited = new boolean[N+1];
		
		//직접 연결 구성
		for(int i=0; i<pairNum; i++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			
			//!! 1과 2가 직접 연결이면, 1번 칸에도, 2번 칸에도 모두 입력해줘야 함. !!
			computers[num1][num2]=1;
			computers[num2][num1]=1;
		}
		
		visited[1]=true;
		dfs(1);
		System.out.println(cnt);
	}
	
	/*
	 * 함수 flow 순서가 잘못됨. 현재 노드 처리 후 또 N번의 반복문을 통해서 재귀호출을 하니
	 * 끝이 나지 않아 stack overFlow 발생함.
	 * ex) 끝에 도달 해서 더 갈 곳이 없는데도 N번의 재귀호출을 함..
	public static void dfs(int start) {
		//현재 노드를 아직 방문하지 않았다면, cnt 증가
		if(!visited[start]) {
			cnt++;
			//방문 처리
			visited[start]=true;
		}
		
		for(int i=1; i<=N; i++) {
			//다음 직접 연결된 노드 dfs처리
			if(computers[start][i]==1)
				dfs(i);
		}
	}
	
	*/
	
	public static void dfs(int start) {
		for(int i=1; i<=N; i++) {
			//직접 연결되어 있고, 아직 방문하지 않았다면
			if(computers[start][i]==1 && !visited[i]) {
				//방문 한 다음 check
				cnt++; visited[i]=true;
				//그 노드에 대한 dfs 수행
				dfs(i);
			}
		}
	}
	
