package com.reber.algorithm.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 698. Partition to K Equal Sum Subsets
 *
 *  给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 *  
 *
 * 示例 1：
 *
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 * 示例 2:
 *
 * 输入: nums = [1,2,3,4], k = 3
 * 输出: false
 *  
 *
 * 提示：
 *
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 * 每个元素的频率在 [1,4] 范围内
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-to-k-equal-sum-subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Tong yuan
 * @since 2022-09-23 09:08:00
 */
public class PartitionK {

    public static void main(String[] args) {

//        int[] nums = new int[]{1,3, 3, 2, 3, 5, 2, 1};
//        int k = 4;
//        System.out.println(canPartitionKSubsets(nums, k));
//
//        int[] nums1 = new int[]{2,2,2,2,3,4,5};
//        int k1 = 4;
//        System.out.println(canPartitionKSubsets(nums1, k1));

        int[] nums2 = new int[]{1,1,1};
        int k2 = 3;
        System.out.println(canPartitionKSubsets(nums2, k2));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        Arrays.sort(nums);
        List<Integer> elemsSource = new ArrayList<>(nums.length);
        for (int num : nums) {
            sum += num;
            elemsSource.add(num);
        }
        if (sum % k != 0) {
            return false;
        } else {
            int o = sum / k;
            for (int j = 1; j < nums.length / 2 + 1; j++) {
                List<Integer> elems = new ArrayList<>(elemsSource);
                System.out.printf("开始尝试第%d种策略%n", j);
                for (int i = nums.length - j; i >= 0; i--) {
                    if (nums[i] > o) {
                        return false;
                    }

                    // 倒序比对，elems 每次删除掉正在比对的数
                    boolean contains = elems.contains(nums[i]);
                    if (!contains) {
                        // 已经用掉了当前递归的数，直接跳过
                        continue;
                    }

                    // 删除本次扫描的值
                    elems.remove(Integer.valueOf(nums[i]));

                    int diff = o - nums[i];
                    if (diff == 0) {
                        System.out.printf("已构成总和组合:%d,{%d}%n", o, nums[i]);
                    } else {
                        // 顺序寻找
                        List<Integer> resultIdx = new ArrayList<>();
                        if (!buildAverage2(elems, diff, resultIdx)) {
                            System.out.printf("剩余无法构成总和的组合:%d,{%d,%s}%n", o, nums[i], elems);
                            break;
                        } else {
                            for (Integer val : resultIdx) {
                                elems.remove(val);
                            }
                        }
                        System.out.printf("已构成总和组合:%d,{%d,%s}%n", o, nums[i], resultIdx);
                    }

                    if (elems.size() == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean buildAverage2(List<Integer> elems, int diff, List<Integer> result) {
        if (elems.size() <= 0) {
            return false;
        }
        // 直接查找是否有差值，差值组合是最佳的
        if (elems.contains(diff)) {
            result.add(diff);
            return true;
        }

        for (int i = elems.size() - 1; i >= 0; i--) {
            Integer cur = elems.get(i);
            if (cur < diff) {
                List<Integer> res = new ArrayList<>();
                res.add(cur);
                boolean b = buildAverage2(elems.subList(0, i), diff - cur, res);
                if (b) {
                    result.addAll(res);
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * error case
     *
     * @param elems 初步
     * @param diff  diff
     * @return boolean
     */
    private static boolean buildAverage(List<Integer> elems, int diff) {
        if (elems.size() == 0) {
            return false;
        }
        // 直接查找是否有差值，差值组合是最佳的
        if (elems.contains(diff)) {
            elems.remove(Integer.valueOf(diff));
            return true;
        }

        Integer first = elems.get(0);
        if (first > diff) {
            return false;
        } else if (first == diff) {
            elems.remove(0);
        } else {
            elems.remove(0);
            return buildAverage(elems, diff - first);
        }
        return true;
    }

}
