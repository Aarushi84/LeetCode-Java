class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        int INF = 1000000000;
        int[][] dist = new int[n][n];

        // Initialize
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // Add edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            dist[u][v] = weight;
            dist[v][u] = weight;
        }

        // Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (dist[i][k] != INF &&
                        dist[k][j] != INF) {

                        dist[i][j] = Math.min(
                            dist[i][j],
                            dist[i][k] + dist[k][j]
                        );
                    }
                }
            }
        }

        // Find city with minimum reachable cities
        int answer = -1;
        int minCount = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            int count = 0;

            for (int j = 0; j < n; j++) {

                if (i != j &&
                    dist[i][j] <= distanceThreshold) {
                    count++;
                }
            }

            if (count <= minCount) {
                minCount = count;
                answer = i;
            }
        }

        return answer;
    }
}