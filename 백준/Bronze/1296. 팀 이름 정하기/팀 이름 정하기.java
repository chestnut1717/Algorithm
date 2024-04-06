import java.util.*;
import java.io.*;

public class Main
{
    static int N; // 팀 갯수
    static int[] yeonArr = new int[26]; // 연두의 이름의 알파벳 count
    static int[] alphaArr; // 다른 팀 이름의 알파벳 count
    static int max = Integer.MIN_VALUE; // 가장 큰 확률 저장
    static String maxTeamName; // 가장 큰 확률을 가지고 있는 팀 이름 저장
    static PriorityQueue<String> pq = new PriorityQueue<>();
    
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    for(char c: br.readLine().toCharArray()) {
	        yeonArr[(int) (c-'A')]++;
	    }

	    // N개의입력을 받아준다.
	    N = Integer.parseInt(br.readLine());
	    for(int i = 0; i < N; i++) {
	        alphaArr = new int[26];
	        // 다른 팀 이름도 다음과 같이 처리해준다.
	        String teamName = br.readLine();
    	    for(char c: teamName.toCharArray()) {
    	        alphaArr[(int) (c-'A')]++;
    	    }
    	    // 그리고 우승 확률을 계산해 준다.
    	    int L = yeonArr[(int) ('L'-'A')] + alphaArr[(int) ('L'-'A')];
    	    int O = yeonArr[(int) ('O'-'A')] + alphaArr[(int) ('O'-'A')];
    	    int V = yeonArr[(int) ('V'-'A')] + alphaArr[(int) ('V'-'A')];
    	    int E = yeonArr[(int) ('E'-'A')] + alphaArr[(int) ('E'-'A')];
    	    
    	    
    	    int tmpMax = calc(L, O, V, E);
            
            // 갱신
    	    if(tmpMax > max) {
    	        max = tmpMax;
    	        pq.clear();
    	        pq.add(teamName);
    	        // 같을 경우, pq에 넣어준다.
    	    } else if (tmpMax == max) {
    	        pq.add(teamName);
    	    } 
	    }
	    
	    System.out.println(pq.poll());
	    
		
	}
	
	static int calc(int L, int O, int V, int E) {
	    // Int upper bound를 넘지 않으므로(20^6) int계산 가능
	    return ((L+O) * (L+V) * (L+E) * (O+V) * (O+E) * (V+E)) % 100;
	}
}
