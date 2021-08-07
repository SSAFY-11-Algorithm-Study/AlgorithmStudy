import java.util.Scanner;
//못 풀었어요. 제가 문제 이해를 잘 못하고 있는 것 같습니다..


//시작칸도 1칸으로 봄
//방문한 칸이 1~4칸일 때(1~3회 움직일 때) -> 이동방법 제약 X
//방문한 칸이 5개 이상일 때(4회 이상 움직일 때) -> 이동방법 모두 이용해야 함. 모두 이용 못하면 아예 움직일 수 없음
//0번/1번만 움직이거나, 1,2,3,4 모두 움직여야 함.

//1, 2, 3, 4 모두 한번씩 이동 후 : x는 6칸 증가, y는 그대로 -> 총 12칸 움직임


public class BOJ1783_Knight {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); //세로 길이
		int M = sc.nextInt(); //가로 길이
		int cnt=1; //시작칸도 1칸으로 보기 때문에
		
		if(N==1 || N*M<6) { // 나이트가 아예 움직이지 못하는 경우
			cnt=1;
		}
		else if(N>=3 && M>=7) { // 1, 2, 3, 4 모두 움직일 수 있음
			int cycle=0;
			cycle=M/7;
			cnt=12*cycle+1;
			
		}else { //나이트가 결과적으로 한 번만 움직일 수 있는 경우
			cnt=4;
		}
		
		System.out.println(cnt);
		
	}

}
