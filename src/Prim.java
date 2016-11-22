/**
 * Created by zhongjian on 2016/11/22.
 * <p>
 * Prim算法求最小生成树
 */
public class Prim extends MinimalSpanningTree {


    int[] near;

    public Prim(Graph graph) {
        super(graph);
    }

    @Override
    public void generate() {
        prim();
    }


    /**
     * prim算法构造最小生成树
     * 调用prim(1)
     */
    public void prim() {
        prim(1);
    }

    /**
     * prim算法构造最小生成树，从起点i开始
     *
     * @param i 起点，（1<=i<=n）
     */
    public void prim(int i) {
        if (i < 1 || i > getGraph().getN()) {
            System.err.println("error : 起点错误");
            return;
        }
        setFinish(false);

        this.mincost = 0;
        //初始化near
        this.near = new int[getGraph().getN()];
        for (int j = 0; j < getGraph().getN(); j++) {
            near[j] = i;
        }
        near[i - 1] = 0; //i 是真实id ，需要-1存入数组
        //寻找n-1条边
        for (int j = 0; j < T.length; j++) {
            int nodeid = findMinEdge();
            T[j][0] = nodeid;
            T[j][1] = near[nodeid - 1]; // nodeid是真实id，需要-1
//            System.out.println(T[j][0] + "," + T[j][1]);
            mincost += getGraph().getCost(nodeid, near[nodeid - 1]);//这里的nodeid是从1开始，因此在near数组中需要-1
            near[nodeid - 1] = 0;
            //更新near
            for (int k = 0; k < getGraph().getN(); k++) {
                if (near[k] != 0 && getGraph().getCost(k + 1, nodeid) < getGraph().getCost(k + 1, near[k])) //k+1是真实的点id
                    near[k] = nodeid;
            }
        }

        if (mincost >= getGraph().INFINITE) {
            System.out.println("no spaning tree");
        } else {
            setFinish(true);
            System.out.println("spanning tree has been constructed");
        }
    }


    //寻找没有加入最小生成树的最小边,返回顶点号
    private int findMinEdge() {
        int min = Integer.MAX_VALUE;
        int j = 0;
        for (int i = 0; i < getGraph().getN(); i++) {
            if (near[i] != 0) {
                int cost = getGraph().getCost(i + 1, near[i]); //这里的i需要+1后才是真实的点id
                if (cost < min) {
                    min = cost;
                    j = i + 1;
                }
            }
        }
        return j;
    }


    public static void main(String[] args) {
        Graph graph = new Graph(6, 10, Graph.TYPE_UNDIRECTED);
        graph.addEdge(1, 2, 10).addEdge(1, 4, 30).addEdge(1, 5, 45).addEdge(2, 5, 40).addEdge(2, 6, 25).addEdge(2, 3, 50).addEdge(3, 5, 35)
                .addEdge(3, 6, 15).addEdge(4, 6, 20).addEdge(5, 6, 55);
        MinimalSpanningTree prim = new Prim(graph);
        prim.generate();
        prim.printTree();
    }

}
