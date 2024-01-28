import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Main {
    static char[] alpha = new char[26];
    static int N;
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            alpha[br.readLine().toCharArray()[0] - 'a']++;
        }
        
        for(int i = 0; i < alpha.length; i++) {
            if(alpha[i] >= 5) {
                flag = true;
                System.out.print((char) (i + 'a'));
            }
        }
        if (flag == false) {
            System.out.println("PREDAJA");
        }
    }
}