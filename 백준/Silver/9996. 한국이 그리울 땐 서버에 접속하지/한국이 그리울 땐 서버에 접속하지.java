import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Main {
    static int N;
    static String[] pArr;
    static String headPattern;
    static String tailPattern;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        // find head, tail pattern
        pArr = br.readLine().split("\\*");
        headPattern = pArr[0];
        tailPattern = pArr[1];


        for(int i = 0; i < N; i++) {
            String tmpStr = br.readLine();
            if (check(tmpStr)) {
                System.out.println("DA");
            } else {
                System.out.println("NE");
            }
        }
        
        
    }
    
    static boolean check(String tmpStr) {
        // a*a, a와 같은 반례 존재함
        if (tmpStr.length() < headPattern.length() + tailPattern.length()) {
            return false;
        }
        
        String head = tmpStr.substring(0, headPattern.length());
        String tail = tmpStr.substring(tmpStr.length() - tailPattern.length(), tmpStr.length());

        if(!head.equals(headPattern)) {
            return false;
        }
        if(!tail.equals(tailPattern)) {
            return false;
        }
        return true;
    }
}