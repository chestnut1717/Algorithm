import java.util.*;
import java.io.*;

public class Main
{
    static int N;// 크레인 개수
    static int[] cranes;
    static int M; // 박스 개수
    static int[] boxes;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 크레인 입력
		N = Integer.parseInt(br.readLine());
		cranes = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
		    cranes[i] = Integer.parseInt(st.nextToken());
		}
		
		// 박스 입력
		M = Integer.parseInt(br.readLine());
		boxes = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
		    boxes[i] = Integer.parseInt(st.nextToken());
		}
		
		// 우선 크레인 오름차순 정렬해준다.
		Arrays.sort(cranes);
		
		// 그리고 박스도 오름차순으로 정렬해준다.
		Arrays.sort(boxes);
		
		// 탐색 자체를 못할경우
		if (boxes[M - 1] > cranes[N - 1]) {
            System.out.println(-1);
            System.exit(0);
        }
		
		Stack<Integer> stk = new Stack<>(); // 값 저장할 스택

		// 탐색 가능한 경우
		for(int i = 0; i < boxes.length; i++) {
		    stk.push(boxes[i]);
		}
			// 반복
    	int time = 0;
    	int minIndex = 0; // 무게제한때문에 들 수 없는 크레인은 제외
    	while(!stk.isEmpty()) {
    	    // 우선 스택이 빈다면
    	    Stack<Integer> tmpStk = new Stack<>(); // 임시 스택
    	    for(int i = N-1; i >= minIndex; i--) {
    	        while(!stk.isEmpty() && stk.peek() > cranes[i]) {
    	            int box = stk.pop();
    	            tmpStk.push(box);
    	        }
    	        if(stk.isEmpty()) {
        	        minIndex = i+1;
        	        break;
        	    } else {
        	        stk.pop();
        	    }
    	    }
    	    
    	    
    	    // 반복이 끝날 경우 임시 스택에 저장한 값 저장
    	    while(!tmpStk.isEmpty()) {
    	        stk.push(tmpStk.pop());
    	    }
    	    time++;
    	}
	
    	
    	System.out.println(time);
	}
	

}