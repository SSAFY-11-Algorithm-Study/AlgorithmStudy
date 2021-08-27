import java.util.ArrayList;
import java.util.Scanner;

//만들 수 있는 수가 최소 4개 이상이려면, 두번째로 선택하는 수는N/2이상 N이하의 수여야 한다.

public class BOJ2635_Counting {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> copyList = new ArrayList<>();
		int max=Integer.MIN_VALUE;
		Scanner sc = new Scanner(System.in);
		int First = sc.nextInt();
		
		for(int i=First/2; i<=First; i++) { //이 범위 안에서 완탐
			//초기화
			int cnt=2;
			int result=0;
			int Second = i;
			list.clear(); 
			list.add(First); list.add(Second);
			int idx1=0, idx2=1;
			
			while(true) {
				result=list.get(idx1)-list.get(idx2);
				if(result>=0) {
					list.add(result);
					idx1++; idx2++; cnt++;
				} else { //더 이상 수를 만들 수 없다면
					if(cnt>max) { //cnt 최댓값 갱신 및 그에 따른 배열도 갱신
						max=cnt;
						copyList.clear();
						for(int j=0; j<list.size(); j++) {
							copyList.add(list.get(j));
						}//for
					}//if
					break; //다음 경우로 넘어가기
				}//else
			}
		}
		System.out.println(max);
		for( int n : copyList) {
			System.out.print(n + " ");
		}
	}

}
