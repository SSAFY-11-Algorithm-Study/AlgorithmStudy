import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//가로로 한 번도 자르지 않거나 세로로 한번도 자르지 않는 경우 IndexOutOfBoundException 발생


public class BOJ2628_CutPaper {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int W = sc.nextInt(); //가로
		int H = sc.nextInt(); //세로
		
		int N = sc.nextInt(); //자르는 횟수
		ArrayList<Integer> horizontal = new ArrayList<>();
		ArrayList<Integer> vertical = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			int n = sc.nextInt();
			if(n==0) { //가로로 자름
				horizontal.add(sc.nextInt());
			} else { // 세로로 자름
				vertical.add(sc.nextInt());
			}
		} //입력 완료
		
		//크기순 정렬
		Collections.sort(horizontal);
		Collections.sort(vertical);
		
		//각 cut에 대해 생기는 변의 길이들 저장
		int[] h = new int[horizontal.size()+1];
		int[] v = new int[vertical.size()+1];
		//확인 완료
		
		for(int i=0; i<h.length; i++) {
			
			//가로로는 한번도 자르지 않는 경우
			if(horizontal.size()==0) {
				h[i] = H;
				break;
			}
			
			if(i==0) { //맨 처음일 경우
				h[i] = horizontal.get(0);
			} else if(i==h.length-1) { //맨 끝일 경우
				h[i] = H-horizontal.get(horizontal.size()-1);
			} else {
				h[i] = horizontal.get(i)-horizontal.get(i-1);
			}
		}
		
		for(int i=0; i<v.length; i++) {
			
			//세로로는 한번도 자르지 않는 경우
			if(vertical.size()==0) {
				v[i]=W;
				break;
			}
			
			if(i==0) {
				v[i]=vertical.get(0);
			} else if(i==v.length-1) {
				v[i] = W-vertical.get(vertical.size()-1);
			} else {
				v[i] = vertical.get(i)-vertical.get(i-1);
			}
		}
		
		
		int max = Integer.MIN_VALUE;
		
		//자른 결과 생긴 변의 길이들을 가로*세로 모든 경우 계산하여 max값 업데이트
		for(int i=0; i<h.length; i++) {
			for(int j=0; j<v.length; j++) {
				int area = h[i]*v[j];
				max = Math.max(max, area);
			}
		}
		System.out.println(max);
	}
}
