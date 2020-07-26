package cn.wyedward.algorithms.chapter4_2;

import cn.wyedward.algorithms.chapter1_3.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 有向图的表现
 */
public class Digraph {
    private final int V;    //表示顶点数
    private int E;           //表示边数
    private Bag<Integer>[] adj; //邻接表
    public Digraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0; v < V; v++){
            adj[v] = new Bag<Integer>();
        }
    }
    public Digraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    public void addEdge(int v, int w){
        adj[v].add(w);
        E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    public Digraph reverse(){
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++){
            for(int w : adj(v)){
                R.addEdge(w, v);
            }
        }
        return R;
    }

    /**
     * 求一个顶点的出度
     * @param v
     * @return
     */
    public int outdegree(int v){
        return adj[v].size();
    }

    /**
     * 求一个顶点的入度
     * @param v
     * @return
     */
    public int indegree(int v){
        return reverse().adj[v].size();
    }

    @Override
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w : this.adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

}
