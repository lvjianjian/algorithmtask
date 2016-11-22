/**
 * Created by zhongjian on 2016/11/22.
 *
 * 构造最小生成树
 */
public abstract class MinimalSpanningTree {
    private Graph graph;//图

    int[][] T;//最小生成树

    int mincost = 0;


    public MinimalSpanningTree(Graph graph) {
        this.graph = graph;
        setGraph(graph);
    }

    public void setGraph(Graph graph) {
        if (graph == null) {
            System.err.println("error : graph is null");
            return;
        }
        this.graph = graph;
        T = new int[graph.getN() - 1][2];
    }



    protected Graph getGraph() {
        return graph;
    }

    /**
     * 生成最小生成树
     */
    public abstract void generate();

    /**
     * 打印最小生成树
     */
    public void printTree(){
        if(!finish){
            System.out.println("no spaning tree");
            return;
        }
        System.out.println("tree:");
        for (int i = 0; i < T.length; i++) {
            System.out.println(T[i][0]+","+T[i][1]+","+getGraph().getCost(T[i][0],T[i][1]));
        }
        System.out.println();
    }

    private boolean finish = false;

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
