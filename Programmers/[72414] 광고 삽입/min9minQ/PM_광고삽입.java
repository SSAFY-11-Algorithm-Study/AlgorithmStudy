package time21;

public class PM_광고삽입 {

	public static void main(String[] args) {
		
		String play_time = "02:03:55";
		String adv_time = "00:14:15";
		String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
		
		String result = "";
		
		//여기서 이제 시작하고  누적 시청시간 종합시킨다음에
		//슬라이딩 윈도우 알고리즘? 으로 최대치 구하기..?
		//
		
		
		System.out.println(result);

	}
	
	private int toint(String time){
        String[] sArr = time.split(":");
        return 3600 * Integer.valueOf(sArr[0]) + 60 * Integer.valueOf(sArr[1]) + Integer.valueOf(sArr[2]);
    }
	
	private String tostring(int time){
        int hour = time / 3600;
        time %= 3600;
        int min = time / 60;
        time %= 60;
        int sec = time;
        
        StringBuilder sb = new StringBuilder("");
        if(hour < 10) sb.append("0");
        sb.append(Integer.valueOf(hour));
        sb.append(":");
        
        if(min < 10) sb.append("0");
        sb.append(Integer.valueOf(min));
        sb.append(":");
        
        if(sec < 10) sb.append("0");
        sb.append(Integer.valueOf(sec));
        
        return sb.toString();
    }

}
