import java.util.*;
import java.io.*;

public class Main
{
    static char[] L, R;
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = st.nextToken().toCharArray();
		R = st.nextToken().toCharArray();
		
		int result = 0;
		
		// 일단 배열의 수가 다르면 => 자릿수의 수도 다르다는 의미 => 무조건 0
		if(L.length != R.length) {
		    System.out.println(result);
		// 자릿수가 같을 경우
		} else {
            // 모든 자릿수를 탐색해서, 각 자릿수가 모두 8로 끼어있는지 확읺나다.
    		for(int i = 0; i < L.length; i++) {
    		    if(L[i] == '8' && R[i] == '8') {
    		        result++;
    		    } else if(L[i] == R[i]) {
    		        continue;
    		    }
    		    else{
    		        break;
    		    }
    		}

    		System.out.println(result);
		}

		
		
	}
}