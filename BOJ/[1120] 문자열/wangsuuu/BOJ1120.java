import java.util.Scanner;
/* 아이디어:
 * A가 B위를 돌아다닐 때, 차이가 최소인 위치를 찾는다면,
 * 그 위치에서의 차이가 바로 답이 됨.
 */

public class BOJ1120 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String A = sc.next();
		String B = sc.next();
		//문자열로 변환
		char[] newA = A.toCharArray();
		char[] newB = B.toCharArray();
		
		int min = 51;
		
		int iter_num = newB.length-newA.length+1; //넓게 본 반복 횟수
		int i = 0;//(넓게) 몇 번째 반복인지
		int j=0;//newB의 인덱스
		
		while(iter_num>0) {
			j=i; //newB의 인덱스를 다시 세팅
			int cnt=0;
			for(int k=0; k<newA.length; k++) {
				if(newA[k]!=newB[j])
					cnt++;
				j++;
			}
			if(cnt<min)
				min=cnt;
			i++;
			iter_num--;
		}
		
		System.out.println(min);
	}
	
}
