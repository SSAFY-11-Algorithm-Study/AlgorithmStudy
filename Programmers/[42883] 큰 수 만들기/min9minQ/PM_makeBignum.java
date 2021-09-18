/* 테스트케이스 10 시간초과, 12 실패
 * (같은 경우 여러개 인 경우 실패...)
 * 
 */

package time8;

public class PM_makeBignum {

	static String numbers = "77777";
	static int K = 3;

	public static void main(String[] args) {

		StringBuilder sb = new StringBuilder();
		sb.append(numbers);
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < sb.length() - 1; j++) {
				if (sb.charAt(j) < sb.charAt(j + 1)) {
					sb.deleteCharAt(j);
					break;
				}

			}
		}
		System.out.println(sb);
	}
	

}
