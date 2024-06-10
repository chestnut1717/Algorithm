import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int[] stationary = new int[N];
        int min = Integer.MAX_VALUE;
        int index = -1;
        
        st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < N; i++) {
            stationary[i] = Integer.parseInt(st.nextToken());
            
            if(min >= stationary[i]) {
                min = stationary[i];
                index = i;
            }
            
        }
        
        int money = Integer.parseInt(br.readLine());
        char[] digits = new char[51];
        int cnt = 0;
        
        
        while(money >= min) {
            digits[cnt++] = (char) (index + '0');
            money -= min;
        }
        
        int start = 0;
        for(int i = 0; i < cnt; i++) {
            for(int j = N-1; j>= 0; j--) {
                if(stationary[j] <= money + min) {
                    digits[i] = (char) (j + '0');
                    money += min - stationary[j];
                    break;
                }
            }
            if(digits[start] == '0') {
                start++;
                money += min;
            }
        }
        
        String result = "";
        if(start == cnt) {
            result = "0";
        } else {
            for(int i = start; i < cnt; i++) {
                result += digits[i];
            }
        }
        
        bw.write(result);
        bw.close();
        
        
	}
}
