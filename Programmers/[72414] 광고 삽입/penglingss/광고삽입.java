class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = toSecond(play_time);
        long[] timeLine = new long[playTime + 1];
        
        for(int i = 0; i < logs.length; i++) {
            String[] startAndEnd = logs[i].split("-");
            int startTime = toSecond(startAndEnd[0]);
            int endTime = toSecond(startAndEnd[1]);
            
            for(int j = startTime; j < endTime; j++) {
                timeLine[j]++;
            }
        }
        
        
        int adTime = toSecond(adv_time);
        long sum = 0;
        for(int j = 0; j < adTime; j++) {
            sum += timeLine[j];
        }
        
        int start = 0;
        long max = sum;
        for(int i = adTime; i < playTime; i++) {
            sum += timeLine[i];
            sum -= timeLine[i - adTime];
            
            if(sum > max) {
                max = sum;
                start = i - adTime + 1;
            }
        }
        
        return answerForm(start);
    }
    
    public int toSecond(String time) {
        int result = 0;
        String[] timeArray = time.split(":");
        
        for(int i = 0; i < 3; i++) {
            result += Integer.parseInt(timeArray[i]) * Math.pow(60,(2 - i));
        }
        return result;
    }
    
    public String answerForm(int time) {
        String result = "";
        
        int hour = time / (60 * 60);
        if(hour != 0) {
            if(hour < 10) result += "0";
            result += Integer.toString(hour);
            result += ":";
        } else result += "00:";
        time = time % (60 * 60);
        
        int min = time / 60;
        if(min != 0) {
            if(min < 10) result += "0";
            result += Integer.toString(min);
            result += ":";
        } else result += "00:";
        time = time % 60;
        
        if(time != 0) {
            if(time < 10) result += "0";
            result += Integer.toString(time);
        } else result += "00";
        
        return result;
    }
}
