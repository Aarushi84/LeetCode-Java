class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // Create graph
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build graph
        for (int[] pair : prerequisites) {

            int course = pair[0];
            int prerequisite = pair[1];

            graph.get(prerequisite).add(course);
        }

        // 0 = unvisited
        // 1 = currently visiting
        // 2 = completely finished
        int[] state = new int[numCourses];

        // Store course order
        int[] order = new int[numCourses];

        // Position where we put the course
        int[] index = new int[]{numCourses - 1};

        // DFS every course
        for (int i = 0; i < numCourses; i++) {

            if (state[i] == 0) {

                if (dfs(i, graph, state, order, index)) {
                    return new int[0];
                }
            }
        }

        return order;
    }

    private boolean dfs(int course,
                        List<List<Integer>> graph,
                        int[] state,
                        int[] order,
                        int[] index) {

        // Currently visiting
        state[course] = 1;

        for (int next : graph.get(course)) {

            // Cycle found
            if (state[next] == 1) {
                return true;
            }

            // Visit unvisited course
            if (state[next] == 0) {

                if (dfs(next, graph, state, order, index)) {
                    return true;
                }
            }
        }

        // Completely finished
        state[course] = 2;

        // Put course into answer
        order[index[0]] = course;
        index[0]--;

        return false;
    }
}