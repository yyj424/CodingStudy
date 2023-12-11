import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        
        HashMap<String, Integer> playerMap = new HashMap<String, Integer>();
        for (int i = 0; i < players.length; i++) {
            playerMap.put(players[i], i);
        }
        
        for (String c : callings) {
            int idx = playerMap.get(c);
            String temp = players[idx];
            players[idx] = players[idx - 1];
            players[idx - 1] = temp;
            
            //인덱스 변경된 부분을 map에도 반영해주어야 함
            playerMap.put(players[idx - 1], idx - 1);
            playerMap.put(players[idx], idx);
        }
        
        return players;
    }
}