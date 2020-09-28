package com.javbus.server;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.reflection.ArrayUtil;

import java.util.*;

public class ListTest {

	public static void main(String[] args) {
		int[][] arr = {{9, 9, 4},{6, 6, 8}, {2, 1, 1}};
		String[] stra = {"1", "2"};
		System.out.println(++arr[1][2]);
		List list = arrayToList(arr);
		System.out.println(JSON.toJSONString(list));
		System.out.println(JSON.toJSONString(list.toArray()));  // list转数组
		List<String> list2 = new ArrayList<String>(Arrays.asList(stra));
		Boolean[] a = new Boolean[5];
		test(5);
	}

	/**
	 * 数组转List
	 * @param a
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> arrayToList(T... a) {
		//但该方法存在一定的弊端，返回的list是Arrays里面的一个静态内部类，该类并未实现add,remove方法，因此在使用时存在局限性
		List result = Arrays.asList(a);
		return result;
	}

	/**
	 * v你有一个 n x 3 的网格图 grid ，你需要用 红，黄，绿 三种颜色之一给每一个格子上色，且确保相邻格子颜色不同（也就是有相同水平边或者垂直边的格子颜色不同）。
	 *
	 * 给你网格图的行数 n 。
	 *
	 * 请你返回给 grid 涂色的方案数。由于答案可能会非常大，请你返回答案对 10^9 + 7 取余的结果。
	 *
	 *
	 *
	 * 示例 1：
	 *
	 * 输入：n = 1
	 * 输出：12
	 * 解释：总共有 12 种可行的方法：
	 *
	 * 示例 2：
	 *
	 * 输入：n = 2
	 * 输出：54
	 * 示例 3：
	 *
	 * 输入：n = 3
	 * 输出：246
	 * 示例 4：
	 *
	 * 输入：n = 7
	 * 输出：106494
	 * 示例 5：
	 *
	 * 输入：n = 5000
	 * 输出：30228214
	 *
	 *
	 * 提示：
	 *
	 * n == grid.length
	 * grid[i].length == 3
	 * 1 <= n <= 5000
	 */

	public static void test(int n) {
		int[][] a = new int[n][3];
		for (int c = 0; c < n; c++) {
			for (int i = 0; i < a[c].length; i++) {
				for (int j = 0; j < a[c].length; j++) {
					if (i != j) {
//						if ()
//						int temp = a[c][i] == 1 ? a[c][j+1] == 2 ? 3 : 2 : 1;
					}
				}
			}
		}


	}
}
