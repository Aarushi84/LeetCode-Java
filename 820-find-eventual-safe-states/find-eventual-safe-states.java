class Solution {

    public List<Integer> eventualSafeNodes(int[][] graph) {

        int n = graph.length;

        // Reverse graph
        List<List<Integer>> reverse = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            reverse.add(new ArrayList<>());
        }

        // Outdegree of every node
        int[] outdegree = new int[n];

        // Build reverse graph
        for (int node = 0; node < n; node++) {

            outdegree[node] = graph[node].length;

            for (int neighbor : graph[node]) {

                reverse.get(neighbor).add(node);
            }
        }

        // Queue contains terminal nodes
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {

            if (outdegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Process safe nodes
        boolean[] safe = new boolean[n];

        while (!queue.isEmpty()) {

            int node = queue.poll();

            safe[node] = true;

            // Find nodes that point to this node
            for (int prev : reverse.get(node)) {

                outdegree[prev]--;

                if (outdegree[prev] == 0) {
                    queue.offer(prev);
                }
            }
        }

        // Create answer in ascending order
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (safe[i]) {
                result.add(i);
            }
        }

        return result;
    }
}