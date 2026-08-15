class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<Integer> result = new ArrayList<>();

        // Special case
        if (n == 1) {
            result.add(0);
            return result;
        }

        // Build graph
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Degree of every node
        int[] degree = new int[n];

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);

            degree[u]++;
            degree[v]++;
        }

        // Put all leaves into queue
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {

            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        // Remove leaves layer by layer
        while (n > 2) {

            int size = queue.size();

            n -= size;

            for (int i = 0; i < size; i++) {

                int leaf = queue.poll();

                for (int neighbor : graph.get(leaf)) {

                    degree[neighbor]--;

                    if (degree[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        // Remaining nodes are the centers
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }

        return result;
    }
}