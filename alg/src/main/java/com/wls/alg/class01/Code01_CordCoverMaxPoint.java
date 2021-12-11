package com.wls.alg.class01;

import java.util.Arrays;

/**
 * @Author:wangpeng
 * @Date: 2021/12/11
 * @Description: 1. 一个有序数组，给一个长度，最大可以覆盖几个数
 * @version:1.0
 */
public class Code01_CordCoverMaxPoint {

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int times = 100000;
        System.out.println("test start");
        for(int i=0;i<times;i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len,max);
            int result1 = maxPoint1(arr, L);
            int result2 = maxPoint2(arr, L);
            int result3 = test(arr, L);
            if (result1 != result2 || result2 != result3) {
                System.out.println("oops!");
                break;
            }
        }
    }

    public static int maxPoint1(int[] arr, int l) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr, i, arr[i] - l);
            res = Math.max(res, i - nearest + 1);
        }
        return res;
    }

    public static int nearestIndex(int[] arr, int R, int target) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = (L + (R-L)>>1);
            if (arr[mid] >= target) {
                index = mid;
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        return index;
    }

    // 滑动窗口
    public static int maxPoint2(int[] arr, int l) {
        int left = 0;
        int right = 0;
        int N = arr.length;
        int max = 0;
        while(left < N) {
            while(right< N && arr[right] - arr[left] <=l ) {
                right++;
            }
            max = Math.max(max, (right-left));
            left++;
        }
        return max;

    }

    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    public static int[] generateArray(int length, int max) {
        int[] ans = new int[(int)(Math.random() * length) + 1];
        for(int i=0;i<ans.length;i++){
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

}
