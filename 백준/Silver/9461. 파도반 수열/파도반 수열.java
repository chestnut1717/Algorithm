import java.util.*;
import java.io.*;

public class Main {
    static int T;
    static int N;
    static ArrayList<Long> list;
    
    public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        
        // 점화식 : F(n) = F(n-1) + F(n-5); (n>=5)
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<Long>(Arrays.asList(1L, 1L, 1L, 2L, 2L));
            
            for (int n = 0; n < N - 5; n++) {
                list.add(list.get(n) + list.get(n+4));
            }
            
            sb.append(list.get(N-1)).append('\n');
        }
        bw.write(sb.toString());
        bw.close();
    }
}
