import java.util.Arrays;

/**
 * Created by lzj on 2016/11/2.
 * 快排
 */
public class QuickSort {

    /**
     * 分治法快排
     *
     * @param A    待排序数组
     * @param low  低位
     * @param high 高位
     */
    public static void quickSort(int[] A, int low, int high) {
        if (low < high) {
            int j = high + 1;
            j = partition(A, low, j);//第j个位置已经排好
            quickSort(A, low, j - 1);
            quickSort(A, j + 1, high);
        }
    }


    /**
     * 对数组A中的m到j-1进行划分，使得m到j-1之间所有比A[m]小的排在A[m]前，比A[m]大的排在A[m]后
     *
     * @param A 数组
     * @param m 最小下标
     * @param j 最大下标 + 1
     * @return 划分下标（0开始）
     */
    public static int partition(int[] A, int m, int j) {
        int temp = A[m];
        int i = m;
        while (i < j) {
            do {
                ++i;
            } while (A[i] < temp && i < j - 1);

            do {
                --j;
            } while (A[j] > temp);

            if (i < j) {//交换
                Tool.interChange(A,i,j);
            }
        }

        A[m] = A[j];
        A[j] = temp;
        return j;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            int[] A = Tool.randomArray(Tool.random.nextInt(50) + 10, -1000,1000);
            System.out.println("before sort:");
            System.out.println(Arrays.toString(A));
            quickSort(A, 0, A.length - 1);
            System.out.println("after sort:");
            System.out.println(Arrays.toString(A));
            System.out.println();
        }

    }


}
