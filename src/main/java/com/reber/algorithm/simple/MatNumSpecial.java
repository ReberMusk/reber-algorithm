package com.reber.algorithm.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1582. 二进制矩阵中的特殊位置
 * 给你一个大小为 rows x cols 的矩阵 mat，其中 mat[i][j] 是 0 或 1，请返回 矩阵 mat 中特殊位置的数目 。
 *
 * 特殊位置 定义：如果 mat[i][j] == 1 并且第 i 行和第 j 列中的所有其他元素均为 0（行和列的下标均 从 0 开始 ），则位置 (i, j) 被称为特殊位置。
 *
 *
 *
 * 示例 1：
 *
 * 输入：mat = [[1,0,0],
 *             [0,0,1],
 *             [1,0,0]]
 * 输出：1
 * 解释：(1,2) 是一个特殊位置，因为 mat[1][2] == 1 且所处的行和列上所有其他元素都是 0
 * 示例 2：
 *
 * 输入：mat = [[1,0,0],
 *             [0,1,0],
 *             [0,0,1]]
 * 输出：3
 * 解释：(0,0), (1,1) 和 (2,2) 都是特殊位置
 * 示例 3：
 *
 * 输入：mat = [[0,0,0,1],
 *             [1,0,0,0],
 *             [0,1,1,0],
 *             [0,0,0,0]]
 * 输出：2
 * 示例 4：
 *
 * 输入：mat = [[0,0,0,0,0],
 *             [1,0,0,0,0],
 *             [0,1,0,0,0],
 *             [0,0,1,0,0],
 *             [0,0,0,1,1]]
 * 输出：3
 *
 *
 * 提示：
 *
 * rows == mat.length
 * cols == mat[i].length
 * 1 <= rows, cols <= 100
 * mat[i][j] 是 0 或 1
 *
 * https://leetcode.cn/problems/special-positions-in-a-binary-matrix/
 * @author Tong yuan
 * @since 2022-09-04 22:56:00
 */
public class MatNumSpecial {

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {1,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,1,0},
                {0,0,0,0,0,0,0,1}
        };
        System.out.println("result ===: " + numSpecial(a));
    }

    public static int numSpecial(int[][] mat) {
        int rows = mat.length;
        if (rows == 0) {
            return 0;
        }
        int cols = mat[0].length;
        //理解成一个坐标体系 count每个 x 上的 1 的数量
        int[] x = new int[rows];
        int[] y = new int[cols];
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 当存在 1 则将对应的值 + 1
                if (mat[i][j] == 1) {
                    x[i] += 1;
                    y[j] += 1;
                }
            }
        }
        //重新遍历，数量同时为1时，则是 ok 的
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1 && x[i] == 1 && y[j] == 1) {
                    result++;
                }
            }
        }
        return result;
    }



    public static int numSpecial2(int[][] mat) {
        int rows = mat.length;
        if (rows == 0) {
            return 0;
        }
        int cols = mat[0].length;
        //效率很慢。。
        List<String> xy = new ArrayList<>();
        Map<Integer, Integer> x = new HashMap<>();
        Map<Integer, Integer> y = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1) {
                    xy.add(i + "," + j);
                    x.put(i, x.getOrDefault(i, 0) + 1);
                    y.put(j, y.getOrDefault(j, 0) + 1);
                }
            }
        }
        if (xy.size() == 0) {
            return 0;
        }
        for (Map.Entry<Integer, Integer> entry : x.entrySet()) {
            if (entry.getValue() > 1) {
                xy.removeIf(i -> i.startsWith(entry.getKey() + ","));
            }
        }
        for (Map.Entry<Integer, Integer> entry : y.entrySet()) {
            if (entry.getValue() > 1) {
                xy.removeIf(i -> i.endsWith("," + entry.getKey()));
            }
        }
        return xy.size();
    }



    public static int numSpecial3(int[][] mat) {
        //求和为1的行、和为1的列，
        //再统计他们的交点是不是1的个数
        int rows = mat.length;
        if (rows == 0) {
            return 0;
        }
        int cols = mat[0].length;
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            int rowSum = 0;
            for (int j = 0; j < cols; j++) {
                rowSum += mat[i][j];
            }
            if (rowSum == 1) {
                x.add(i);
            }
        }

        for (int i = 0; i < cols; i++) {
            int colSum = 0;
            for (int j = 0; j < rows; j++) {
                colSum += mat[j][i];
            }
            if (colSum == 1) {
                y.add(i);
            }
        }

        int res = 0;
        for (int row : x) {
            for (int col : y) {
                res += mat[row][col];
            }
        }
        return res;
    }
}
