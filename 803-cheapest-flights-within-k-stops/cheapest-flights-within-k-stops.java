class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        // dist[i] = cheapest price to reach city i
        int[] dist = new int[n];

        // Initially, every city is unreachable
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        // Cost to reach source = 0
        dist[src] = 0;

        // k stops means k + 1 flights
        for (int i = 0; i <= k; i++) {

            // Copy previous distances
            int[] temp = dist.clone();

            // Check every flight
            for (int[] flight : flights) {

                int from = flight[0];
                int to = flight[1];
                int price = flight[2];

                // If 'from' is reachable
                if (dist[from] != Integer.MAX_VALUE) {

                    // Take this flight and see if it is cheaper
                    temp[to] = Math.min(
                        temp[to],
                        dist[from] + price
                    );
                }
            }

            // Move to the next level
            dist = temp;
        }

        // Destination unreachable
        if (dist[dst] == Integer.MAX_VALUE) {
            return -1;
        }

        return dist[dst];
    }
}