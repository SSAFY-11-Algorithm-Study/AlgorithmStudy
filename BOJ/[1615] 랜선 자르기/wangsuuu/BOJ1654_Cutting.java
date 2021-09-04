import java.util.Scanner;

//시간 초과...
//이분탐색으로 풀어야 할 거 같은데 어떻게 풀어야 할지 잘 모르겠네요ㅠ

public class BOJ1654_Cutting {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt(); //영식이가 이미 가지고 있는 랜선의 갯수
		int N = sc.nextInt(); //필요한 랜선의 갯수
		
		int[] lines = new int[K];
		int arrMax=Integer.MIN_VALUE; //배열의 값들 중 최댓값 저장
		
		for(int i=0; i<K; i++) {
			lines[i] = sc.nextInt();
			arrMax = Math.max(arrMax, lines[i]);
		}//입력 완료
		
		
		int max = Integer.MIN_VALUE;
		int answer=1;
		
		while(true) {
			
			int cnt=0;
			
			for(int i=0; i<K; i++) {
				cnt+=(lines[i]/answer); //나눗셈의 몫은 길이로 만들 수 있는 랜선 갯수이므로
				if(cnt>N) { //N을 넘어가면 바로 answer을 1 증가시켜서 새로운 값으로 다시 시도
					break;
				}
			}
			
			if(cnt==N) {
				max = Math.max(max, answer);
			}
			
			if(answer>arrMax)
				break;
			answer++;
		}
		System.out.println(max);
	}

}
