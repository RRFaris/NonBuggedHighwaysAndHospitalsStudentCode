/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Ryan Faris
 *
 */

public class HighwaysAndHospitals {
    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {
        // First check if it is an easy case
        if (hospitalCost < highwayCost) {
            return ((long) n * hospitalCost);
        }

        // Declare and initialize array that stores the root of every node, as well as how many nodes each node is
        // connected to
        int[] roots = new int[n + 1];

        // Main loop
        for (int i = 0; i < cities.length; i++) {
            int a = cities[i][0];
            int b = cities[i][1];

            // Follow root pointers until you get to the root node of a
            int rootA = a;
            while (roots[rootA] > 0) {
                rootA = roots[rootA];
            }

            // Path compression: make all nodes point to root node
            while (roots[a] > 0) {
                int temp = roots[a];
                roots[a] = rootA;
                a = temp;
            }

            // Follow root pointers until you get to the root node of b
            int rootB = b;
            while (roots[rootB] > 0) {
                rootB = roots[rootB];
            }

            // Path compression: make all nodes point to root node
            while (roots[b] > 0) {
                int temp = roots[b];
                roots[b] = rootB;
                b = temp;
            }

            // Union step: attach the smaller subtree to the larger subtree
            if (rootA != rootB) {
                if (roots[rootA] > roots[rootB]) {
                    // Update size and roots
                    roots[rootB] += roots[rootA] - 1;
                    roots[rootA] = rootB;
                }
                else {
                    // Update size and roots
                    roots[rootA] += roots[rootB] - 1;
                    roots[rootB] = rootA;
                }
            }
        }

        // Calculate the number of subtrees by looking for nodes with a root of themselves
        int num_subtrees = 0;
        for (int i = 1; i <= n; i++) {
            if (roots[i] <= 0) {
                num_subtrees++;
            }
        }

        // Calculate cost
        return (long) num_subtrees * hospitalCost + (long) (n - num_subtrees) * highwayCost;
    }
}
