import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//동일한 거리를 최대한 넓게 해서 C개의 공유기를 설치해야 함(나머지 한두개 정도는 동일한 거리들보다 좀 더 넓게..)
//'동일한 거리'를 찾아야 하므로 "이분탐색"

public class BOJ2110_Wifi {
	
	static int N, C;
	static int[] house;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //집 갯수
		C = Integer.parseInt(st.nextToken()); //공유기 갯수
		house= new int[N];
		for(int i=0; i<N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);
		
		//가능한 간격의 범위
		int start = 1;
		int end = house[N-1] - house[0];
		
		int answer = 0;
		while(start <= end) {
			int mid = (start+end)/2;
			int num = wifiNum(mid);
			
			if(num >= C)
				start = mid+1;
			else
				end = mid-1;
		}
		System.out.println(end);
	}
	private static int wifiNum(int mid) { //간격이 mid일 때 설치할 수 있는 공유기의 총 갯수
		
		int cnt=0;
		int cur = house[0];
		cnt++; //첫 번째 집은 무조건 설치
		
		for(int i=1; i<N; i++) {
			if(house[i] - cur >= mid) { //공유기 설치 가능하면
				cur = house[i];
				cnt++;
			}
			if(cnt>C)
				break;
		}
		return cnt;
	}
}
