package time19;

public class PM_배달 {
	
	private static int N = 5;
	private static int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
	private static int K = 3;

	public static void main(String[] args) {
		int answer = 0;
		int[][] distance = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == j) 
					distance[i][j] = 0;
				else
					distance[i][j] = 50000000;
			}
		}
		// 정점과 정점 연결 하는 맵
		for (int i = 0; i < road.length; i++) { 
			// 새 다리가 기존보다 크면 그냥 넘기기 작으면 갱신.
	        if(distance[road[i][0] - 1][road[i][1] - 1] < road[i][2]) 
	        	continue;   
	        distance[road[i][0] - 1][road[i][1] - 1] = road[i][2];                 
	        distance[road[i][1] - 1][road[i][0] - 1] = road[i][2];
	    }
		
		for(int i=0; i < N; i++) {
            for(int j=0; j < N; j++) {
                for(int k=0; k< N; k++) {
                    if(distance[j][k] > distance[j][i] + distance[i][k])
                    	distance[j][k] = distance[j][i] + distance[i][k];
                }
            }
        }
		
		for (int i = 0; i < N; i++) {
            if (distance[0][i] <= K) 
                answer++;
        }
		System.out.println(answer);
	}

}
