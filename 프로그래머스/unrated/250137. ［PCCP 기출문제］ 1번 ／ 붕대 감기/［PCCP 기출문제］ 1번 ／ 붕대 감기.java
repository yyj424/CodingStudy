//시간 - 10:45 ~ (1시간 제한)
//기록 11:11 - 86.7점 - 6번,9번 실패
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int curHealth = health;
        int aIdx = 0; //공격 시간
        int success = 1;//연속 성공
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];
        
        for (int i = 1; i <= attacks[attacks.length - 1][0]; i++) {
            if (i == attacks[aIdx][0]) {
                curHealth -= attacks[aIdx][1];
                success = 0;
                aIdx++;
            } else {
                success++;
                
                int add = x;
                
                if (success == t) {
                    add += y;
                    success = 0;
                }
                
                if (health < curHealth + add) {
                    curHealth = health;
                } else {
                    curHealth += add;
                }
            }
            
            if (curHealth <= 0) {
                return -1;
            }
        }
        
        return curHealth;
    }
}