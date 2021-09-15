import java.util.HashMap;

public class PRO_PrimeNum {
	
	static int[] number;
	static boolean[] isSelected;
	static HashMap<Integer, Integer> hmap = new HashMap<>();
	
	public int solution(String numbers) {
		
		number = new int[numbers.length()];
		
		for(int i=0; i<number.length; i++) {
			number[i] =numbers.charAt(i) - '0';
		}
		
		//가능한 모든 순열 구하기
		for(int i=1; i<=number.length; i++) { //1개부터 숫자의 갯수만큼 뽑아서 순열
			isSelected = new boolean[numbers.length()];
			int[] result = new int[i];
			perm(0, i ,result);
		}
		
		int answer=0;
		for(Integer key : hmap.keySet()) {
			if(isPrime(key)) {
				answer++;
			}
		}
		return answer;
    }
	
private static boolean isPrime(Integer key) {
		
		if(key==1 || key==0) return false;
		
		for(int i=2; i<key; i++) {
			if(key%i==0) { //소수가 아니면
				return false;
			}
		}
		return true;
	}
	
	
	private static void perm(int cnt, int end, int[] result) {
		
		if(cnt==end) {
			int integer = toNumber(result); //뽑은 순열을 정수로 변환
			//System.out.println(integer);
			hmap.put(integer, hmap.getOrDefault(integer, 0)+1);
			return;
		}
		
		for(int i=0; i<number.length; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i]=true;
			result[cnt] = number[i];
			perm(cnt+1, end, result);
			isSelected[i]=false;
		}
		
		
	}
	private static int toNumber(int[] result) { //배열에 들어있는 값을 숫자로 바꾸기
		int integer=0;
		
		int mul = 1;
		for(int i=result.length-1; i>=0; i--) {
			integer += result[i] * mul;
			mul *= 10;
		}
		
		return integer;
	}
}
