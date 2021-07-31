
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BOJ_1181 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		
		String[] arr = new String[num];
		for (int i = 0; i < num; i++) {
			arr[i]=sc.next();
		}
		sc.close();
		//입력 완료
		
		
		//길이 순으로 정렬 : Comparator 활용
		//Comparator(o1, o2) 리턴값 : 음수(오름차순 정렬), 0(값이 같음), 양수(내림차순)
		
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length(); // 오름차순
				//리턴값이 양수라면 o1>o2이고, 왼쪽이 크기 때문에 오른쪽으로 보내짐.
				//음수라면 o1<o2이고, 그대로 작은 수가 왼쪽에 있게 됨.
				// return o2.length() - o1.length() 이면 내림차순
				
				//로직 정확히는 모르곘음..
			}
		});
		
		ArrayList<String> sorted = new ArrayList<>();
		for(String str:arr) {
			sorted.add(str);
		}
		
		//중복값 제거
		for (int i = 1; i < sorted.size(); i++) {
			if(sorted.get(i).equals(sorted.get(i-1)))
				sorted.remove(i-1);
					
		}
		//글자 수가 같을 경우 그 안에서 다시 정렬 필요
		
		for(String str : sorted) {
				System.out.println(str);
		}
		
	}
}
