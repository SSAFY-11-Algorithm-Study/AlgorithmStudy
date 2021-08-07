
import java.util.Scanner;
import java.util.Stack;

public class BOJ10773_Zero {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Stack<Integer> stack = new Stack<>();
		int sum=0;
		
		int num=sc.nextInt();
		 
		 for(int i=0; i<num; i++) {
			 int input = sc.nextInt();
			 if(input==0)
				 stack.pop();
			 else {
				 stack.push(input);
			 }
		 }
		 
		 while(!stack.isEmpty()) {
			 sum += stack.pop();
		 }
		 System.out.println(sum);
		
	}

}
