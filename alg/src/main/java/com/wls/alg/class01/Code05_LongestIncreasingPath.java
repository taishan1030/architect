package com.wls.alg.class01;

/**
 * @Author:wangpeng
 * @Date: 2021/12/11
 * @Description: 一个二维数组， 可以上下左右移动， 找到一个最大的增长连
 * @version:1.0
 */
public class Code05_LongestIncreasingPath {

    public static int longestIncreasingPath1(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process1(matrix, i, j));
            }
        }
        return ans;
    }

    //从m[i][j]开始走，走出来的最长递增链，返回！
    public static int process1(int[][] matrix, int i, int j) {
        // 向上
        int up = i > 0 && matrix[i][j] < matrix[i-1][j] ? process1(matrix, i-1, j) : 0;
        // 向下
        int down = i < (matrix.length-1) && matrix[i][j] < matrix[i+1][j] ? process1(matrix, i+1, j) : 0;
        // 向左
        int left = j > 0 && matrix[i][j] < matrix[i][j-1] ? process1(matrix, i, j-1) : 0;
        //向右
        int right = j < (matrix[0].length-1) && matrix[i][j] < matrix[i][j+1] ? process1(matrix, i, j+1) : 0;
        return Math.max(Math.max(up, down), Math.max(left, right));
    }

    public static int longestIncreasingPath2(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process2(matrix, i, j, dp));
            }
        }
        return ans;
    }

    //从m[i][j]开始走，走出来的最长递增链，返回！
    public static int process2(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        // 向上
        int up = i > 0 && matrix[i][j] < matrix[i-1][j] ? process1(matrix, i-1, j) : 0;
        // 向下
        int down = i < (matrix.length-1) && matrix[i][j] < matrix[i+1][j] ? process1(matrix, i+1, j) : 0;
        // 向左
        int left = j > 0 && matrix[i][j] < matrix[i][j-1] ? process1(matrix, i, j-1) : 0;
        //向右
        int right = j < (matrix[0].length-1) && matrix[i][j] < matrix[i][j+1] ? process1(matrix, i, j+1) : 0;
        dp[i][j] =  Math.max(Math.max(up, down), Math.max(left, right));
        return dp[i][j];
    }
}
