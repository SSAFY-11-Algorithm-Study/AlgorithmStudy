import java.util.Scanner;

//시간복잡도가 큼..

public class VSP {
	 
	/* VSP 일 조건
	 * 1. ( 와 ) 의 갯수가 동일해야 함
	 * 2. 갯수와 상관없이, -> 방향으로 진행하는 도중에 ( 의 수보다 )이 더 많이 나오면 VSP가 아님.
	 */
	
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String msg;
        
        String[] str = new String[num];
        for (int i = 0; i < str.length; i++) {
			str[i] = sc.next();
		}
        sc.close();
        
        for(int i=0; i<num; i++) { //총 test case 수만큼 반복
        	int left=0, right=0;
        	for (int j = 0; j < str[i].length(); j++) { //각 test case
				char c = str[i].charAt(j);
				//( 와 ) 갯수 세기
				if(c=='(')	left++;
				else 
				{
					right++;
					if(left<right) { // 2번에 해당
						msg="NO";
						break; }
				}
			}
        	if(left!=right) msg="NO"; //1번에 해당
        	else msg="YES";
        	
        	System.out.println(msg);
        }
    }
}
