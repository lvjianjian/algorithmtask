import java.util.Arrays;

/**
 * Created by zhongjian on 2016/11/21.
 * 选择问题
 */
public class Select {


    /**
     * 从数组A中选择第k小的元素（k>=1 and k<A.length）
     *
     * @param A 数组A
     * @param k 第k小
     * @return 第k小元素下标(0开始) ,即返回k-1
     */
    public static int selectK(int[] A, int k) {
        int low = 0;
        int high = A.length;
        int j;
        while (low < high) {
            j = QuickSort.partition(A, low, high);
            if (j == k - 1) {
                return j;
            } else if (j > k - 1) {
                high = j;
            } else {
                low = j + 1;
            }
        }
        return -1;
    }

    /**
     * 改进的选择算法
     * @param A 数组A
     * @param k 第k小
     * @return 第k小元素下标(0开始) ,即返回k-1
     */
    public static int selectK2(int[] A,int k){


        return -1;
    }


    public static void main(String[] args) {
        int[] A = Tool.randomArray(100000, -100000, 100000);
        int x = selectK(A, 50000);
        System.out.println(x);
        System.out.println(A[x]);
    }
}
