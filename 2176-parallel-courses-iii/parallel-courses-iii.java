class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {

        // Build graph
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Indegree of every course
        int[] indegree = new int[n];

        for (int[] relation : relations) {

            int prev = relation[0] - 1;
            int next = relation[1] - 1;

            graph.get(prev).add(next);
            indegree[next]++;
        }

        // Earliest finishing time of every course
        int[] finishTime = new int[n];

        Queue<Integer> queue = new LinkedList<>();

        // Courses with no prerequisites
        for (int i = 0; i < n; i++) {

            if (indegree[i] == 0) {

                queue.offer(i);

                finishTime[i] = time[i];
            }
        }

        int answer = 0;

        // Kahn's algorithm
        while (!queue.isEmpty()) {

            int current = queue.poll();

            answer = Math.max(answer, finishTime[current]);

            for (int next : graph.get(current)) {

                // DP transition
                finishTime[next] = Math.max(
                    finishTime[next],
                    finishTime[current] + time[next]
                );

                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return answer;
    }
}