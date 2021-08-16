import java.util.Scanner;

public class BOJ2578_Bingo {
	
	static int[][] bingo;
	static int[][] chk; //빙고판 숫자 체크 배열
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		bingo = new int[5][5];
		chk = new int[5][5];
		int[] mc = new int[25];
		int cnt=0, answer=0; //cnt: 선 수  answer: 사회자가 부른 숫자의 갯수
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				bingo[i][j]=sc.nextInt();
			}
		}
		for(int i=0; i<25; i++) //사회자가 부르는 숫자 
			mc[i] = sc.nextInt();
		//입력 완료
		
		for(int i=0; i<25; i++) {
			
			for(int j=0; j<5; j++) {
				for(int k=0; k<5; k++) {
					if(bingo[j][k]==mc[i]) { //부른 수를 체크표시.
						chk[j][k]=1;
						answer++;
					}
				}
			}
			
			if(i>=11) {//최소 12개 이상부터 빙고 가능하므로, 12개 이후에는 사회자가 수를 부를 때마다 빙고 체크
				int tmp3=0, tmp4=0;
				for(int a=0; a<5; a++) {
					int tmp1=0, tmp2=0; //초기화 필요
					for(int b=0; b<5; b++) {
						tmp1 += chk[a][b];
						tmp2 += chk[b][a];
						if(b==4 && tmp1==5) { //가로 한 줄이 빙고일 경우
							cnt++;
						}
						if(b==4 && tmp2==5) {//세로 한 줄이 빙고인 경우
							cnt++;
						}
						if(a==b) { //대각선 1
							tmp3+=chk[a][b];
						}
						if(a+b==4) {//대각선 2
							tmp4+=chk[a][b];
						}
					}
				}
				if(tmp3==5) cnt++; //대각선 1이 빙고인 경우
				if(tmp4==5) cnt++; //대각선 2가 빙고인 경우
				
				if(cnt>=3)
					break;
			}
			cnt=0; //빙고 체크 한번 끝날 때마다 초기화 해줘야 함.
		}
		System.out.println(answer);
	}
}
