import java.util.ArrayList;

public class PRO_NewsClustering {
	
	static ArrayList<String> split1 = new ArrayList<>();
	static ArrayList<String> split2 = new ArrayList<>();
	
	public int solution(String str1, String str2) {
		
        //대소문자 구분 없애기 위해
		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();
        
        Split(str1, split1);
        Split(str2, split2);
        
        
        int answer = Calculate();
        
        return answer;
    }
	
	public int Calculate() { //자카드 유사도 계산
		
		int len1 = split1.size();
		int len2 = split2.size();
		
		//교집합 계산
		float intersect=0;
		for(int i=0; i<split1.size(); i++) {
			if(split2.contains(split1.get(i))) {
				split2.remove(split1.get(i));
				split1.remove(i);
				intersect++; i--;
			}
		}
		
		//합집합 계산
		float union = len1 + len2 - intersect;
		
		if(union==0) { //합집합이 0인 경우 자카드 유사도는 1
			return 65536;
		}
		
		float Jaccard = (intersect/union)*65536;
		
		return (int)Jaccard;
	}

	//다중집합 만들기
	public void Split(String str1, ArrayList<String> split) {
		
		//다중집합 쌍 모두 만들기
		for(int i=0; i<str1.length()-1; i++) {
			
			split.add(str1.substring(i, i+2));
			
		}
		
		//영문 구성인 것만 골라내기
		for(int i=0; i<split.size(); i++) {
			
			int tmp1 = split.get(i).charAt(0);
			int tmp2 = split.get(i).charAt(1);
			//모두 대문자로 바꿨고, 영문으로만 쌍이 이뤄져야 하므로 모든 원소는 65~90사이의 값이어야 한다.
			if(tmp1 < 65 || tmp1> 90 || tmp2<65 || tmp2>90) {
				split.remove(i);
				i--;
			}
		}
	}

}

/*
 * 아스키 코드값으로 A : 65, a:97 이라 A~Z는 65~96사이의 값인줄 알고 삽질했다ㅠㅠ
 * A~Z는 26개이므로 65~90까지고, 91~96은 다른 특수문자임을 기억할 것.
 * 
 */
