import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhongjian on 2016/11/23.
 * 0/1背包问题，动态规划
 */
public class DKNAP {

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

    private int[] F;
    private List<Integer> P = new ArrayList<>();
    private List<Integer> W = new ArrayList<>();


    public DKNAP(int n, int[] p, int[] w) {
        set(n, p, w);
    }

    public void set(int n, int[] p, int[] w) {
        this.n = n;
        this.p = p;
        this.w = w;
        F = new int[n + 1];
        P.clear();
        W.clear();
    }

    /**
     * dknap算法
     * <p>
     * it's so amazing
     *
     * @param M
     */
    public void dknap(int M) {
        F[0] = 0;
        P.add(0);
        W.add(0);//S^0//
        int pp, ww;
        int l = 0, h = 0; //S^0 的首段和末端
        F[1] = 1;
        int next = 1;
        for (int i = 1; i <= n; i++) { //生成S^i
            int k = l;
            int u = max(l, h, M, i - 1);
            for (int j = l; j <= u; j++) { //生成S^i_1 及归并
                //S^i_1的下一个元素
                pp = P.get(j) + p[i - 1];
                ww = W.get(j) + w[i - 1];
                //从S^i_1 中取元素归并
                while (k <= h && W.get(k) < ww) {
                    P.add(P.get(k));
                    W.add(W.get(k));
                    ++k;
                    ++next;
                }
                if (k <= h && W.get(k) == ww) {
                    pp = Math.max(pp, P.get(k));
                    ++k;
                }
                if (pp > P.get(next - 1)) {
                    P.add(pp);
                    W.add(ww);
                    ++next;
                }
                //清除
                while (k <= h && P.get(k) <= P.get(next - 1)) {
                    ++k;
                }
            }
            //将S^{i-1} 中的剩余元素并入S^i
            while (k <= h) {
                P.add(P.get(k));
                W.add(W.get(k));
                ++next;
                ++k;
            }
            // 对S^{i+1}置初值
            l = h + 1;
            h = next - 1;
            if (i + 1 < F.length) {
                F[i + 1] = next;
            }
        }

        //生成X
        x = new int[n];
        int px = P.get(P.size() - 1);
        int wx = W.get(W.size() - 1);
        int py;
        for (int i = n - 1; i >= 0; --i) {
            int high = F[i + 1];
            int low = F[i];
            int index = -1;
            int max = -1;
            for (int j = high - 1; j >= low; --j) {
                if (W.get(j) <= (wx - w[i]) && W.get(j) > max) {
                    index = j;
                    break;
                }
            }
            if (index == -1) {
                x[i] = 0;
                continue;
            }
            py = P.get(index) + p[i];
            if(px > py){
                x[i] = 0;
            }else{
                x[i] = 1;
                px = P.get(index);
                wx = W.get(index);
            }

        }

    }

    /**
     * 求W（r）+w(i)<=M 最大的值的r，l<=r<=h
     *
     * @param l 最小范围
     * @param h 最大范围
     * @param M 背包容量
     * @param i 第i+1个物品
     * @return
     */
    private int max(int l, int h, int M, int i) {
        int max = 0;
        int r = 0;
        for (int j = l; j <= h; j++) {
            int temp = W.get(j) + w[i];
            if (temp > max && temp <= M) {
                max = temp;
                r = j;
            }

        }
        return r;
    }

    public void print() {
        System.out.print("P: ");
        for (int i = 0; i < P.size(); i++) {
            System.out.print(P.get(i) + " ");
        }
        System.out.println();
        System.out.print("W: ");
        for (int i = 0; i < W.size(); i++) {
            System.out.print(W.get(i) + " ");
        }
        System.out.println();
        System.out.print("X: ");
        for (int i = 0; i < n; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        DKNAP dknap = new DKNAP(3, new int[]{1, 2, 5}, new int[]{2, 3, 4});
        dknap.dknap(6);
        dknap.print();

        dknap.set(6, new int[]{100, 50, 20, 10, 7, 3}, new int[]{100, 50, 20, 10, 7, 3});
        dknap.dknap(165);
        dknap.print();

        dknap.set(4, new int[]{2,5,8,1}, new int[]{10, 15, 6, 9});
        dknap.dknap(15);
        dknap.print();
    }
}
