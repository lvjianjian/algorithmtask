/**
 * Created by lzj on 2016/11/2.
 * 二分检索
 */
public class BiSearch {


    /**
     * 二分检索查找数组a中是否有found值，有返回found在a中的下标，无返回-1
     * @param a 数组a（已排序）
     * @param low  查找低位
     * @param high 查找高位
     * @param found 带查找的值
     * @return found在a中的下标（从0开始）或-1
     */
    public static int bisearch(int[] a, int low, int high, int found) {
        if (low > high)
            return -1;
        int m = (low + high) / 2;
        if (m >= a.length)
            return -1;
        if (a[m] == found)
            return m;
        int f = bisearch(a, low, m - 1, found);
        if (f > -1)
            return f;
        else
            return bisearch(a, m + 1, high, found);
    }

    public static void main(String[] args) {
        System.out.println(bisearch(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, 10, 5));
        ;

    }

}
