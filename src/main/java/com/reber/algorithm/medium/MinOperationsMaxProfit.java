package com.reber.algorithm.medium;

/**
 * 你正在经营一座摩天轮，该摩天轮共有 4 个座舱 ，每个座舱 最多可以容纳 4 位游客 。你可以 逆时针 轮转座舱，但每次轮转都需要支付一定的运行成本 runningCost 。摩天轮每次轮转都恰好转动 1 / 4 周。
 *
 * 给你一个长度为 n 的数组 customers ， customers[i] 是在第 i 次轮转（下标从 0 开始）之前到达的新游客的数量。这也意味着你必须在新游客到来前轮转 i 次。每位游客在登上离地面最近的座舱前都会支付登舱成本 boardingCost ，一旦该座舱再次抵达地面，他们就会离开座舱结束游玩。
 *
 * 你可以随时停下摩天轮，即便是 在服务所有游客之前 。如果你决定停止运营摩天轮，为了保证所有游客安全着陆，将免费进行所有后续轮转 。注意，如果有超过 4 位游客在等摩天轮，那么只有 4 位游客可以登上摩天轮，其余的需要等待 下一次轮转 。
 *
 * 返回最大化利润所需执行的 最小轮转次数 。 如果不存在利润为正的方案，则返回 -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-profit-of-operating-a-centennial-wheel
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Tong yuan
 * @version 1.0, 2023-03-05 22:55:00
 */
public class MinOperationsMaxProfit {

    public static void main(String[] args) {
        int[] customers = new int[]{8, 3};
        System.out.println(minOperationsMaxProfit(customers, 5, 6));
    }

    public static int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int length = customers.length;
        // 剩余数量
        int wait = 0;
        // 利润
        int profit = 0;
        // 批次数
        int i = 0;
        // 最大利润，最佳批次
        int max = 0, res = -1;

        while (wait > 0 || i < length) {
            if (i < length) {
                wait += customers[i];
            }
            ++i;
            int cur = Math.min(4, wait);
            profit += boardingCost * cur - runningCost;
            wait -= cur;
            if (profit > max) {
                max = profit;
                res = i;
            }
        }
        return res;
    }
}
