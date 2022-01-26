import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//시간초과나서 블로그 참고..

public class BOJ1946_Newbie {
	
	static class Rank implements Comparable<Rank>{
		int resume;
		int interview;
		
		public Rank(int resume, int interview) {
			super();
			this.resume = resume;
			this.interview = interview;
		}

		public int compareTo(Rank r) { //서류 성적을 기준으로 오름차순 정렬
			return this.resume - r.resume;
		}
	}
	
	static int N;
	static Rank[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new Rank[N];

			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i] = new Rank(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}//입력 완료

			Arrays.sort(arr);
			
			int answer=1; //arr[0]은 서류 1등이니까 무조건 뽑힘.
			int mark = arr[0].interview;
			for(int i=1; i<N; i++) {
				if(arr[i].interview < mark) { //뽑힐 사람이면
					answer++;
					mark = arr[i].interview;
				}
			}
			System.out.println(answer);
		}
	}
}
