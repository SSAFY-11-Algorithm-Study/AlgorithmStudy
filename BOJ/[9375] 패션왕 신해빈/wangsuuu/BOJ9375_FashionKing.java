import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

//hashMap 사용
//처음에 너무 어렵게 생각했음. 
//옷 이름을 해시맵에 넣을 필요 없이, 그냥 각 키값에 대한 value의 "갯수"만 세면 됨.

public class BOJ9375_FashionKing {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		HashMap<String, Integer> clothes = new HashMap<String, Integer>();

		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<T; tc++) {
			
			int num = Integer.parseInt(br.readLine()); //옷의 갯수
			
			//초기화 작업!
			clothes.clear();
			int answer = 1;
			
			for(int i=0; i<num; i++) {
				int value = 0;
				st = new StringTokenizer(br.readLine());
				String name = st.nextToken(); //사실 쓰이지 않음.
				String kind = st.nextToken(); //옷 종류(key)
				
				if(clothes.containsKey(kind)) { //이미 key값이 존재한다면
					value = clothes.get(kind); //해당 키값에 대한 value를 가져온 다음에
					value++; //그 value값을 증가시킨 다음 다시 해시맵에 넣어줌
				} else { //key값이 존재하지 않는다면
					value++;
				}
				clothes.put(kind, value);
			}
			//해시맵 구성 완료 (각 종류에 대한 의상 갯수)

			for(int v : clothes.values()) { //values() : 모든 value값 반환
				answer *= v+1;
			}
			
			sb.append(answer-1 + "\n"); //옷을 최소 하나는 선택해야 하므로
		}
		System.out.println(sb);
	}
}
