import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BOJ1205_Ranking {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int score = sc.nextInt(); //새 점수
		int P = sc.nextInt(); //리스트에 올라갈 수 있는 정수의 갯수
		Integer[] scores = new Integer[P]; //Collections.reverseOrder 쓰려면 Integer형으로 해야 함
		
		for(int i=0; i<P; i++) { //안 채워지는 값은 0으로 디폴트 초기화가 되는게 아니라 null로 들어가서 해줘야 함
			scores[i] = 0;
		}
		for(int i=0; i<N; i++) {
			scores[i] = sc.nextInt();
		}
		
		Arrays.sort(scores, Collections.reverseOrder()); //내림차순으로 정렬
		
		if(scores[0]==0) { //가장 높은 등수가 0이라면 -> score가 어떤 수라도 1등이 됨
			System.out.println(1);
			return;
		}
		
		if(N==P && scores[P-1] >= score) { //애초에 리스트가 꽉 차 있는데, score가 가장 작은 점수 이하라면 올라갈 수 없음.
			//N==P 조건이 빠지면 안됨!!! 주의할 것
			System.out.println(-1);
			return;
		}
		
		int answer = 1;
		for(int i=0; i<N; i++) {
			if(scores[i] <= score) {
				break;
			} else if(scores[i] > score) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}

}
