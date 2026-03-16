package problems.p0201_0300;

import patterns.BreadthFirstSearchPattern;
import patterns.GraphPattern;
import patterns.DepthFirstSearchPattern;
import difficulty.Medium;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to
 * numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi]
 * indicates that
 * you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to
 * first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 * <br>
 * <a href="https://leetcode.com/problems/course-schedule/">207. Course
 * Schedule</a>
 */
public class _0207_CourseSchedule implements BreadthFirstSearchPattern, GraphPattern, DepthFirstSearchPattern, Medium {
    /**
     * Time Complexity: O(V+E)
     * Space Complexity: O(V+E)
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] incomingEdges = new int[numCourses];
        List<Integer>[] goCourses = new List[numCourses];
        for (int i = 0; i < goCourses.length; i++) {
            goCourses[i] = new LinkedList<>();
        }
        for (int[] pair : prerequisites) {
            incomingEdges[pair[0]]++;
            goCourses[pair[1]].add(pair[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < incomingEdges.length; i++) {
            if (incomingEdges[i] == 0) {
                queue.add(i);
            }
        }
        int edgeCnt = prerequisites.length;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int goCrs : goCourses[cur]) {
                edgeCnt--;
                if (--incomingEdges[goCrs] == 0)
                    queue.add(goCrs);
            }
        }
        return edgeCnt == 0;
    }

    /**
     * Time Complexity: O(V+E)
     * Space Complexity: O(V+E)
     */
    public boolean canFinish_dfs(int numCourses, int[][] prerequisites) {
        int[] state = new int[numCourses]; // 0 - unvisited, 1: in-progress, 2: completed
        LinkedList<Integer>[] preMap = new LinkedList[numCourses];
        for (int[] prerequisity : prerequisites) {
            if (preMap[prerequisity[0]] == null) {
                preMap[prerequisity[0]] = new LinkedList<>();
            }
            preMap[prerequisity[0]].add(prerequisity[1]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, preMap, state)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int course, LinkedList<Integer>[] preMap, int[] state) {
        if (state[course] == 2) {
            return true;
        }
        if (state[course] == 1) {
            return false;
        }
        state[course] = 1;
        LinkedList<Integer> preList = preMap[course];
        if (preList != null) {
            for (int i : preList) {
                if (!dfs(i, preMap, state)) {
                    return false;
                }
            }
        }
        state[course] = 2;
        return true;
    }
}
