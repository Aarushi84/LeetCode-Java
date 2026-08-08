class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Create graph
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build directed graph
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prerequisite = pair[1];

            graph.get(prerequisite).add(course);
        }

        // 0 = not visited
        // 1 = currently visiting
        // 2 = completely finished
        int[] state = new int[numCourses];

        // Check every course
        for (int i = 0; i < numCourses; i++) {

            if (state[i] == 0) {
                if (dfs(i, graph, state)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int course,
                        List<List<Integer>> graph,
                        int[] state) {

        // Currently inside this DFS path
        state[course] = 1;

        for (int next : graph.get(course)) {

            // Found a node already in current DFS path
            if (state[next] == 1) {
                return true;
            }

            // Not visited yet
            if (state[next] == 0) {

                if (dfs(next, graph, state)) {
                    return true;
                }
            }
        }

        // Finished exploring this course
        state[course] = 2;

        return false;
    }
}