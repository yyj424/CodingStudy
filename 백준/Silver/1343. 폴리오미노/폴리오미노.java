import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        bw.write(solution(br.readLine()));
        
        bw.flush();
        br.close();
        bw.close();
    }

    public static String solution(String ex) {
        String a = "AAAA";
        String b = "BB";

        String answer = "";
        
        ex = ex.replaceAll("XXXX", a);
        answer = ex.replaceAll("XX", b);

        if (answer.contains("X")) {
            return "-1";
        } else {
            return answer;
        }
    }
}
