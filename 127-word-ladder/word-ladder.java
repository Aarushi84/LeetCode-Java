class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> words = new HashSet<>(wordList);

        if (!words.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                String word = queue.poll();

                if (word.equals(endWord)) {
                    return level;
                }

                for (int i = 0; i < word.length(); i++) {

                    char[] chars = word.toCharArray();

                    for (char ch = 'a'; ch <= 'z'; ch++) {

                        chars[i] = ch;

                        String newWord = new String(chars);

                        if (words.contains(newWord) &&
                            !visited.contains(newWord)) {

                            queue.offer(newWord);
                            visited.add(newWord);
                        }
                    }
                }
            }

            level++;
        }

        return 0;
    }
}