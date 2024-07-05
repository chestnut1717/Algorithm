import java.util.*;
import java.io.*;

public class Main
{
    static char[] charArr;
    static int length;
    static int N;
    static int arr[][];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        // 문자열 입력
        charArr  = br.readLine().toCharArray();
        
        length = charArr.length;
        arr = new int[length+1][26];
        
        // task 수 입력
        N = Integer.parseInt(br.readLine());
        
        for(int i = 1; i <= length; i++) {
            for(int j = 0; j < 26; j++) {
                
                if (j == charArr[i-1] -'a') {
                    arr[i][j] = arr[i-1][j] + 1;
                } else {
                    arr[i][j] = arr[i-1][j];
                }
                
            }
            
        }
        
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            char a = st.nextToken().toCharArray()[0];
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            
            sb.append(arr[r+1][a-'a'] - arr[l][a-'a']).append('\n');
            
           
        }
        
        
        bw.write(sb.toString());
        bw.close();
        
    }
}

