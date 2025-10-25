package problems.p0201_0300;

import patterns.BreadthFirstSearchPattern;
import patterns.GraphPattern;
import patterns.DepthFirstSearchPattern;
import difficulty.Medium;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 * <br>
 * <a href="https://leetcode.com/problems/course-schedule/">207. Course Schedule</a>
 */
public class _0207_CourseSchedule implements BreadthFirstSearchPattern, GraphPattern, DepthFirstSearchPattern, Medium {
    /**
     * <a href="https://leetcode.com/problems/course-schedule/solutions/58516/easy-bfs-topological-sort-java/">source</a>
     */
    public boolean canFinish(int numCourses, int[][] prerequisites){
        int[] incomingEdges = new int[numCourses];
        List<Integer>[] goCourses = new List[numCourses];
        for(int i = 0; i < goCourses.length; i++){
            goCourses[i] = new LinkedList<>();
        }
        for(int[] pair: prerequisites){
            incomingEdges[pair[0]]++;
            goCourses[pair[1]].add(pair[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < incomingEdges.length; i++){
            if(incomingEdges[i] == 0){
                queue.add(i);
            }
        }
        int edgeCnt = prerequisites.length;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int goCrs : goCourses[cur]){
                edgeCnt--;
                if(--incomingEdges[goCrs] == 0)
                    queue.add(goCrs);
            }
        }
        return edgeCnt == 0;
    }

    public static void main(java.lang.String[] args) {
        new _0207_CourseSchedule().canFinish(2, new int[][] {{1, 3}, {0, 1}, {3, 1}, {3, 2}});
    }
}
