import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by zhongjian on 2016/12/8.
 */
public class BiConnectedGraph {

    private Graph graph;

    /**
     * 深度优先生成树
     */
    private int[] DFN;

    /**
     * 最低深度优先数
     */
    private int[] L;

    private int num;

    /**
     * 存放边的栈
     */
    private LinkedList<int[]> S;

    public BiConnectedGraph(Graph graph) {
        set(graph);
    }

    public void set(Graph graph) {
        this.graph = graph;
        this.DFN = new int[graph.getN() + 1];
        this.L = new int[graph.getN() + 1];
        this.num = 1;
        this.S = new LinkedList<>();
    }


    /**
     * 计算DFN 和 L
     *
     * @param u 当前顶点
     * @param v 深度优先生成树中u的父亲节点 或者 0（u是根节点）
     */
    public void ART(int u, int v) {
        DFN[u] = L[u] = num;
        ++num;
        for (int i = 1; i <= graph.getN(); i++) {
            if (graph.hasEdge(i, u)) {//i是u的邻接节点
                //将树枝或者逆边加入栈
                if (v != i && DFN[i] < DFN[u]) {
                    S.push(new int[]{u, i});
                }

                if (DFN[i] == 0) {
                    ART(i, u);

                    if (L[i] >= DFN[u]) {
                        System.out.println("new biconnected component");
                        int x;
                        int y;
                        do {
                            int[] edge = S.pop();
                            x = edge[0];
                            y = edge[1];
                            System.out.println("(" + x + "," + y + ")");
                        } while ((x != u || y != i) && (x != i || y != u));
                    }
                    L[u] = Math.min(L[u], L[i]);
                } else if (i != v) {
                    L[u] = Math.min(L[u], DFN[i]);
                }
            }
        }
    }


    private void printLAndDFN() {
        System.out.println("L:");
        for (int i = 1; i <= graph.getN(); i++) {
            System.out.print(L[i] + ",");
        }
        System.out.println();
        System.out.println("DNF:");
        for (int i = 1; i <= graph.getN(); i++) {
            System.out.print(DFN[i] + ",");
        }
    }


    public static void main(String[] args) {
        Graph graph = new Graph(10, 13, Graph.TYPE_UNDIRECTED);
        graph.addAllEdges(new int[][]{{1, 4, 1}, {1, 2, 1}, {4, 3, 1}, {2, 3, 1}, {3, 10, 1}, {3, 9, 1}, {2, 5, 1}, {2, 7, 1}, {2, 8, 1}, {5, 8, 1}, {5, 7, 1}, {7, 8, 1}, {5, 6, 1}});
        BiConnectedGraph biConnectedGraph = new BiConnectedGraph(graph);
        biConnectedGraph.ART(1, 0);
        biConnectedGraph.printLAndDFN();
    }

}
