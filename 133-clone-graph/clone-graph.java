/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {

    public Node cloneGraph(Node node) {

        // Empty graph
        if (node == null) {
            return null;
        }

        // Original node -> cloned node
        Map<Node, Node> map = new HashMap<>();

        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {

        // Already cloned
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Create copy of current node
        Node clone = new Node(node.val);

        // Store it immediately
        map.put(node, clone);

        // Clone all neighbors
        for (Node neighbor : node.neighbors) {

            clone.neighbors.add(dfs(neighbor, map));
        }

        return clone;
    }
}