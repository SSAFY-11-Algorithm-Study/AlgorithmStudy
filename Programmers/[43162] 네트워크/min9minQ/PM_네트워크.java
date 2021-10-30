package time14;

public class PM_네트워크 {
	
	static int n = 3;
	static boolean[] visited;
	static int[][] computers = {{1,1,0},{1,1,0},{0,0,1}};

	public static void main(String[] args) {
		visited = new boolean[n];
		int answer = 0;
		for(int i = 0; i < n; i++) {
			if(visited[i] == false) {
				dfs(computers,i);
				answer ++;
			}
		}
		//return answer;
		System.out.println(answer);
	}

	private static void dfs(int[][] computers, int cnt) {
		visited[cnt] = true;
		
		for(int i = 0 ; i < computers.length; i ++) {
			if(computers[cnt][i] == 1 && visited[i] == false) {
				dfs(computers,i);
			}
		}
	}

}
