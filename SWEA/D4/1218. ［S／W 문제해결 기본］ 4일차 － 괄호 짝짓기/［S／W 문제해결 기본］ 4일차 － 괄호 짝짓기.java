import java.io.*;
import java.util.*;

/**
 * @since jdk1.8
 * - 문제 : SWEA 1218. 괄호 짝짓기
 * - 소요시간 : 20분 
 * - 난이도 : 하
 * - 1. 스택을 활용해 간단히 해결
 * - 2. 괄호 짝짓기 조건을 활용하고, 모든 스택에 괄호를 넣고 난 후 => 스택이 비지 않았다면 0 출력
 */
class Solution {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		Map<Character, Character> map = new HashMap<>(); // 괄호 짝짓기를 활용하기 위해 생성
		map.put('>', '<');
		map.put(')', '(');
		map.put('}', '{');
		map.put(']', '[');


		/* 테스트 케이스 돌아감 */
		for (int test_case = 1; test_case <= T; test_case++) {
			
			int N = Integer.parseInt(br.readLine());//  테스트케이스 길이
			Stack<Character> stack = new Stack<>(); // 괄호 담을 스택 초기화
			char[] charArr = br.readLine().toCharArray(); // 괄호 개개별로 char 형식의 배열에 담아줌
	
			
			for(int i = 0; i < charArr.length; i++) {
				char nowChar = charArr[i];
				// 만약 현재 스택이 비어있고, 반복문이 끝나지 않았다면(더 비교할 대상 존재)
				if(stack.isEmpty()) {
					stack.add(nowChar);
					continue;
				}
				
				char topChar = stack.peek(); // 스택의 위에 있는 거면
				// 만약 기존값이 들어오는 값이랑 반대라면 => pop()
				if (isRight(nowChar) && map.get(nowChar) == topChar ) {
					
					stack.pop();
				// 그렇지 않으면 지금 들어온 것을 넣어줌
				} else {
					stack.push(nowChar);
				}
				
			}
			
			int result = 0;
			// 반복문이 끝나고 비어있으면 => 올바른 괄호
			if (stack.isEmpty()) {
				result = 1;
			}
			

			System.out.printf("#%d %d%n", test_case, result);
		}
	}
	
    // 오른쪽 괄호인지 여부 확인
	static boolean isRight(char c) {
		return c ==')' || c == ']' || c == '}' || c == '>';
	}

}