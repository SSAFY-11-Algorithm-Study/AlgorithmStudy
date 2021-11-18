import java.util.ArrayList;
import java.util.Scanner;

//에라토스테네스의 체 활용

public class BOJ9020_GBPartition {
	
	static boolean[] list;
	static ArrayList<Integer> result;
	static ArrayList<int[]> gb;
	static int[] combRes = new int[2];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //테케 수
		int[] input = new int[N];
		int max=-1;
		for(int i=0; i<N; i++) {
			input[i] = sc.nextInt();
			max = Math.max(max, input[i]); //입력값 중 최댓값 찾기 -> 소수 구해서 재활용하기 위함
		}//입력 완료
		
		findPrimeNum(max); //max 이하의 소수 모두 찾기
		
		
		for(int i=0; i<N; i++) {
			gb = new ArrayList<>(); //초기화
			int[] res = new int[2];
			makeGB(input[i]); //소수 두개를 뽑아 골드바흐 파티션 만들기
			//골드바흐 파티션이 여러개인 경우 소수의 차이가 최소인 것 구하기
			int min = 99999;
			for(int j=0; j<gb.size(); j++) {
				int diff=Math.abs(gb.get(j)[0] - gb.get(j)[1]);
				if(diff < min) {
					min = diff;
					res[0] = gb.get(j)[0];
					res[1] = gb.get(j)[1];
				}
			}
			System.out.println(Math.min(res[0], res[1]) + " " + Math.max(res[0], res[1]));
		}
		
	}
	private static void findPrimeNum(int max) { //n이하의 소수를 찾아 list에 저장
		list = new boolean[max+1]; //true면 소수, false면 소수가 아님
		result = new ArrayList<>(); //결과를 담을 리스트
		
		for(int i=2; i<=max; i++) { //초기화
			list[i] = true;
		}
		
		for(int i=2; i<=Math.sqrt(max); i++) {
			for(int j=i*i; j<=max; j+=i) {
				if(list[i]==true) {
					list[j]=false;
				}
			}
		}
		
		for(int i=0; i<=max; i++) {
			if(list[i]==true)
				result.add(i);
		}
	}

	private static void makeGB(int n) {
		
		for(int i=0; i<result.size(); i++) {
			if(result.get(i)<=n && result.contains(n-result.get(i))) {
				gb.add(new int[] {result.get(i), n-result.get(i)});
			}
		}
	}
}
