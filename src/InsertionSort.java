import java.util.Arrays;

/**
 * Created by zhongjian on 2016/11/21.
 * 插入排序
 */
public class InsertionSort {

    /**
     * 对数组A排序
     *
     * @param A
     */
    public static void insertionSort(int[] A) {
        insertionSort(A, 0, A.length - 1);
    }

    /**
     * 对数组A中low到high的元素排序
     *
     * @param A
     * @param low
     * @param high
     */
    public static void insertionSort(int[] A, int low, int high) {
        if (low > high) {
            System.err.println("insertionSort error:low > high");
            return;
        }
        for (int i = low + 1; i <= high; i++) {
            int temp = A[i];
            int j = i - 1;
            while (j >= low && temp < A[j]) {
                A[j + 1] = A[j];
                --j;
            }
            A[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] a = Tool.randomArray(10, -10, 10);
        System.out.println(Arrays.toString(a));
        insertionSort(a, 3, 7);
        System.out.println(Arrays.toString(a));
    }
}
