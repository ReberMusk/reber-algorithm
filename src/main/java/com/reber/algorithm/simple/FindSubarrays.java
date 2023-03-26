package com.reber.algorithm.simple;

import java.util.HashSet;

/**
 * 给你一个下标从 0 开始的整数数组 nums ，判断是否存在 两个 长度为 2 的子数组且它们的 和 相等。注意，这两个子数组起始位置的下标必须 不相同 。
 * <p>
 * 如果这样的子数组存在，请返回 true，否则返回 false 。
 * <p>
 * 子数组 是一个数组中一段连续非空的元素组成的序列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,2,4]
 * 输出：true
 * 解释：元素为 [4,2] 和 [2,4] 的子数组有相同的和 6 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-subarrays-with-equal-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Tong yuan
 * @version 1.0, 2023-03-26 23:04:00
 */
public class FindSubarrays {

    public static void main(String[] args) {
        int[] ab = new int[]{1,2,3,4,5};
        System.out.println(findSubarrays(ab));
    }

    public static boolean findSubarrays(int[] nums) {
        int length = nums.length;
        if (length > 2) {
            HashSet<Integer> hashSet = new HashSet<>();
            for (int i = 0; i < nums.length - 1; i++) {
                boolean add = hashSet.add(nums[i] + nums[i + 1]);
                if (!add) {
                    return true;
                }
            }
        }
        return false;
    }

}
