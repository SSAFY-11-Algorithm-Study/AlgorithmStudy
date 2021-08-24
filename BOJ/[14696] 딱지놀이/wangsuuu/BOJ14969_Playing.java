import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14969_Playing {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //라운드 총 횟수
		StringBuilder sb = new StringBuilder();
		
		
		for(int i=0; i<N; i++) {
		
			int A_star=0, A_circle=0, A_square=0, A_triangle=0; 
			int B_star=0, B_circle=0, B_square=0, B_triangle=0; 
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A_num = Integer.parseInt(st.nextToken()); //A가 내는 딱지에 나온 그림의 총 개수
			for(int j=0; j<A_num; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n==4) A_star++;
				else if(n==3) A_circle++;
				else if(n==2) A_square++;
				else A_triangle++;
			}
			
			st = new StringTokenizer(br.readLine());
			int B_num = Integer.parseInt(st.nextToken()); //B가 내는 딱지에 나온 그림의 총 개수
			for(int j=0; j<B_num; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n==4) B_star++;
				else if(n==3) B_circle++;
				else if(n==2) B_square++;
				else B_triangle++;
			} //입력 완료
			
			if(A_star>B_star) {
				sb.append("A\n");
			} else if(A_star<B_star) {
				sb.append("B\n");
			} else { //별 갯수가 같은 경우 -> 동그라미 갯수 비교
				if(A_circle>B_circle) {
					sb.append("A\n");
				} else if(A_circle < B_circle){
					sb.append("B\n");
				} else { //동그라미 갯수가 같은 경우 -> 네모 갯수 비교
					if(A_square > B_square) {
						sb.append("A\n");
					} else if(A_square<B_square) {
						sb.append("B\n");
					} else { //네모 갯수가 같은 경우 -> 세모 갯수 비교
						if(A_triangle > B_triangle) {
							sb.append("A\n");
						} else if(A_triangle < B_triangle) {
							sb.append("B\n");
						} else {
							sb.append("D\n");
						}
					}
				}
		}
	}
		System.out.println(sb);
  }
}
