
public class Programmers_TargetNumber {
	
	static int cnt; //cnt : 경우의 수 누적

	public static int solution(int[] numbers, int target) {
		
		//기저조건
		if(numbers.length==0) {
			if(target==0) //조건 만족
				return cnt++;
			else //조건 만족 없이 끝났을 땐 누적 X
				return cnt;
		}
		
		int[] nums = new int[numbers.length-1];
		
		numbers[0]=numbers[0]; //첫번째 원소가 + 일 경우
		for(int i=0; i<nums.length; i++) //첫번째 원소를 제외한 나머지를 새로운 배열에 옮겨담음
			nums[i]=numbers[i+1]; 
		
		solution(nums, target-numbers[0]);
		
		numbers[0]=-numbers[0]; //첫번째 원소가 -일 경우
		for(int i=0; i<nums.length; i++)
			nums[i]=numbers[i+1];
		solution(nums, target-numbers[0]); //동일한 작업 수행. 
		//Flat하게, 각 +, -에 대한 결과가 누적 되었을 것이라 생각하고
		
		return cnt; //총 경우의 수 리턴
		
	}
	
	public static void main(String[] args) {
		
		int[] nums = {1,1,1,1,1};
		System.out.println(solution(nums, 3));
		
	}
}
