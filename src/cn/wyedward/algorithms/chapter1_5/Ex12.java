package cn.wyedward.algorithms.chapter1_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 使用路径压缩的quick-union算法
 * 1.5.12
 */
public class Ex12 {
    private int[] id; //父链接
    private int count; // 分量个数
    public Ex12(int N){
        id = new int[N];
        count = N;
        for(int i = 0; i < N; i++){
            id[i] = i;
        }
    }
    public int count(){
        return count;
    }
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    public int find(int p){
        int root = p;
        while(root != id[root]){
            root = id[root];
        }
        while(id[p] != root){
            int temp = p;
            p = id[p];
            id[temp] = root;
        }
        return root;
    }
    public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Ex12 uf = new Ex12(N);
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
        }
        StdOut.println(uf.count + " components" );
    }
}
