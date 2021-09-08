import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//순열 사용하니 시간초과 -> 그리디 사용


public class BOJ2012_Ranking {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //사람의 수
		int [] ranks = new int[N];
		
		for(int i=0; i<N; i++) {
			int n = Integer.parseInt(br.readLine());
			ranks[i] = n;
		}//입력 완료
		
		//순위가 높은 순서대로 정렬
		Arrays.sort(ranks);
		
		//최악의 경우가 int범위를 넘어가므로, long형으로 지정해야 함에 주의!
		// https://www.acmicpc.net/board/view/19589
		long unhappy=0;
		for(int i=0; i<N; i++) {
			//정렬한 상태에서 1, 2, 3, 4 ...차례대로 등수 부여해주고, 그 상태에서의 불만도 합 계산
			unhappy += Math.abs(ranks[i] - (i+1));
		}
		System.out.println(unhappy);
	}

}
