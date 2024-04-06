import java.util.*;
import java.io.*;

public class Main
{
    static String X, Y; // 1000보다 작거나 같은 자연수꼴
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		X = st.nextToken();
		Y = st.nextToken();
		
		StringBuilder sb = new StringBuilder();
		sb.append(rev(X) + rev(Y));
		int result = rev(sb.toString());
		System.out.println(result);
	}
	
	static int rev(String S) {
	    String s = "";
	    for(int i = S.length() - 1; i >= 0; i--) {
	        if (s.length() == 0 && S.charAt(i) == '0') continue;
	        else {
	            s += S.charAt(i);
	        }
	        
	    }
	    
	    if(s.length() == 0) return 0;
	    else return Integer.parseInt(s);
	}
}
