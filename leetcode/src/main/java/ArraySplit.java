/**
 *给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 *
 * 注意:
 * 数组长度 n 满足以下条件:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * 示例:
 *
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * 输出:
 * 18
 *
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 *
 * 解题思路：
 * 由题意可知：子数组的最大值是有范围的，即在区间 [max(nums),sum(nums)][max(nums),sum(nums)] 之中。
 * 令 l=max(nums)，h=sum(nums)l=max(nums)，h=sum(nums)，mid=(l+h)/2mid=(l+h)/2，计算数组和最大值不大于mid对应的子数组个数 cnt(这个是关键！)
 * 如果 cnt>m，说明划分的子数组多了，即我们找到的 mid 偏小，故 l=mid+1l=mid+1；
 * 否则，说明划分的子数组少了，即 mid 偏大(或者正好就是目标值)，故 h=midh=mid。
 *
 * 作者：coder233
 * 链接：https://leetcode-cn.com/problems/split-array-largest-sum/solution/er-fen-cha-zhao-by-coder233-2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class ArraySplit {
    public static int splitArray(int[] nums, int m) {
        int max = 0;
        for (int a : nums) {
            if (a > max) {
                max = a;
            }
        }
        long sum = 0;
        for (int a : nums) {
            sum += a;
        }

        while (max < sum) {
            long mid = (max + sum) / 2;
            int count = 1;
            int sub = 0;
            for (int a : nums) {
                sub += a;
                if (sub > mid) {
                    sub = a;
                    count ++;
                }
            }
            if (count > m) {
                max += 1;
            } else {
                sum = mid;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {7,2,5,10,8,6};
        int m = 3;
        System.out.println(splitArray(nums, m));
    }
}
