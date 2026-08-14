class Solution {
    public List<Integer> findSmallestSetOfVertices(
            int n, List<List<Integer>> edges) {

        int[] indegree = new int[n];

        // Calculate indegree
        for (List<Integer> edge : edges) {

            int from = edge.get(0);
            int to = edge.get(1);

            indegree[to]++;
        }

        // Find all nodes with indegree 0
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (indegree[i] == 0) {
                result.add(i);
            }
        }

        return result;
    }
}