import java.util.*;
class Solution {
    public int[] solution(String[] park, String[] routes) {
        int sx = 0; //가로 축(열)
        int sy = 0; //세로 축(행)
        
        char[][] arr = new char[park.length][park[0].length()];
        
        for (int i = 0; i < park.length; i++) {
            arr[i] = park[i].toCharArray(); //[s,o,o]
            
            //equals가 아니라 contains를 사용 -> 중간에 시작점이 있을 수 있음
            if (park[i].contains("S")) {
                sy = i;
                sx = park[i].indexOf("S");
            }
        }
        
        for (String r : routes) {
            String direction = r.split(" ")[0];
            int move = Integer.parseInt(r.split(" ")[1]);
            
            int nx = sx;
            int ny = sy;
            
            //move 만큼 움직인다
            for (int i = 0; i < move; i++) {
                if (direction.equals("N")) {
                    ny--;
                }
                if (direction.equals("S")) {
                    ny++;
                }
                if (direction.equals("W")) {
                    nx--;
                }
                if (direction.equals("E")) {
                    nx++;
                }
                
                //공원을 벗어나지 않은 경우
                if (nx >= 0 && ny >= 0 && ny < arr.length && nx < arr[0].length) {
                    //장애물을 만나면 넘어감
                    if (arr[ny][nx] == 'X') {
                        break;
                    }
                    //성공한 경우
                    if (i == move - 1) {
                        sx = nx;
                        sy = ny;
                    }
                }
            }
        }
        
        int[] answer = {sy, sx};
        return answer;
    }
}