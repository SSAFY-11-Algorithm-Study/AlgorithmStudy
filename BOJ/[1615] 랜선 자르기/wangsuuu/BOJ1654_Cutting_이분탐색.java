import java.util.Scanner;

//구하는 랜선의 길이의 범위를 맨 처음에 1부터 max길이까지로 설정한 다음,
//구하는 범위를 반으로 줄여가며 이분탐색

public class BOJ1654_Cutting1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int N = sc.nextInt();
		long[] lines = new long[K];
		long end = 0;
		for(int i=0; i<K; i++) {
			lines[i] = sc.nextLong();
			end = Math.max(end, lines[i]); //랜선의 최댓값 구하기
		}//입력 완료
		
		long start = 1, mid=0;
		
		int cnt=0;
		
		while(start<=end) {
			
			mid = (start+end)/2;
			//초기화
			cnt=0;
			
			for(int i=0; i<K; i++) {
				cnt += lines[i] / mid;
			}
			
			if(cnt>=N) { //N개보다 많이 만들어지면, 길이를 더 늘려도 된다는 의미이므로
				start=mid+1;
			} else { //N개보다 적게 만들어지면, 길이를 줄어야 한다는 의미이므로
				end = mid-1;
			}
		}
		System.out.println(end);
	}
}

/*
중간에 cnt==N을 넣으면, 답이 틀렸습니다가 나옴
-> 특정 값을 구하는 것이 아니라, 가능한 값들 중 최댓값을 구하므로 cnt>=N만 가능

주어진 테케
4 11
802
743
457
539 에 대해서는,
마지막 단게에서 start==end==mid==201이다가 end가 200이 되며 while문 빠져나와서 정답(end) 출력.

*/
