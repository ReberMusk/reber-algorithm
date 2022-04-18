package com.reber.algorithm.simple;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * https://leetcode-cn.com/problems/merge-sorted-array/
 * @author Tong yuan
 * @date 2022-04-17 21:56:00
 */
public class Merge {

    public static void main(String[] args) {
        int[] a = {1,2,3,0,0,0};
        int[] b = {2,4,5};

        merge2(a, 3, b, 3);
        System.out.println(Arrays.toString(a));
    }

    /**
     * my answer
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //判断存在一方长度为0的情况
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, nums2.length);
            return;
        }
        if (n == 0) {
            return;
        }

        int len = nums1.length;
        //从后倒数的指针，从1开始
        int cur1 = 1;
        int cur2 = 1;
        //当倒数到数组长度时，意味着该数组已经全部抽取完毕
        for (int i = 1; i <= len; i++) {
            //如果数组1存在数据部分被耗尽，则直接取数组2填充
            if (cur1 > m) {
                nums1[len - i] = nums2[n - cur2];
                cur2 = cur2 + 1;
                continue;
            }
            //数组2也耗尽，直接跳出
            if (cur2 > n) {
                break;
            }
            //last1 是数组1的最后一位数，指针一直向前
            int last1 = nums1[m - cur1];
            int last2 = nums2[n - cur2];
            //两个数组中取大的抽取出来填充到 数组1 中
            if (last1 > last2) {
                nums1[len - i] = last1;
                cur1 = cur1 + 1;
            } else {
                nums1[len - i] = last2;
                cur2 = cur2 + 1;
            }
        }
    }

    /**
     * 极限利用 i++ , i-- 这种用法
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m-- + --n;

        while (n >= 0) {
            nums1[i--] = m >= 0 && nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
    }
}
