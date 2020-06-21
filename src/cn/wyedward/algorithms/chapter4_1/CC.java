package cn.wyedward.algorithms.chapter4_1;

public class CC {
    private boolean[] marked; //判断是否标记了
    private int id[];  //每个点的连通分量标识符
    private int count; //连通分量数
    public CC(Graph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for(int s = 0; s < G.V(); s++){
            if(!marked[s]){
                dfs(G, s);
                count++;
            }
        }
    }
    private void dfs(Graph G, int v){
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if(!marked[w]){
                dfs(G, w);
            }
        }
    }
    public boolean connected(int v, int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }

}
