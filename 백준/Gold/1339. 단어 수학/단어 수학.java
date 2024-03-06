import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static String[] str; //문자열 저장하는 배열
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[] alphabet = new int[26]; // 문자열 배열
		str = new String[N];
		// 입력받기
		for(int i = 0; i < N; i++) {
		    str[i] = br.readLine();
		    for(int j = 0; j < str[i].length(); j++) {
		        int pos = str[i].length() - j;
		        alphabet[str[i].charAt(j) - 'A'] += Math.pow(10, pos-1);
		    }
		}
		
		Arrays.sort(alphabet);
		
		int ans = 0;
		int num = 9;
		
		for(int i = 25; i >= 0; i--) {
		    ans += alphabet[i] * num;
		    num--;
		}
		
		System.out.println(ans);
	}
}
