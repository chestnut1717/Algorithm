import java.util.Scanner;

class Main {
    static int N;
    static int r;
    static int c;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in)        ;
        N = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();

        System.out.println(recur(N, r, c));
        
    }

    static int recur(int n, int y, int x) {
        if (n == 1) {
            return y*2 + x;
        }
        int div = (int) Math.pow(2, n-1);
        int mul = (int) Math.pow(2, 2*n-2);
        if (y/div == 0 && x/div == 0) return recur(n-1, y, x);
        else if (y/div == 0 && x/div == 1) return mul + recur(n-1, y, x-div);
        else if (y/div == 1 && x/div == 0) return mul * 2 + recur(n-1, y-div, x);
        else return mul * 3 + recur(n-1, y-div, x-div);
    }
}