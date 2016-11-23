/**
 * Created by zhongjian on 2016/11/23.
 * <p>
 * 最优二分检索树，动态规划
 */
public class OBST {


    /**
     * 成功检索概率
     */
    private int[] P;

    /**
     * 不成功检索概率
     */
    private int[] Q;

    /**
     * 检索数量
     */
    private int n;

    private int[][] W;

    private int[][] C;

    private int[][] R;

    public OBST() {
    }

    /**
     * @param n 检索数量
     * @param P 成功检索概率，数量=n
     * @param Q 不成功检索概率，数量=n+1
     */
    public OBST(int n, int[] P, int[] Q) {
        set(n, P, Q);
    }

    /**
     * 设置值
     *
     * @param n
     * @param p
     * @param q
     */
    public void set(int n, int[] p, int[] q) {
        check(n, p, q);
        this.n = n;
        this.P = p;
        this.Q = q;
        this.W = new int[n + 1][n + 1];
        this.C = new int[n + 1][n + 1];
        this.R = new int[n + 1][n + 1];
    }

    /**
     * 检查输入是否合理
     *
     * @param n
     * @param p
     * @param q
     */
    private void check(int n, int[] p, int[] q) {
        if (p.length != n) {
            System.err.println("P is Unreasonable ");
            System.exit(-1);
        }
        if (q.length != n + 1) {
            System.err.println("Q is Unreasonable");
            System.exit(-1);
        }
    }


    /**
     * 执行OBST算法
     * im not a maker of code,im just a carrier of code
     */
    public void obst() {
        for (int i = 0; i < n; i++) {
            //初值
            W[i][i] = Q[i];
            C[i][i] = R[i][i] = 0;
            //含一个结点的最优树
            W[i][i + 1] = Q[i] + Q[i + 1] + P[i];
            R[i][i + 1] = i + 1;
            C[i][i + 1] = Q[i] + Q[i + 1] + P[i];
        }
        W[n][n] = Q[n];
        C[n][n] = R[n][n] = 0;
        //找有m个结点的最优树
        for (int m = 2; m <= n; m++) {
            for (int i = 0; i <= n - m; i++) {
                int j = i + m;
                W[i][j] = W[i][j - 1] + P[j - 1] + Q[j];
                //求k
                int k = -1;
                int min = Integer.MAX_VALUE;
                for (int l = i + 1; l < j; l++) {
                    int temp = C[i][l - 1] + C[l][j];
                    if (temp < min) {
                        k = l;
                        min = temp;
                    }
                }
                C[i][j] = W[i][j] + C[i][k - 1] + C[k][j];
                R[i][j] = k;
            }
        }

    }


    private void printCWR(int i, int j) {
        System.out.printf("%2d,%2d,%2d|", W[i][j], C[i][j], R[i][j]);
    }

    public void printAllWCR() {
        for (int i = 0; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                printCWR(j - i, j);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        OBST obst = new OBST(4, new int[]{3, 3, 1, 1,}, new int[]{2, 3, 1, 1, 1});
        obst.obst();
        obst.printAllWCR();
    }
}
