/**
 * Created by lzj on 2016/11/2.
 * 求最大公约数
 */
public class GCD {

    /**
     * 递归求数a和数b的最大公约数
     * @param a 数a
     * @param b 数b
     * @return
     */
    public static int gcd(int a, int b){
        if(b == 0)
            return a;
        else
            return gcd(b,Math.floorMod(a,b));
    }



    public static void main(String[] args) {
        System.out.println(gcd(-12,2));
        System.out.println(gcd(4,24));
        System.out.println(gcd(1,24));
        System.out.println(gcd(100,28));
    }

}
