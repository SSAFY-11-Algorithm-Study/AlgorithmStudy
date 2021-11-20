package time17;

public class PM_예상대진표 {

	static int n = 8;
	static int a = 4;
	static int b = 7;

	public static void main(String[] args) {
		int answer = 0;
		while ( true) {
			if (a%2 == 1) {
				a = a/2 + 1;
			} else if(a%2 == 0){
				a = a/ 2;
			}
			
			if (b%2 == 1) {
				b = b/2 + 1;
			} else if ( b%2 ==0) {
				b= b/2;
			}
			answer++;
			if(a==b) break;
			
		}
		System.out.println(answer);
		//return answer;
	}

}
