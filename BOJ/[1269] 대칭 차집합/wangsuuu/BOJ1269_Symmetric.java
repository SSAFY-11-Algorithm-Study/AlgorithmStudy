import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ1269_Symmetric {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, Integer> hash = new HashMap<>();
		int n = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<A; i++) {
			n = Integer.parseInt(st.nextToken());
			hash.put(n, hash.getOrDefault(n,0)+1);
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<B; i++) {
			n = Integer.parseInt(st.nextToken());
			hash.put(n, hash.getOrDefault(n, 0)+1);
		}
		
		int answer=0;
		for(int key : hash.keySet()) {
			if(hash.get(key) == 1)
				answer++;
		}
		System.out.println(answer);
	}
}
