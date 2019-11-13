import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex03 {
    public static void checkSequence(int[] v){
        Stack<Integer> s = new Stack<Integer>();
        int n = v.length;
        int i = 0, j = 0;
        while(i < n && j < n){
            if(!s.isEmpty() && s.peek() == v[i]){
                StdOut.print(s.pop()+" ");
                i++;
            }
            else{
                if(j < n){
                    s.push(j);
                    j++;
                }
            }
        }
        StdOut.println();
        StdOut.printf("%s (Unprinted: %d: Stack:[ %s])\n", i == n && s.isEmpty(), n - i, s);
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAll().split("\\s+");
        int[] v = new int[a.length];
        for(int i = 0; i < a.length; i++){
            v[i] = Integer.parseInt(a[i]);
        }
        checkSequence(v);
    }
}
