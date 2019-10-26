package cn.wyedward.algorithms.chapter1_1;

public class Ex14 {
    public static int lg(int N){
        int count = 0;
        do{
            N /= 2;
            count++;
        }while(N != 0);
        return count - 1;

    }
    public static void main(String[] args) {
        int value = 1025;
        int lg = lg(value);
        System.out.println(lg);
    }
}
