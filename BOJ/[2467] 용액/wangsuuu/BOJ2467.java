import java.util.Scanner;

public class BOJ2467 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}//오름차순으로 입력이 들어옴
		
		int start = 0, end = N-1;
		int ans1=0, ans2=0;
		int min = Integer.MAX_VALUE;
		while(start <= end) {
			int sum = arr[start] + arr[end];
			if(start!=end && Math.abs(sum) < min) {
				min = Math.abs(sum);
				ans1 = arr[start];
				ans2 = arr[end];
			}
			
			if(sum == 0)
				break;
			else if(sum>0) end--; //합이 양수면 음수쪽으로 값을 옮겨야 0이랑 가까워지니까
			else start++; //합이 음수면 양수 쪽으로 값을 옮겨야 0이랑 가까워지니까
		}
		System.out.println(ans1 + " " + ans2);
	}
}
