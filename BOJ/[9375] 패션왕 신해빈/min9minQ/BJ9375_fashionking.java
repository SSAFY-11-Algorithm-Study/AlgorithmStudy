package time3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ9375_fashionking {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int num = Integer.parseInt(br.readLine());
			HashMap<String, Integer> map = new HashMap<>();
			for(int i = 0; i < num; i ++) {
				st = new StringTokenizer(br.readLine());
				String str1 = st.nextToken();
				String str2 = st.nextToken();
				if(!map.containsKey(str2)) {//그 종류의 옷이 없으면 새로추가
					map.put(str2, 1);
				} else {// 그 종류의 옷이 있으면 그 종류 옷 수 1 추가
					map.put(str2, map.get(str2)+1);
				}
			}
			
			int result = 1;
			for(int count : map.values()) {
				result *= (count+1);
			}
			result --; // 아무것도 안입은 건 빼야 됨.
			System.out.println(result);
			
			
		}
	}

}
