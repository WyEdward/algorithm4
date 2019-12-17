package cn.wyedward.algorithms.chapter2_2;
import cn.wyedward.algorithms.chapter2_1.Ex24;
import cn.wyedward.algorithms.chapter2_1.Ex25;
import cn.wyedward.algorithms.chapter2_1.Selection;
import cn.wyedward.algorithms.chapter2_1.Shell;
import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Merge;

public class SortCompare {
    public static double time(String alg, Double[] a){
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) {
            cn.wyedward.algorithms.chapter2_1.Insertion.sort(a);
        }
        if (alg.equals("Selection")) {
            Selection.sort(a);
        }
        if (alg.equals("Shell")) {
            Shell.sort(a);
        }
        if (alg.equals("Merge")) {
            Merge.sort(a);
        }
        if (alg.equals("Quick")) {
            Quick.sort(a);
        }
        if (alg.equals("Heap")) {
            Heap.sort(a);
        }
        // Exercise 2.1.24
        if (alg.equals("Ex24")) {
            Ex24.sort(a);
        }
        // Exercise 2.1.25
        if (alg.equals("Ex25")) {
            Ex25.sort(a);
        }
        if (alg.equals("Ex11")) {
            Ex11.sort(a);
        }
        return timer.elapsedTime();
    }
    public static double timeRandomInput(String alg, int N, int T){
        double total = 0.0;
        Double[] a = new Double[N];
        for(int t = 0; t < T; t++){
            for(int i = 0; i < N; i++){
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = StdIn.readString(); //表示测的类名
        int N = StdIn.readInt(); //500
        int T = StdIn.readInt(); //10000
        double t1 = timeRandomInput(alg1, N, T);
        StdOut.printf("%s 方法在%d次-%d个元素中运行的时间: %.1f", alg1, T, N, t1);
    }
}
