import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Main {
    static int N;
    static String pattern;
    static String headPattern = "";
    static String tailPattern = "";
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        // find Pattern
        pattern = br.readLine();
        int pos = pattern.indexOf("*");
        char[] tmpArr = pattern.toCharArray();
        
        // find Head Pattern
        for (int i = 0; i < pos; i++) {
            headPattern += tmpArr[i];
        }
        
        // find Tail Pattern
        for (int i = pos+1; i < tmpArr.length; i++) {
            tailPattern += tmpArr[i];
        }
        

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
        // a*a, a와 같은 반례 존재
        if (tmpStr.length() < pattern.length() - 1) {
            return false;
        }
        if(tmpStr.indexOf(headPattern) != 0 ) {
            return false;
        }
        if(tmpStr.length() - tailPattern.length() != tmpStr.lastIndexOf(tailPattern)) {
            return false;
        }
        return true;
    }
}