package com.wls.alg.class01;

import java.io.File;
import java.util.Stack;

/**
 * @Author:wangpeng
 * @Date: 2021/12/11
 * @Description: 统计路径下有多少文件？
 * @version:1.0
 */
public class Code02_CountFiles {

    public static void main(String[] args) {
        // 你可以自己更改目录
        String path = "";
        System.out.println(getFileNumber(path));
    }

    public static int getFileNumber(String path) {
        File root  = new File(path);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        Stack<File> stack = new Stack<>();
        stack.add(root);
        int files = 0;
        while(!stack.isEmpty()) {
            File file = stack.pop();
            for (File next: file.listFiles()) {
                if (next.isFile()) {
                    files++;
                }
                if (next.isDirectory()) {
                    stack.push(next);
                }
            }
        }
        return files;

    }
}
