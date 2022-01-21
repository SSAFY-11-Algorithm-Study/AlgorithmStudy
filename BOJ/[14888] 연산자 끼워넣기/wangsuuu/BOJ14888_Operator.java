import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888_Operator {
	
	static int N;
	static boolean[] isSelected;
	static int[] numbers;
	static char[] operator;
	static char[] result;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N]; //숫자 저장
		operator=  new char[N-1]; //연산자 저장
		isSelected = new boolean[N-1];
		result = new char[N-1]; //순열의 결과 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int plus = Integer.parseInt(st.nextToken());
		int minus = Integer.parseInt(st.nextToken());
		int multi = Integer.parseInt(st.nextToken());
		int divide = Integer.parseInt(st.nextToken());
		
		fillOperator(plus, minus, multi, divide);
//		for(char c : operator) {
//			System.out.print(c);
//		}
//		
		perm(0);
		System.out.println(max);
		System.out.println(min);	
	}
	
	private static void perm(int cnt) {
		if(cnt==N-1) {
			int res = calculate();
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}
		for(int i=0; i<N-1; i++) {
			if(isSelected[i]) continue;
			isSelected[i]=true;
			result[cnt] = operator[i];
			perm(cnt+1);
			isSelected[i]=false;
		}
	}

	private static int calculate() {
		int res = numbers[0];
		
		for(int i=0; i<N-1; i++) {
			if(result[i]=='+') {
				res += numbers[i+1];
			} else if(result[i]=='-') {
				res -= numbers[i+1];
			} else if(result[i] == '*') {
				res *= numbers[i+1];
			} else {
				res /= numbers[i+1];
			}
		}
		return res;
	}

	static int idx;
	private static void fillOperator(int plus, int minus, int multi, int divide) {
		fillOp('+', plus); fillOp('-', minus);
		fillOp('*', multi); fillOp('/', divide);
	}
	private static void fillOp(char op, int kind) {
		for(int i=idx; i<idx + kind; i++) { // kind 가 아닌 idx + kind 임에 주의!
			if(op == '+') {
				operator[i] = '+';
			} else if(op=='-') {
				operator[i] = '-';
			} else if(op=='*') {
				operator[i] = '*';
			} else {
				operator[i] = '/';
			}
		}
		idx += kind;
	}
}
