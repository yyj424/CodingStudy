import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println(solution(sc.next()));
    }

    private static int solution(String n) {
        int num = Integer.parseInt(n);
        
        for (int i = num - (9 * n.length()); i < num; i++) {
            int sum = i;
            int answer = i;

            while (answer > 0) {
                sum += answer % 10;
                answer /= 10;
            }

            if (sum == num) {
                return i;
            }
        }
        
        return 0;
    }
}