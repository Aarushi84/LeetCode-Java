class Solution {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // [value, original index]
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        // Sort by value
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        // DSU
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Connect consecutive values if difference <= limit
        for (int i = 1; i < n; i++) {

            if ((long) arr[i][0] - arr[i - 1][0] <= limit) {
                union(parent, arr[i][1], arr[i - 1][1]);
            }
        }

        // Store indices belonging to each group
        Map<Integer, List<Integer>> groups = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = find(parent, i);

            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        int[] result = nums.clone();

        // Process each group
        for (List<Integer> indices : groups.values()) {

            // Sort original indices
            Collections.sort(indices);

            // Get values belonging to this group
            List<Integer> values = new ArrayList<>();

            for (int index : indices) {
                values.add(nums[index]);
            }

            // Smallest values go to smallest indices
            Collections.sort(values);

            for (int i = 0; i < indices.size(); i++) {
                result[indices.get(i)] = values.get(i);
            }
        }

        return result;
    }

    private int find(int[] parent, int x) {

        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }

        return parent[x];
    }

    private void union(int[] parent, int a, int b) {

        int rootA = find(parent, a);
        int rootB = find(parent, b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}