package problems.p0901_1000;

import patterns.SlidingWindowPattern;
import patterns.ArrayPattern;
import patterns.HashTablePattern;
import difficulty.Medium;

import java.util.*;

/**
 * You are visiting a farm that has a single row of fruit trees arranged from left to right.
 * The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 *
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 *
 * 1. You only have two baskets, and each basket can only hold a single type of fruit.
 *    There is no limit on the amount of fruit each basket can hold.
 * 2. Starting from any tree of your choice,
 *    you must pick exactly one fruit from every tree (including the start tree) while moving to the right.
 *    The picked fruits must fit in one of your baskets.
 * 3. Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 *    Given the integer array fruits, return the maximum number of fruits you can pick.
 *
 * Example 1:
 * Input: fruits = [1,2,1]
 * Output: 3
 * Explanation: We can pick from all 3 trees.
 *
 * Example 2:
 * Input: fruits = [0,1,2,2]
 * Output: 3
 * Explanation: We can pick from trees [1,2,2].
 * If we had started at the first tree, we would only pick from trees [0,1].
 *
 * Example 3:
 * Input: fruits = [1,2,3,2,2]
 * Output: 4
 * Explanation: We can pick from trees [2,3,2,2].
 * If we had started at the first tree, we would only pick from trees [1,2].
 * <br>
 * <a href="https://leetcode.com/problems/fruit-into-baskets/">904. Fruit Into Baskets</a>
 */
public class _0904_FruitsIntoBasket implements SlidingWindowPattern, ArrayPattern, HashTablePattern, Medium {
    public int practice(int[] fruits) {
        int[][] cache = new int[2][3];
        cache[0][0] = fruits[0];
        cache[0][1] = 0;
        cache[0][2] = 0;
        int max = 0;
        int idx = 1;
        for (; idx < fruits.length; idx++) {
            if (fruits[idx] != cache[0][0]) {
                cache[1][0] = fruits[idx];
                cache[1][1] = idx;
                cache[1][2] = idx;
                max = idx += 1;
                break;
            } else {
                cache[0][2] = idx;
            }
        }
        if (max == 0) return fruits.length;
        int length = max;
        for (; idx < fruits.length; idx++) {
            if (fruits[idx] == cache[0][0]) {
                cache[0][2] = idx;
                length += 1;
            } else if (fruits[idx] == cache[1][0]) {
                cache[1][2] = idx;
                length += 1;
            } else {
                if (fruits[idx - 1] == cache[0][0]) {
                    cache[0][1] = cache[1][2] + 1;
                    cache[1][0] = fruits[idx];
                    cache[1][1] = idx;
                    cache[1][2] = idx;
                } else {
                    cache[1][1] = cache[0][2] + 1;
                    cache[0][0] = fruits[idx];
                    cache[0][1] = idx;
                    cache[0][2] = idx;
                }
                length = idx - java.lang.Math.min(cache[0][1], cache[1][1]) + 1;
            }
            if (length > max) max = length;
        }
        return max;
    }

    /**
     * <a href="https://www.youtube.com/watch?v=wey1yZdkUNE&list=PL7g1jYj15RUOjoeZAJsWjwV8XUo9r0hwc&index=3">source</a>
     */
    public int slidingWindow(int[] fruits) {
        Set<Integer> buckets = new HashSet<>();
        int max = 0;
        int start = 0;
        for (int end = 0; end < fruits.length; end++) {
            if (buckets.size() < 2 && !buckets.contains(fruits[end])) {
                buckets.add(fruits[end]);
            } else if (!buckets.contains(fruits[end])) {
                buckets = new HashSet<>();
                buckets.add(fruits[end]);
                buckets.add(fruits[end - 1]);
                start = end - 1;
                while (start > 0 && fruits[start] == fruits[start - 1]) {
                    start--;
                }
            }
            if(end - start + 1 > max) max = end - start + 1;
        }
        return max;
    }

    public static void main(java.lang.String[] args) {
        int[] fruits = {0, 0, 1, 1};
        System.out.println(new _0904_FruitsIntoBasket().practice(fruits));
    }
}
