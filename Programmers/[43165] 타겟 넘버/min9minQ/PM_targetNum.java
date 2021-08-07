package time2;

public class PM_targetNum {//부분 집합
	
	private static int target = 3;
	private static int[] numbers = {1,1,1,1,1};
	private static boolean[] isSelected = new boolean[numbers.length];
	private static int totalCnt = 0 ;

	public static void main(int[] numbers, int target) {
		int answer = 0;
		
		Tarnum(0);
		answer = totalCnt;
		System.out.println(answer);
		//return answer;
		
		
	
	
	}
	public static void Tarnum(int cnt) {
		if(cnt == numbers.length){
			int result = 0;
			for(int i = 0; i < numbers.length; i ++) {
				if(isSelected[i]== true) {
					result += numbers[i];
				} else if(isSelected[i]==false) {
					result -= numbers[i];
				}
			}
			if(result == target) totalCnt++;
			return;
		}
		isSelected[cnt] = true;
		Tarnum(cnt+1);
		isSelected[cnt] = false;
		Tarnum(cnt+1);
		
	}
}
