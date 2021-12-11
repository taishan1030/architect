package com.wls.alg.class01;

/**
 * @Author:wangpeng
 * @Date: 2021/12/11
 * @Description: ***
 * @version:1.0
 */
/*
 * 给定两个数组x和hp，长度都是N。
 * x数组一定是有序的，x[i]表示i号怪兽在x轴上的位置；hp数组不要求有序，hp[i]表示i号怪兽的血量
 * 为了方便起见，可以认为x数组和hp数组中没有负数。
 * 再给定一个正数range，表示如果法师释放技能的范围长度, 被打到的每只怪兽损失1点血量。
 * 返回要把所有怪兽血量清空，至少需要释放多少次aoe技能？
 * 三个参数：int[] x, int[] hp, int range
 * 返回：int 次数
 *
 * */
public class Code06_AOE {

    // 暴力解法
    public static int minAoe1(int[] x, int[] hp, int range) {
        int N = x.length;
        int[] coverLeft = new int[N];
        int[] coverRight = new int[N];
        int left = 0;
        int right = 0;
        for (int i = 0; i < N; i++) {
            while (x[i] - x[left] > range ) {
                left++;
            }
            while (right < N && x[right] - x[i] <= range) {
                right++;
            }
            coverLeft[i] = left;
            coverRight[i] = right - 1;
        }
        return process(hp, coverLeft, coverRight);
    }

    public static int process(int[] hp, int[] coverLeft, int[] coverRight) {
        int N = hp.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int f = coverLeft[i]; f <= coverRight[i]; f++) {
                if (hp[f] > 0) {
                    int[] next = aoe(hp, coverLeft[i], coverRight[i]);
                    ans = Math.min(ans, 1 + process(next, coverLeft, coverRight));
                    break;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static int[] aoe(int[] hp, int L, int R) {
        int N = hp.length;
        int[] next = new int[N];
        for (int i = 0; i < N; i++) {
            next[i] = hp[i];
        }
        for (int i = L; i <= R; i++) {
            next[i] -= next[i] > 0 ? 1 : 0;
        }
        return next;
    }

    // 贪心策略：永远让最左边缘以最优的方式(AOE尽可能往右扩，最让最左边缘盖住目前怪的最左)变成0，也就是选择：
    // 一定能覆盖到最左边缘, 但是尽量靠右的中心点
    // 等到最左边缘变成0之后，再去找下一个最左边缘...
    public static int minAoe2(int[] x, int[] hp, int range) {
        int N = x.length;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (hp[i] > 0) {
                int triggerPost = i;
                while (triggerPost < N && x[triggerPost] - x[i] <= range) {
                    triggerPost++;
                }
                ans += hp[i];
                aoe(x, hp, i, triggerPost - 1, range);
            }
        }
        return ans;
    }

    public static void aoe(int[] x, int[] hp, int L, int trigger, int range) {
        int N = x.length;
        int RPost = trigger;
        while (RPost < N && x[RPost] - x[trigger] <= range) {
            RPost++;
        }
        int minus = hp[L];
        for (int i = L; i < RPost; i++) {
            hp[i] = Math.max(0, hp[i] - minus);
        }
    }


}
