import java.util.*;
import java.io.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        char[] king  = st.nextToken().toCharArray();
        char[] stone = st.nextToken().toCharArray(); 
        int N = Integer.parseInt(st.nextToken());
        
        while(N --> 0) {
            String cmd = br.readLine();
            
            char[] next_king = move(cmd, king);
            
            if(rangeCheck(next_king)) {
                if(next_king[0] == stone[0] && next_king[1] == stone[1]) {
                    char[] next_stone = move(cmd, stone);
                    
                    if(rangeCheck(next_stone)) {
                        king = next_king;
                        stone = next_stone;
                    }
                    else
                        continue;
                }
                else {
                    king = next_king;
                }
            }
            else {
                continue;
            }
        }
        
        // 킹 마지막 위치, 돌 마지막 위치 출력하기
        for(char k : king) {
            System.out.print(k);
        }
        System.out.println();
        
        for(char s : stone) {
            System.out.print(s);
        }
    }
    
    static boolean rangeCheck(char[] ch) {
        if(ch[0] < 'A' || ch[0] > 'H' || ch[1] < '1' || ch[1] > '8')    return false;
        return true;
    }
    
    static char[] move(String cmd, char[] target) {
        char[] result = target.clone();
        
        switch(cmd) {
            case "R":
                result[0]++;
                break;
            case "L":
                result[0]--;
                break;
            case "B":
                result[1]--;
                break;
            case "T":
                result[1]++;
                break;
            case "RT":
                result[0]++;    result[1]++;
                break;
            case "LT":
                result[0]--;    result[1]++;
                break;
            case "RB":
                result[0]++;    result[1]--;
                break;
            case "LB":
                result[0]--;    result[1]--;
                break;
        }
        return result;
    }
}