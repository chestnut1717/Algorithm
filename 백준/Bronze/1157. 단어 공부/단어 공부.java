import java.util.*;
import java.io.*;

public class Main {
    // Map<Integer, Integer> map = new HashMap<>(); // 알파벳 count를 할 map
    static int[] arr = new int[26]; // counting배열
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] charArray = br.readLine().toCharArray();
		
		// 각문자마다 하나씩 탐색해서, 대소문자를 => 대문자로 변환시킨다.
		for(char c: charArray) {
		    // a = 97, A = 65;
		    int n;
		    if((int) c >= 97) {
		        n = c - 97;
		    } else {
		        n = c - 65;
		    }
		    // 이제 counting배열에 추가한다.
		    arr[n]++;
		}
		
		// 모두 탐색이 끝났으면 최댓값을 찾는다.
		int max = Integer.MIN_VALUE;
        char c = '?';
		
		for(int i = 0; i < arr.length; i++) {
		    if (max < arr[i]) {
		        c = (char) (i+65);
                max = arr[i];
		    } else if (max == arr[i]) {
		        c = '?';
		    }
		}
		
		System.out.println(c);

		
	}
}