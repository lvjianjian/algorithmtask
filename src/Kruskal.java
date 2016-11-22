import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zhongjian on 2016/11/22.
 * Kruskal算法求最小生成树
 */
public class Kruskal extends MinimalSpanningTree {

    private int parent[];

    //最小堆，int数组中int[2]为cost，int[0]和int[1]为边上的2个顶点
    private PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[2] - o2[2];
        }
    });
    ;


    public Kruskal(Graph graph) {
        super(graph);
    }

    @Override
    public void generate() {
        setFinish(false);
        parent = new int[getGraph().getN()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        mincost = 0;
        //构造最小堆
        heap.clear();
        for (int i = 1; i <= getGraph().getN(); i++) {
            for (int j = i + 1; j <= getGraph().getN(); j++) {
                int cost = getGraph().getCost(i, j);
                if (cost < Graph.INFINITE)//存在边
                    heap.add(new int[]{i, j, cost});
            }
        }

        int i = 0;//生成树的边的个数
        while (i < getGraph().getN() - 1 && !heap.isEmpty()) {
            int[] edge = heap.remove();
            int u = edge[0];//真实顶点
            int j = Tool.find(u - 1, parent);//真实顶点需要-1
            int v = edge[1];//真实顶点
            int k = Tool.find(v - 1, parent);
            if (j != k) {//不在一个集合中，即不在一颗树当中
                T[i][0] = u;
                T[i][1] = v;
                ++i;
                mincost += getGraph().getCost(u, v);
                Tool.union(j, k, parent);
            }
        }

        if (i != getGraph().getN() - 1) {
            System.out.println("no spaning tree");
        } else {
            setFinish(true);
            System.out.println("spanning tree has been constructed");
        }

    }

    public static void main(String[] args) {
        Graph graph = new Graph(6, 10, Graph.TYPE_UNDIRECTED);
        graph.addEdge(1, 2, 10).addEdge(1, 4, 30).addEdge(1, 5, 45).addEdge(2, 5, 40).addEdge(2, 6, 25).addEdge(2, 3, 50).addEdge(3, 5, 35)
                .addEdge(3, 6, 15).addEdge(4, 6, 20).addEdge(5, 6, 55);
        MinimalSpanningTree kruskal = new Kruskal(graph);
        kruskal.generate();
        kruskal.printTree();
    }
}
