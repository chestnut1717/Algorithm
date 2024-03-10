/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.io.*;
import java.util.*;

public class Main
{
    static StringBuilder sb = new StringBuilder();
    static String str;
    static char[] bomb;
    static int pivot, idx;
    static Stack<Integer> savePoint = new Stack<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    str = br.readLine();
	    bomb = br.readLine().toCharArray();

		
        for(int i = 0; i < str.length(); i++) {
            char pivot = str.charAt(i);
            sb.append(pivot);
            // sb의 길이가 bomb길이 이상이 되면
            if(sb.length() >= bomb.length) {
                boolean isSame = true; // 같은지 안같은지 점검하기 위함
                for(int j = 0; j<bomb.length; j++) {
                    char ch1 = sb.charAt(sb.length() - bomb.length + j);
                    char ch2 = bomb[j];
                    if(ch1 != ch2) {
                        isSame = false;
                        break;
                    }
                }
                if(isSame) {
                    sb.delete(sb.length() - bomb.length, sb.length());
                }
            }
        }
		

		if(sb.length() == 0) {
		    bw.write("FRULA");
		}
		else {
		    bw.write(sb.toString());
		}
		bw.close();
		
	}
	

}
