import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        // Create adjacency list
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Store edges
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];

            graph.get(u).add(new int[]{v, w});
        }

        // Distance from k to every node
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[k] = 0;

        // {distance, node}
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> a[0] - b[0]);

        pq.offer(new int[]{0, k});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int currentDistance = current[0];
            int currentNode = current[1];

            // Check all neighbors
            for (int[] edge : graph.get(currentNode)) {

                int neighbor = edge[0];
                int weight = edge[1];

                int newDistance = currentDistance + weight;

                if (newDistance < dist[neighbor]) {

                    dist[neighbor] = newDistance;

                    pq.offer(new int[]{newDistance, neighbor});
                }
            }
        }

        // Find the time when the last node receives signal
        int answer = 0;

        for (int i = 1; i <= n; i++) {

            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }

            answer = Math.max(answer, dist[i]);
        }

        return answer;
    }
}