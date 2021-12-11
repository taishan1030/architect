package com.wls.alg.class02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @Author:wangpeng
 * @Date: 2021/12/11
 * @Description: ***
 * @version:1.0
 */
public class Code01_ChooseWork {

    public static class Job {
        public int money;
        public int hard;

        public Job(int m, int h) {
            hard = hard;
            money = money;
        }
    }

    public static class JobComparator implements Comparator<Job> {

        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? o1.hard - o2.hard : o2.money - o1.money;
        }
    }

    public static int[] moneys(Job[] jobs, int[] ability) {
        Arrays.sort(jobs, new JobComparator());
        // key : 难度   value：报酬
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(jobs[0].hard, jobs[0].money);
        // pre : 上一份进入map的工作
        Job pre = jobs[0];
        for (int i=1; i<jobs.length; i++) {
            if(jobs[i].hard != pre.hard) {
                map.put(jobs[i].hard, jobs[i].money);
                pre = jobs[i];
            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            // ability[i] 当前人的能力 <= ability[i]  且离它最近的
            Integer key = map.floorKey(ability[i]);
            ans[i] = key != null ? map.get(key) : 0;
        }
        return ans;
    }


}
