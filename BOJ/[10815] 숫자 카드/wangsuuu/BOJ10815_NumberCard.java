import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ10815_NumberCard {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<Integer, Integer> hash = new HashMap<>();
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = 0;
		for(int i=0; i<N; i++) {
			n = Integer.parseInt(st.nextToken());
			hash.put(n, hash.getOrDefault(n, 0)+1);
		}
		
		int M = Integer.parseInt(br.readLine());
		int[] input = new int[M];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<M; i++) {
			n = Integer.parseInt(st.nextToken());
			input[i] = n;
			hash.put(n, hash.getOrDefault(n, 0)+1);
		}
		//입력 완료
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			if(hash.get(input[i])==1)  //상근이가 숫자 카드가 없으면
				sb.append("0 ");
			else //숫자 카드가 있으면
				sb.append("1 ");
		}
		sb.setLength(sb.length()-1);
		System.out.print(sb);
	}
}
