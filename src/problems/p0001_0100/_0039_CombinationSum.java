package problems.p0001_0100;

import patterns.ArrayPattern;
import patterns.BacktrackingPattern;
import difficulty.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen
 * numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen
 * numbers is different.
 *
 * The test cases are generated such that the number of unique combinations that
 * sum up to target is less than 150 combinations for the given input.
 *
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 *
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * Example 3:
 * Input: candidates = [2], target = 1
 * Output: []
 *
 * Constraints:
 * - 1 <= candidates.length <= 30
 * - 2 <= candidates[i] <= 40
 * - All elements of candidates are distinct.
 * - 1 <= target <= 40
 *
 * <a href="https://leetcode.com/problems/combination-sum/">39. Combination Sum</a>
 */
public class _0039_CombinationSum implements ArrayPattern, BacktrackingPattern, Medium {

    /**
     * Time Complexity: O(n ^ (T / M + 1))
     * Space Complexity: O(K * (T / M))
     *
     * n = 候選數字個數,T = target,M = 最小的候選值,K = 產生的原始組合數(指數級)。
     * 與 backtrackBottomUp 同為指數級,但更差:enumerateAllOrderings 沒有起始索引,
     * 會把同一組合的各種排列(順序不同)都產生出來,共 K 個、每個長度最多 T / M,
     * 之後再逐一 Collections.sort 並丟進 HashSet 去除重複。
     * 額外空間需一次存下全部 K 個原始組合,故為指數級,比 backtrackBottomUp 更耗記憶體。
     */
    public List<List<Integer>> enumerateThenDeduplicate(int[] candidates, int target) {
        List<List<Integer>> all = enumerateAllOrderings(candidates, target);
        Set<List<Integer>> result = new HashSet<>();
        for (List<Integer> find : all) {
            Collections.sort(find);
            result.add(find);
        }
        return new ArrayList<>(result);
    }

    private List<List<Integer>> enumerateAllOrderings(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = candidates.length - 1; i >= 0; i--) {
            if (candidates[i] > target) {
                continue;
            }
            if (target % candidates[i] == 0) {
                List<Integer> find = new ArrayList<>();
                for (int j = 0; j < target / candidates[i]; j++) {
                    find.add(candidates[i]);
                }
                result.add(find);
            }
            int next = target - candidates[i];
            List<List<Integer>> nextFind = enumerateAllOrderings(candidates, next);
            if (!nextFind.isEmpty()) {
                for (List<Integer> find : nextFind) {
                    find.add(candidates[i]);
                    result.add(find);
                }
            }
        }
        return result;
    }

    /**
     * Time Complexity: O(n ^ (T / M + 1))
     * Space Complexity: O(T / M)
     *
     * n = 候選數字個數,T = target,M = 最小的候選值。
     * 遞迴展開成一棵樹:每層最多分支 n 個候選,深度最多 T / M
     * (每次遞迴至少扣掉最小值 M),故節點數約 n ^ (T / M),
     * 再乘上每個節點複製組合的成本,得到指數級時間。
     * 空間只計遞迴堆疊深度 T / M(不含輸出結果本身)。
     */
    public List<List<Integer>> backtrackBottomUp(int[] candidates, int target) {
        Arrays.sort(candidates);
        return backtrackBottomUp(candidates, target, 0);
    }

    private List<List<Integer>> backtrackBottomUp(int[] candidates, int target, int idx) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = idx; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            if (target == candidates[i]) {
                List<Integer> find = new ArrayList<>();
                find.add(candidates[i]);
                result.add(find);
            }
            int next = target - candidates[i];
            List<List<Integer>> nextFind = backtrackBottomUp(candidates, next, i);
            if (!nextFind.isEmpty()) {
                for (List<Integer> find : nextFind) {
                    find.add(candidates[i]);
                    result.add(find);
                }
            }
        }
        return result;
    }

    /**
     * Time Complexity: O(n ^ (T / M + 1))
     * Space Complexity: O(T / M)
     *
     * n = 候選數字個數,T = target,M = 最小的候選值。
     * 遞迴展開成一棵樹:每層最多分支 n 個候選,深度最多 T / M
     * (每次遞迴至少扣掉最小值 M),故節點數約 n ^ (T / M),
     * 再乘上每個節點複製組合的成本,得到指數級時間。
     * 空間只計遞迴堆疊深度 T / M(不含輸出結果本身)。
     */
    public List<List<Integer>> backtrackInPlace(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        backtrackInPlace(candidates, target, 0, result, current);
        return result;
    }

    private void backtrackInPlace(int[] candidates, int target, int idx, List<List<Integer>> result, List<Integer> current) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (candidates[i] > target) {
                return;
            }
            current.addLast(candidates[i]);
            backtrackInPlace(candidates, target - candidates[i], i, result, current);
            current.removeLast();
        }
    }
}
