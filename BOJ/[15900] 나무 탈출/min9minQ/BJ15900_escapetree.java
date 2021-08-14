package time3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
//virus 코드 이용(dfs 사용)
public class BJ15900_escapetree {
	
	private static int result = 0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int num = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] trees = new ArrayList[num+1]; // 루트 노드를 1로 하기 위해 +1
		boolean[] isSelected = new boolean[num+1]; 
		for(int i = 0; i < num+1; i++) {
			trees[i] = new ArrayList<>(); // ArrayList 배열 초기화
		}
		
		for(int i = 0; i < num-1; i ++) {// 연결되어 있는 값 서로 저장.
			st = new StringTokenizer(br.readLine());
			int num1= Integer.parseInt(st.nextToken());
			int num2= Integer.parseInt(st.nextToken());
			trees[num1].add(num2);
			trees[num2].add(num1);
		}
		int cnt = 0;
		search(1,0,isSelected,trees);
		if(result%2 == 1) System.out.println("Yes");
		else if(result%2 == 0) System.out.println("No");
		
	
	}
	private static void search(int num,int cnt, boolean[] isSelected,ArrayList<Integer>[] trees) { //DFS
		isSelected[num] = true;
		if (trees[num].size()==1) result+=cnt; // 크기가 1이면 하나만 연결되어 있는 것이므로 자식이 없음. 따라서 여기까지 오기 위해 건너온 길(cnt)를 결과값에 더해줌
		
		for(int v : trees[num]) {
			if(isSelected[v]==false) {//true면 이미 탐색 들어갔음.
				search(v,cnt+1,isSelected,trees);
			}
		}
		
		
	}
}
