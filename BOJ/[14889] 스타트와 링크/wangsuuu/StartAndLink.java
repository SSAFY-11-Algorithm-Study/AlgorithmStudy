import java.util.Scanner;


public class BOJ14889_StartAndLink {
	
	static int N;
	static int[][] ability;
	static int[] startT, linkT;
	static int answer=Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 총 사람의 수
		ability = new int[N][N];
		startT = new int[N/2]; //스타트 팀
		linkT = new int[N/2]; //링크 팀
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				ability[i][j] = sc.nextInt();
			}
		}//입력 완료
		
		comb(0, 0);
		System.out.println(answer);
	}

	private static void comb(int cnt, int start) {
		
		if(cnt==N/2) {
			boolean[] temp = new boolean[N]; //link팀을 구하기 위한 배열
			for(int i=0; i<N/2; i++) {
				temp[startT[i]]=true;
			}
			
			int j=0;
			for(int i=0; i<N; i++) {
				if(!temp[i]) { //false이면, link팀이므로 linkT에 추가
					linkT[j]=i;
					j++;
				}
			}
			//각 팀의 능력치 구하기
			int sAbility = calculate(startT);
			int lAbility = calculate(linkT);
			int diff = Math.abs(sAbility - lAbility);
			answer= Math.min(diff, answer);
			return;
		}
		
		for(int i=start; i<N; i++) {
			startT[cnt] = i;
			comb(cnt+1, i+1);
		}
		
	}

	private static int calculate(int[] team) {
		int sum=0;
		
		for(int i=0; i<team.length-1; i++) {
			for(int j=1; j<team.length; j++) {
				if(i+j<team.length) {
					sum += ability[team[i]][team[i+j]];
					sum += ability[team[i+j]][team[i]]; //거꾸로도 한번 더 더해주기.
				}
			}
		}
		return sum;
	}	
}
