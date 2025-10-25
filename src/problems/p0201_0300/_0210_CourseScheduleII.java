package problems.p0201_0300;

import patterns.BreadthFirstSearchPattern;
import patterns.GraphPattern;
import patterns.DepthFirstSearchPattern;
import difficulty.Medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses.
 * If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0.
 * So the correct course order is [0,1].
 *
 * Example 2:
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take.
 * To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * <br>
 * <a href="https://leetcode.com/problems/course-schedule-ii/">210. Course Schedule II</a>
 */
public class _0210_CourseScheduleII implements BreadthFirstSearchPattern, GraphPattern, DepthFirstSearchPattern, Medium {
    public int[] practice(int numCourses, int[][] prerequisites) {
        int[] incomingEdges = new int[numCourses];
        LinkedList<Integer>[] goCourse = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            goCourse[i] = new LinkedList<>();
        }
        for (int[] pair : prerequisites) {
            incomingEdges[pair[0]] += 1;
            goCourse[pair[1]].add(pair[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[numCourses];
        int idx = 0;
        for (int i = 0; i < incomingEdges.length; i++) {
            if (incomingEdges[i] == 0) {
                queue.add(i);
                result[idx++] = i;
            }
        }
        int edgeCount = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            for (int goCrs : goCourse[course]) {
                incomingEdges[goCrs]--;
                if (incomingEdges[goCrs] == 0) {
                    queue.add(goCrs);
                    result[idx++] = goCrs;
                }
                edgeCount += 1;
            }
        }
        if (edgeCount != prerequisites.length) return new int[] {};
        return result;
    }
}
