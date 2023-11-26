//시작 12:15 - 제한 13:15
//사전 순으로 판별
//map 사용
//첫번째가 비동의 관련, 두번째가 동의 관련
import java.util.*;
class Solution {
    HashMap<String, Integer> map = new HashMap<String, Integer>();//최종적으로 판단해야하는 점수
    
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        for (int i = 0; i < survey.length; i++) {
            madeMap(survey[i], choices[i]);
        }
        
        return madeResult();
    }
    
    public void madeMap(String survey, int choice) {
        //점수에 해당하는 맵 처리
        String f = Character.toString(survey.charAt(0));
        String s = Character.toString(survey.charAt(1));
        
        switch(choice) {
            case 1:
                map.put(f, map.containsKey(f) ? map.get(f) + 3 : 3);
                break;
            case 2:
                map.put(f, map.containsKey(f) ? map.get(f) + 2 : 2);
                break;
            case 3:
                map.put(f, map.containsKey(f) ? map.get(f) + 1 : 1);
                break;
            case 5:
                map.put(s, map.containsKey(s) ? map.get(s) + 1 : 1);
                break;
            case 6:
                map.put(s, map.containsKey(s) ? map.get(s) + 2 : 2);
                break;
            case 7:
                map.put(s, map.containsKey(s) ? map.get(s) + 3 : 3);
                break;
            default:
                map.put(f, map.containsKey(f) ? map.get(f) + 0 : 0);
        }
    }
    
    public String madeResult() {
        String answer = "";
        
        String[][] types = {{"R","T"},{"C","F"},{"J","M"},{"A","N"}};
        
        for (int i = 0; i < types.length; i ++) {
            int first = map.containsKey(types[i][0]) ? map.get(types[i][0]) : 0;
            int second = map.containsKey(types[i][1]) ? map.get(types[i][1]) : 0;
            
            if (first < second) {
                answer += types[i][1];
            }
            else {
                answer += types[i][0];
            }
        }
        
        return answer;
    }
}