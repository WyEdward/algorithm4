package cn.wyedward.algorithms.chapter4_1;

/**
 * 深度优先搜索
 * 搜索在图中与source点连通的个数和点的索引位置
 * 单点连通性
 */
public class DepthFirstSearch {
    private Boolean[] marked; //表示路口经过的标记 也就是与起点连通的顶点
    private int count;   //表示图的标记点的数量  如果是连通图的话就是顶点数量
    public DepthFirstSearch(Graph G, int s){
        marked = new Boolean[G.V()];
        dfs(G, s);
    }
    private void dfs(Graph G, int v){
        marked[v] = true;
        count++;
        for(int w : G.adj(v)){
            if(!marked[w]) dfs(G, w);
        }
    }
    public boolean marked(int w){
        return marked[w];
    }
    public int count(){
        return count;
    }
}
