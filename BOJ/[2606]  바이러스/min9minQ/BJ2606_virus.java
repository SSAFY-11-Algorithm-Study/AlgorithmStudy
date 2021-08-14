package time3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ2606_virus {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int num = Integer.parseInt(br.readLine());
		int link = Integer.parseInt(br.readLine());
		int count = 0;
		
		ArrayList<Integer>[] virus = new ArrayList[num+1];//ArrayList로 이루어진 배열 선언 (컴퓨터 인덱스 1부터로 맞추기 위해서 +1)
		boolean[] isSelected = new boolean[num+1];// 연결되어 있으면 true로 표시 하기 위한 배열
		for(int i = 0; i < num+1; i++) {
			virus[i] = new ArrayList<>();//Arraylist 배열 각각 초기화
		}
		
		for(int i = 0; i < link; i ++) {
			st = new StringTokenizer(br.readLine());
			int num1= Integer.parseInt(st.nextToken());
			int num2= Integer.parseInt(st.nextToken());
			virus[num1].add(num2);//num1에 num2연결 표시
			virus[num2].add(num1);//num2에 num1 연결 표시
		}
		
		search(1,isSelected,virus);// DFS 형식의 search함수 호출
		for(int i = 0; i < num+1; i++) {
			if(isSelected[i]==true) {//true 갯수 카운팅.
				count++;
			}
		}
		
		System.out.println(count-1);//본인 빼기 위한 -1
		
		
		/*이 코드는 연결 입력 첫줄은 처음 무조건 1이 포함한 (1 2 처럼)컴퓨터를 데이터로 주는 줄 알고 짠 코드... set사용
		HashSet<Integer> set = new HashSet<>();
		set.add(1);
		
		for(int i = 0; i < link; i ++) {
			st = new StringTokenizer(br.readLine());
			int num1= Integer.parseInt(st.nextToken());
			int num2= Integer.parseInt(st.nextToken());
			if(set.contains(num1)) {
				set.add(num2);
				count++;
			}
		}
		System.out.println(--count);
		*/
	}
	private static void search(int num, boolean[] isSelected,ArrayList<Integer>[] virus) {
		isSelected[num] = true;
		for(int v : virus[num]) {
			if(isSelected[v]==false)// 이미 선택되었으면 안가도 됨. 이미 찾으러 떠났음.
				search(v,isSelected,virus);
		}
		
	}
}
