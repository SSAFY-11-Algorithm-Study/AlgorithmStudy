import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2599_Sequence {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //온도를 측정한 전체 날짜의 수 
		int K = Integer.parseInt(st.nextToken()); //연속적인 날짜의 수
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} //입력 완료
		
		int start=0;
		int sum = 0;
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<N-K+1; i++) { //총 N-K+1번만큼 온도의 합이 계산됨
			for(int j=start; j<start+K; j++) { //연속 날짜의 수만큼 덧셈 연산
				sum+=arr[j];
			}
			max = Math.max(max, sum); //max값 업데이트
			start++; //start point 증가
			sum=0; //초기화!
		}
		System.out.println(max);
	}
}
