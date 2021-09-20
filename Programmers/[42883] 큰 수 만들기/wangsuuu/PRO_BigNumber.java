  
//인터넷 참고해서 풀었습니다! (https://ukyonge.tistory.com/197)

public class PRO_BigNumber {

	public static void main(String[] args) {
		String number = "000000";
		int k = 4;
		System.out.println(solution(number, k));
	}

	private static String solution(String number, int k) {
		
		StringBuilder sb = new StringBuilder();
		
		int idx=0;
		for(int i=0; i<number.length()-k; i++) { //length-k의 수를 뽑아야 하므로
			int max=-1;
			for(int j=idx; j<=i+k; j++) { //뽑은 최댓값 다음부터 그 다음수를 뽑기 위해 다시 최댓값을 뽑아야 하므로
				if(max<number.charAt(j)-'0') {
					max=number.charAt(j)-'0';
					idx=j;
				}
			}
			//하나의 수를 뽑았다면
			idx++;
			sb.append(String.valueOf(max));
		}
		return sb.toString();
	}
}
