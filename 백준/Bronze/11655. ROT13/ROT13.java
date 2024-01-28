import java.util.Scanner;

class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(char c : sc.nextLine().toCharArray()) {
            if (Character.isLowerCase(c)) System.out.print( (char) ((c - 'a' + 13) % 26 + 'a'));
            else if (Character.isUpperCase(c)) System.out.print( (char) ((c - 'A' + 13) % 26 + 'A'));
            else System.out.print(c);
        }

    }
};