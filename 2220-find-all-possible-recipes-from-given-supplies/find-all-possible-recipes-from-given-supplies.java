class Solution {
    public List<String> findAllRecipes(
            String[] recipes,
            List<List<String>> ingredients,
            String[] supplies) {

        int n = recipes.length;

        // recipe name -> index
        Map<String, Integer> recipeIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            recipeIndex.put(recipes[i], i);
        }

        // ingredient -> recipes that depend on it
        Map<String, List<Integer>> dependents = new HashMap<>();

        // Number of ingredients needed for each recipe
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {

            indegree[i] = ingredients.get(i).size();

            for (String ingredient : ingredients.get(i)) {

                dependents
                    .computeIfAbsent(ingredient, k -> new ArrayList<>())
                    .add(i);
            }
        }

        Queue<String> queue = new LinkedList<>();

        // Initially available ingredients
        for (String supply : supplies) {
            queue.offer(supply);
        }

        List<String> result = new ArrayList<>();

        while (!queue.isEmpty()) {

            String ingredient = queue.poll();

            if (!dependents.containsKey(ingredient)) {
                continue;
            }

            for (int recipeIndexValue : dependents.get(ingredient)) {

                indegree[recipeIndexValue]--;

                if (indegree[recipeIndexValue] == 0) {

                    String recipe = recipes[recipeIndexValue];

                    result.add(recipe);

                    queue.offer(recipe);
                }
            }
        }

        return result;
    }
}