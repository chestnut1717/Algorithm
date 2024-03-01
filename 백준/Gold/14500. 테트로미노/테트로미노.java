import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static boolean[][] t1 = {{true, true, true, true}};
    static boolean[][] t2 = {{true, true}, {true, true}};
    static boolean[][] t3 = {{true, false}, {true, false}, {true, true}};
    static boolean[][] t4 = {{true, false}, {true, true}, {false, true}};
    static boolean[][] t5 = {{true, true, true}, {false, true, false}};
    static List<boolean[][]> blocks = Arrays.asList(t1, t2, t3, t4, t5);
    static int totalSum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        // 배열 입력
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        


        // 각각의 블록에 대해
        for(int i = 0; i < blocks.size(); i++) {
            boolean[][] block = blocks.get(i);
            
            // System.out.printf("%d----%n", i);
            // 블록의 종류에 따라 회전횟수 다름
            int rotateCnt = 0;
            switch (i) {
                case 0 : rotateCnt = 2; break; // 1 x 4
                case 1 : rotateCnt = 1; break; // 2 x 2
                case 2 : rotateCnt = 4; break; // ㄴ
                case 3 : rotateCnt = 2; break;
                case 4 : rotateCnt = 4; break; // ㅜ
            }
            
            
            
            for(int j = 0; j < rotateCnt; j++) {
                int height = block.length;
                int width = block[0].length;
                // 이제 회전하므로 좌, 우 바뀜
                int newHeight = width;
                int newWidth = height;

                // 테트로미노 하나 놓인 것 계산
                int tmpSum = calcSum(block, height, width);
                
                // System.out.println(tmpSum);
                totalSum = Math.max(totalSum, tmpSum);
                
                // if(i == 2 || i == 3) {
                //     block = reverse(block, height, width);
                //     tmpSum = calcSum(block, height, width);
                //     totalSum = Math.max(totalSum, tmpSum);
                // }

                block = rotate(block, height, width, newHeight, newWidth);
            }
            
            
            if(i == 2 || i == 3) {
                int height = block.length;
                int width = block[0].length;
                // 이제 회전하므로 좌, 우 바뀜
                int newHeight = width;
                int newWidth = height;
                block = reverse(block, height, width);
                for(int j = 0; j < rotateCnt; j++) {
                    height = block.length;
                    width = block[0].length;
                    // 이제 회전하므로 좌, 우 바뀜
                    newHeight = width;
                    newWidth = height;
    
                    // 테트로미노 하나 놓인 것 계산
                    int tmpSum = calcSum(block, height, width);
                    
                    // System.out.println(tmpSum);
                    totalSum = Math.max(totalSum, tmpSum);
                    
                    // if(i == 2 || i == 3) {
                    //     block = reverse(block, height, width);
                    //     tmpSum = calcSum(block, height, width);
                    //     totalSum = Math.max(totalSum, tmpSum);
                    // }
    
                    block = rotate(block, height, width, newHeight, newWidth);
                }
                // for(int j = 0; j < rotateCnt; j++) {
                //     int height = block.length;
                //     int width = block[0].length;
                //     System.out.println("before");
                //     for(int y = 0; y < height; y++) {
                //         for(int x = 0; x < width; x++) {
                //             System.out.printf("%b ", block[y][x]);
                //         }
                //         System.out.println();
                //     }
                //     System.out.println();
                //     block = reverse(block, height, width);
                //     System.out.println("after");
                //     for(int y = 0; y < height; y++) {
                //         for(int x = 0; x < width; x++) {
                //             System.out.printf("%b ", block[y][x]);
                //         }
                //         System.out.println();
                //     }
                //     System.out.println();
                //     // 테트로미노 하나 놓인 것 계산
                //     int tmpSum = calcSum(block, height, width);
                //     System.out.println(tmpSum);
                    
                //     // System.out.println(tmpSum);
                //     totalSum = Math.max(totalSum, tmpSum);
                    
                //     int newHeight = width;
                //     int newWidth = height;
                //     block = rotate(block, height, width, newHeight, newWidth);
    
                    
                // }
            }

        }
        

        System.out.println(totalSum);

    
    }
    
    static int calcSum(boolean[][] block, int h, int w) {
        int maxSum = 0;
        // i란 시작점의 y좌표
        // j란 시작점의 x좌표
        for(int i = 0; i < N-h+1; i++) {
            for(int j = 0; j < M-w+1; j++) {
                int tmpSum = boxSum(block, i, j, h, w);
                maxSum = Math.max(maxSum, tmpSum);
            }
        }
        return maxSum;
    }
    
    // 테트리스 내의 숫자 입력
    static int boxSum(boolean[][] block, int startY, int startX, int h, int w) {
        int sum = 0;
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(block[i][j]) sum += arr[i + startY][j + startX];
            }
        }
        return sum;
    }
    
    static boolean[][] reverse(boolean[][] block, int h, int w) {
        boolean[][] tmp = new boolean[h][w]; // 2 * 3
        // System.out.println(h);
        // System.out.println(w);
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                tmp[i][w-1-j] = block[i][j];
            }
        }
        return tmp;
    }
    
    static boolean[][] rotate(boolean[][] block, int h, int w, int newH, int newW) {
        boolean[][] tmp = new boolean[newH][newW]; // 2 * 3
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                tmp[w-j-1][i] = block[i][j];
            }
        }
        
        return tmp;
    }
    
    
    
}

