
import java.util.Scanner;

//음계 문제
public class Scale {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[8];
		// sort 사용! 오름차순과 같으면 ascending, 내림차순과 같으면 descending, else면 mixed!
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		sc.close();
		
		int flag; // 0 : ascending, 1: descending, 2:mixed
		
		int first_diff=arr[1]-arr[0];
		
		int i=1;
		
		if(first_diff==1) {
			flag=0; //ascending으로 set.
			while(i<7) {
				int diff = arr[i+1]-arr[i];
				if(diff!=1) { //하나라도 안 맞으면 바로 mixed
					flag=2;
					break;
				}
				i++;
			}
		}
		else if(first_diff==-1) {
			flag=1; //descending으로 세팅
			while(i<7) {
				int diff = arr[i+1]-arr[i];
				if(diff!=-1) {
					flag=2;
					break;
				}
				i++;
			}
		}
		else
			flag=2;
		
		if(flag==0) System.out.println("ascending");
		else if(flag==1) System.out.println("descending");
		else System.out.println("mixed");
	}	
}


