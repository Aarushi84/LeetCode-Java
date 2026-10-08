class Solution {
    public String removeOuterParentheses(String s) {

        StringBuilder result = new StringBuilder();
        int balance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Don't add '(' if it is the outermost opening bracket
                if (balance > 0) {
                    result.append(ch);
                }

                balance++;
            } 
            else {
                balance--;

                // Don't add ')' if it is the outermost closing bracket
                if (balance > 0) {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }
}