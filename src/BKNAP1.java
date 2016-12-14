import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongjian on 2016/12/13.
 * 回溯法求解0/1背包问题
 */
public class BKNAP1 {

    /**
     * 物品数量
     */
    private int n;
    /**
     * 物品效益
     */
    private int[] p;
    /**
     * 物品重量
     */
    private int[] w;

    /**
     * 最优解
     */
    private int[] x;

    /**
     * 背包最后重量
     */
    private int fw;

    /**
     * 背包取得的最大效益
     */
    private int fp;

//    private int count = 1;

    public BKNAP1(int n, int[] p, int[] w) {
        set(n, p, w);
    }

    /**
     * 设置值，p/w的值按从大到小排
     *
     * @param n
     * @param p
     * @param w
     */
    public void set(int n, int[] p, int[] w) {
        this.n = n;
        this.p = p;
        this.w = w;
        this.x = new int[n];
        this.fp = -1;
    }


    /**
     * 回溯法求解0/1背包问题
     *
     * @param M 容量最大值
     */
    public void BKNAP1(int M) {
        back1(M, 0, 0, 1, new int[n]);
    }

    private void back1(int M, int cw, int cp, int k, int[] y) {
        if (k >= n) {
            if (cp > this.fp) {
                this.fp = cp;
                this.fw = cw;
                copyValue(y);
            }
            return;
        }


        if (cw + w[k - 1] <= M) {//向左孩子节点移动
            y[k - 1] = 1;
//            ++count;
            back1(M, cw + w[k - 1], cp + p[k - 1], k + 1, y);
            y[k - 1] = 0;
        }
        //向右孩子节点移动
        //由限界函数检测是否有必要移动
        if (BOUND(cp, cw, k, M) > this.fp) {
//            ++count;
            back1(M, cw, cp, k + 1, y);
        }
    }

    /**
     * 限界函数
     *
     * @param p 当前效益值
     * @param w 当前背包重量
     * @param k 上次去掉的物品
     * @param M 背包容量
     * @return 新的可能最大效益值
     */
    private double BOUND(int p, int w, int k, int M) {
        double b = p;
        double c = w;
        for (int i = k + 1; i <= n; i++) {
            c += this.w[i - 1];
            if (c < M) {
                b += this.p[i - 1];
            } else {
                return b + (1 - (c - M) / this.w[i - 1]) * this.p[i - 1];
            }
        }
        return b;
    }

    private void copyValue(int[] y) {
        for (int i = 0; i < x.length; i++) {
            x[i] = y[i];
        }
    }

    public void print() {
        System.out.print("X: ");
        for (int i = 0; i < n; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BKNAP1 bknap1 = new BKNAP1(3, new int[]{1, 2, 5}, new int[]{2, 3, 4});
        bknap1.set(8, new int[]{11, 21, 31, 33, 43, 53, 55, 65}, new int[]{1, 11, 21, 23, 33, 43, 45, 55});
        bknap1.BKNAP1(110);
        bknap1.print();
//        System.out.println(bknap1.count);
    }

}
