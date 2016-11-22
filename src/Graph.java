import java.util.Arrays;

/**
 * Created by zhongjian on 2016/11/22.
 * <p>
 * 邻接矩阵，顶点编号从1到n
 */
public class Graph {

    /**
     * 无穷设为整数最大值的一半
     */
    public static int INFINITE = Integer.MAX_VALUE / 2;

    /**
     * 有向图
     */
    public static int TYPE_DIRECTED = 1;

    /**
     * 无向图
     */
    public static int TYPE_UNDIRECTED = 2;

    /**
     * 顶点数量（同时事顶点最大值）
     */
    private int n;

    /**
     * 边的数量
     */
    private int e;

    /**
     * 边和边上的代价
     */
    private int[][] cost;

    /**
     * 有向图or无向图
     */
    private int type = -1;

    /**
     * @param n    顶点数量
     * @param e    边数量
     * @param type 类型
     */
    public Graph(int n, int e, int type) {
        this.n = n;
        this.e = e;
        this.type = type;
        this.cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i],Graph.INFINITE); // 初始为无穷大
        }
    }

    /**
     * @param i
     * @param j
     * @param cost
     * @return 返回自己
     */
    public Graph addEdge(int i, int j, int cost) {
        this.cost[i - 1][j - 1] = cost;
        if (type == TYPE_UNDIRECTED)
            this.cost[j - 1][i - 1] = cost;
        return this;
    }

    /**
     * 获取顶点数量
     *
     * @return
     */
    public int getN() {
        return n;
    }


    /**
     * 获取边数量
     *
     * @return
     */
    public int getE() {
        return e;
    }

    /**
     * 获取变（i，j）上的权值代价
     * @param i
     * @param j
     * @return
     */
    public int getCost(int i, int j) {
        return cost[i - 1][j - 1];
    }



}
