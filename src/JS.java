import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by zhongjian on 2016/11/21.
 * 带有期限和效益的单位时间的作业排序，贪心法
 *
 * 构造后，调用js再printJ
 */
public class JS {


    private Job[] jobs; // 作业
    int[] j; //最优解，j[0] 存放最优解个数


    /**
     * 构造函数
     * @param jobs 传入待排序的作业
     */
    public JS(Job[] jobs) {
        this.jobs = jobs;
    }

    /**
     * 作业类
     */
    static class Job {
        int id;
        int deadline;
        int benefit;

        public Job(int id, int deadline, int benefit) {
            this.id = id;
            this.deadline = deadline;
            this.benefit = benefit;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "id=" + id +
                    ", deadline=" + deadline +
                    ", benefit=" + benefit +
                    '}';
        }
    }


    /**
     * 贪心法对带有期限和效益的单位时间的作业排序
     *
     * @return 最优解的作业id号
     */
    public int[] js() {
        //先对作业按效益进行非增排序
        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                if (o1.benefit > o2.benefit)
                    return -1;
                else if (o1.benefit == o2.benefit)
                    return 0;
                else
                    return 1;
            }
        });

        int k = 1;
        j = new int[jobs.length + 1];
        j[0] = 0; // 0的位置记录最优解个数
        j[1] = 0; // 计入第一个作业
        ++j[0];
        for (int i = 1; i < jobs.length; i++) {
            int r = k;
            //当r = 0 或者 第r个job的期限小于等于要判断的期限   或者       第r个job的期限等于r的时候终止
            while (r >= 1 && jobs[j[r]].deadline > jobs[i].deadline && jobs[j[r]].deadline != r) {
                --r;
            }
            if (jobs[j[r]].deadline <= jobs[i].deadline && jobs[i].deadline > r) {
                for (int l = k; l > r; --l) {
                    j[l + 1] = j[l];
                }
                j[r + 1] = i;
                ++j[0];
                ++k;
            }
        }
        return j;
    }

    /**
     * 输出最优解作业id号
     */
    public void printJ() {
        System.out.println("最优解作业id序号：");
        for (int i = 0; i < j[0]; i++) {
            System.out.print(jobs[j[i + 1]].id + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        JS js = new JS(new Job[]{new Job(1, 2, 20), new Job(2, 2, 15), new Job(3, 1, 10), new Job(4, 3, 5), new Job(5, 3, 1)});
        js.js();
        js.printJ();


        JS js2 = new JS(new Job[]{new Job(1, 4, 35), new Job(2, 2, 30), new Job(3, 4, 25), new Job(4, 3, 20), new Job(5, 4, 15)
                , new Job(6, 8, 10), new Job(7, 3, 5)});
        js2.js();
        js2.printJ();
    }


}
