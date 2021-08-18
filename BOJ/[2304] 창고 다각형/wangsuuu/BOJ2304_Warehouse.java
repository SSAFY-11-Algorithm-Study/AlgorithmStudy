import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//풀다가 계속 틀려서 다른 풀이 살짝 참고해서 품

// -> 방향으로 꼭대기를 찾으러 갈 때까지, current Top point가 계속 바뀐다는게 포인트.
// 꼭대기를 찾아 그 면적까지 계산한 뒤, <- 방향으로 면적 계산

public class BOJ2304_Warehouse {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); //기둥의 갯수
		int max_h=Integer.MIN_VALUE; //최대 기둥 높이(꼭대기)
		int max_l=0; //max_h의 위치
		ArrayList<Column> cols = new ArrayList<>();
		int area=0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int loc = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			cols.add(new Column(loc, height));
		} //입력 완료
		
		Collections.sort(cols); //위치 순으로 정렬
		
		//맨 처음을 꼭대기로 설정
		int currentTop=cols.get(0).H;
		int currentTopLoc = cols.get(0).L;
		
		// -> 방향으로 꼭대기를 구하며, 면적 계산
		for(int i=0; i<cols.size(); i++) {
			Column c = cols.get(i);
			if(currentTop <= c.H) { //뒷 기둥이 더 크다면 (등호는 꼭대기가 여러개인 경우를 고려한 것)
				area += (c.L - currentTopLoc) * currentTop; //면적 계산
				//꼭대기 업데이트
				currentTop=c.H;
				currentTopLoc=c.L;
			}
		}
		//꼭대기 전 or 꼭대기가 여러개인 경우 제일 오른쪽 꼭대기 전까지 면적 계산
		area += currentTop; //(남은) 꼭대기(1개) 면적 계산
		
		int currentTop2 = cols.get(N-1).H; // <-- 방향으로 다시 시작
		int currentTopLoc2 = cols.get(N-1).L; // 
		
		int i=N-2;
		while(currentTop != currentTop2) {
			Column c = cols.get(i);
			if(currentTop2 <= c.H) {
				area+=(currentTopLoc2 - c.L) * currentTop2;
				currentTop2=c.H;
				currentTopLoc2 = c.L;
			}
			i--;
		}
		System.out.println(area);
	}
}

class Column implements Comparable<Column> {
	int L;
	int H;
	
	public Column(int l, int h) {
		super();
		L = l;
		H = h;
	}
	@Override
	public int compareTo(Column o) {
		return this.L - o.L;
	}
}
