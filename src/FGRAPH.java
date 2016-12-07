/**
 * Created by zhongjian on 2016/12/7.
 * 多段图向前处理法
 */
public class FGRAPH {
    /**
     * k段图
     */
    private Graph graph;
    /**
     * k段
     */
    private int k;

    /**
     * 构造函数
     *
     * @param graph
     * @param k
     */
    public FGRAPH(Graph graph, int k) {
        set(graph, k);
    }


    public void set(Graph graph, int k) {
        this.graph = graph;
        this.k = k;
        cost = new int[graph.getN() + 1]; // 用1到n
        P = new int[k + 1];
        D = new int[graph.getN()];
    }

    /**
     * 每个节点到终点的最小代价
     */
    private int cost[];

    /**
     * 最小成本路径
     */
    private int P[];

    /**
     * 每个节点对应的向后最小成本点
     */
    private int D[];

    /**
     * 向前计算最小成本路径
     *
     * @return self
     */
    public FGRAPH compute() {
        int n = this.graph.getN();//多段图点数量
        cost[n] = 0;
        for (int i = n - 1; i > 0; --i) {//计算每一个节点到终点的最小成本
            int min = Integer.MAX_VALUE;
            for (int j = i + 1; j <= n; j++) { // 找到当前节点到终点的最小成本
                if (graph.getCost(i, j) + cost[j] < min) {
                    min = graph.getCost(i, j) + cost[j];
                    D[i] = j;//记录当前节点到终点的后一个节点
                }
            }
            cost[i] = min;//最小成本
        }
        //计算最小成本路径
        P[1] = 1;
        P[k] = n;
        for (int i = 2; i < k; i++) {
            P[i] = D[P[i - 1]];
        }
        return this;
    }

    public void printMinCostPath() {
        StringBuilder stringBuilder = new StringBuilder();
        String split = "--->";
        for (int i = 1; i < P.length; i++) {
            stringBuilder.append(P[i] + split);
        }
        stringBuilder.delete(stringBuilder.length() - split.length(), stringBuilder.length());
        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args) {
        Graph graph = new Graph(12, 21, Graph.TYPE_DIRECTED);
        graph.addEdge(1, 2, 9).addEdge(1, 3, 7).addEdge(1, 4, 3).addEdge(1, 5, 2)
                .addEdge(2, 6, 4).addEdge(2, 7, 2).addEdge(2, 8, 1).addEdge(3, 6, 2)
                .addEdge(3, 7, 7).addEdge(4, 8, 11).addEdge(5, 7, 1).addEdge(5, 8, 8)
                .addEdge(6, 9, 6).addEdge(6, 10, 5).addEdge(7, 9, 4).addEdge(7, 10, 3)
                .addEdge(8, 10, 5).addEdge(8, 11, 6).addEdge(9, 12, 4).addEdge(10, 12, 2).addEdge(11, 12, 5);
        FGRAPH fgraph = new FGRAPH(graph, 5);
        fgraph.compute().printMinCostPath();
    }

}
