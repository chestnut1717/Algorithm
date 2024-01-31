import java.io.*;
import java.util.*;

/**
 * @since jdk1.8
 * - 문제 : swea 2448 - 별 찍기 - 11
 * - 난이도 : 하
 * - 소요시간 : 2시간
 * - 아이디어 : 재귀를 통해서 계산
 *   1. 별찍기를 원활하게 수행하기 위해, 2차원 배열을 만들고, 그 배열 안에 1 = 별, 0 = 공백으로 구분하게 하여서 출력
 *   2. 하나의 큰 삼각형 => 작은 3개의 삼각형으로 만들어짐
 *   3. 작은 삼각형의 맨 왼쪽 하단 꼭짓점(y, x)를 기준으로 하여 각 삼각형의 width, height를 계산함
 *   4. 그 과정에서 N = 3*2^k꼴로 들어오므로, k를 활용해 작은 삼각형의 width, height를 구하는 것이 핵심
 */
class Main {
    static int[][] arr;
    static int N;
    static int pow;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        // 숫자 입력
        N = Integer.parseInt(br.readLine());
        
        
        // 승수 계산(항상 숫자는 3*2^k꼴로 들어오므로, k계산)
        int tmp = N/3;
        while(tmp > 1) {
            tmp = tmp / 2;
            pow++;
        }
        
        
        // 계산하기 편하게, 그림을 담을 배열은 본래 크기보다 1 크게 함(계산하기 편하게)
        int height = N+1;
        int width = (int) Math.pow(2, pow) * 6;
        arr = new int[height][width];
        
        // 그림 그리기
        drawTriangle(pow, N, 1);
        
        // 결과 출력
        for(int i = 1; i < height; i++) {
            for(int j = 1; j < width; j++ ) {
                // 1이라면, 별 찍고
                if(arr[i][j] == 1) {
                    sb.append("*");
                // 그렇지 않으면 공백 찍는다
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        
    }
    
    // 승수, 시작점(y, x)를 기준으로 계산
    static void drawTriangle(int k, int y, int x) {
        // 종료조건(가장 작은 삼각형 그림)
        if (k == 0) {
            arr[y-2][x+2] = 1;
            arr[y-1][x+1] = 1;
            arr[y-1][x+3] = 1;
            for(int i = 0; i < 5; i++) {
                arr[y][x+i] = 1;
            }
            return;
        }
        
        // k를 통해 더 작은 삼각형의 width, height를 계산
        int h = (int) Math.pow(2, k-1) * 3;
        int w = (int) Math.pow(2, k-1) * 6 - 1;
        
        // 그것을 토대로 작은 삼각형의 왼쪽 하단 y, x를 도출한 후 재귀
        drawTriangle(k-1, y, x);
        drawTriangle(k-1, y, x+w+1);
        drawTriangle(k-1, y-h, x+w/2+1);
        
    }
    
}