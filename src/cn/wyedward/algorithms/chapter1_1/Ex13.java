package cn.wyedward.algorithms.chapter1_1;

public class Ex13 {
    public static void main(String[] args) {
        int[][] a = {{1,2,3},{4,5,6}};
        printTransposedMatrix(a);
    }
    public static void printTransposedMatrix(int[][] matrix){
        for (int i = 0; i < matrix[0].length; i++){
            for(int j = 0; j < matrix.length; j++){
                System.out.printf("%4d", matrix[j][i]);
            }
            System.out.println();
        }
    }

}
