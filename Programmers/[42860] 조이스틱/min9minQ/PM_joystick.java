// 좌우 생각 다시...

package time8;

public class PM_joystick {
	
	static String name = "ABBAA";
	static int length = name.length();
	static int min = length - 1;
	public static void main(String[] args) {
		int answer = 0;
		for(int i = 0; i < length; i++) {
			//상하
			char temp = name.charAt(i);
			// A에서 이동하는 게 빠른지 Z로가서 뒤에서 부터 오는 게 빠른지
			if(temp - 'A' < 'Z' - temp + 1) {//+1은 Z로 가기 위해
				answer += temp - 'A';
			} else {
				answer += 'Z' - temp + 1;
			}
			//좌우
			/*
			int nextidx = i+1;
			// 예를 들어 A가 연속 된 경우 왼쪽으로 쭉 돌아가서 뒤로 가는게 최소일 수 있음.
			while(nextidx < length && name.charAt(nextidx) == 'A') {
				nextidx ++;
			}
			
			min = Math.min(min, i*2 + length - nextidx); // i*2 는 그 목적지 까지가 갔다가 첫번쨰 자리로 돌아가기 횟수 + 뒤에서 부터 접근
			*/
		}
		
		//answer += min;
		
		System.out.println(answer);
	}

}
