import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {

        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String current = queue.poll();

                if (isValid(current)) {
                    result.add(current);
                    found = true;
                }

                // If we already found valid strings at this level,
                // don't generate strings with more removals.
                if (found) {
                    continue;
                }

                // Remove one parenthesis at every possible position
                for (int j = 0; j < current.length(); j++) {

                    if (current.charAt(j) != '(' &&
                        current.charAt(j) != ')') {
                        continue;
                    }

                    String next =
                        current.substring(0, j) +
                        current.substring(j + 1);

                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }

            // We found all valid strings requiring minimum removals
            if (found) {
                break;
            }
        }

        return result;
    }

    private boolean isValid(String s) {

        int balance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                balance++;
            } 
            else if (ch == ')') {
                balance--;

                // More closing parentheses than opening
                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}