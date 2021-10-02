package time10;
// 숫자 찾기 + 효율성 -> 이진탐색 생각.

public class PM_징검다리건너기 {
	static int[] stones = {2,4,5,3,2,1,4,2,5,1};
	static int k = 3;

	public static void main(String[] args) {
		int answer = 0 ;
		
		int start = 1;
		int last = 200000000;
		
		while(start <= last) {
			int mid = (start + last) / 2;
			
			if(cross(stones,k,mid)) { // 건널 수 있으니까 start값 올려서 다시 탐색
				start = mid + 1;
				answer = Math.max(answer, mid);
			} else { // 건널 수 없으니까 last값 낮춰서 다시
				last = mid - 1;
			}
			
		}
		
		System.out.println(answer);
		//return answer;
	}

	private static boolean cross(int[] stones, int k, int friends) {
		
		int count = 0;
		for(int i = 0; i < stones.length; i ++) {
			if(stones[i] < friends) { // 건널수 없음
				count ++;
			} else { // 건널 수 있음
				count = 0; // 건너 뛰어야 하는 돌 수 초기화
			}
			
			if(count == k) return false; // 뛰어넘어야 하는 돌 수 증가하다가 k와 같아지면 못 건넘
		}
		
		return true; // 위에서 return 안걸렸으므로 건널 수 있음
	}

}
