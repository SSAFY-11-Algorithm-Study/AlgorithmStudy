import java.util.ArrayList;
import java.util.Scanner;

public class BOJ2605_Queuing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> arr = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //학생들의 수
		
		for(int i=1; i<=N; i++) {
			arr.add(i);
		} //학생들의 번호를 순서대로 arr에 저장
		
		for(int i=0; i<N; i++) {
			int n = sc.nextInt(); //학생이 뽑은 번호 (순서대로 뽑음)
			arr.remove(i); //먼저 학생 번호가 들어있는 곳을 지움
			arr.add(i-n, i+1); //학생이 뽑은 번호에 따라 학생을 앞으로 옮김
		}
		
		for(int i : arr) {
			System.out.print(i + " ");
		}

	}

}
