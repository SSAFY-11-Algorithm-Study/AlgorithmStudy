import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//이분탐색!

public class BOJ2805_CuttingTree {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //나무의 수
		int M = Integer.parseInt(st.nextToken()); //집으로 가져가려고 하는 나무의 길이
		
		int[] trees = new int[N];
		int max = -1;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, trees[i]); 
		}//입력 완료
		
		long start=0, end = max;
		long mid=0;
		
		while(start <= end) {
			mid = (start+end)/2; //시도해 볼 톱의 길이
			long totalLen = 0; //상근이가 가져갈 수 있는 나무의 총 길이
			
			for (int i = 0; i < N; i++) {
				if(trees[i]>=mid) {
					totalLen += trees[i]-mid;
				}
				if(totalLen>=M) {
					break;
				}
			}
			
			if(totalLen>=M){ //최대 높이를 구하는 것이므로 M과 같아도 더 탐색해 봐야 함.
				start = mid+1;
			} else {
				end= mid-1;
			}
		}
		
		System.out.println(end);//mid가 아니고 end가 답.
	}
}
