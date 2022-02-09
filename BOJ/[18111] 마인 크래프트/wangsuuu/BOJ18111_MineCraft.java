import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18111_MineCraft {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //세로
		int M = Integer.parseInt(st.nextToken()); //가로
		int B = Integer.parseInt(st.nextToken()); //인벤토리 갯수
		
		int max = -1;
		int min = Integer.MAX_VALUE;
		
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
				min = Math.min(min, map[i][j]);
			}
		}//입력 완료
		
		int answer=Integer.MAX_VALUE;
		int opt_h = -1;
		
		for(int h=min; h<=max; h++) {
			int inventory = B;
			int time=0;
			//기억할 것 : (0,0)부터 끝가지 일단 다 구해보고 결과적으로 inventory에 남아있는 수가 무엇인지를 보면 됨
			
			for(int x=0; x<N; x++) {
				for(int y=0; y<M; y++) {
					int diff = map[x][y] - h;
					if(diff>0) { //블록 제거
						inventory += diff;
						time += 2 * diff;
					} else if(diff<0) { //블록 쌓기
						int abs = Math.abs(diff);
						inventory -= abs;
						time += abs;
					}
				}
			}
			
			if(inventory>=0 && time<=answer) { //모두 수행 결과, 수행이 가능하고 최소 시간이면
				answer = time;
				if(opt_h<h) //최소 시간이 같을 경우 최고 높이로 업데이트
					opt_h = h;
			}
		}
		System.out.println(answer + " " + opt_h);
	}
}
