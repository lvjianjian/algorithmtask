import java.util.Arrays;

/**
 * Created by zhongjian on 2016/11/21.
 * 插入排序
 */
public class InsertionSort {

    public static void insertionSort(int[] A) {
        for (int i = 1; i < A.length; i++) {
            int temp = A[i];
            int j = i - 1;
            while (j >= 0 && temp < A[j]) {
                A[j + 1] = A[j];
                --j;
            }
            A[j + 1] = temp;
        }
    }


    public static void main(String[] args) {
        int[] a = Tool.randomArray(10, -10, 10);
        System.out.println(Arrays.toString(a));
        insertionSort(a);
        System.out.println(Arrays.toString(a));
    }
}
