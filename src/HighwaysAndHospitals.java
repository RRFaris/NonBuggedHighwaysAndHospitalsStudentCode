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
        if (hospitalCost < highwayCost) {
            return ((long) n * hospitalCost);
        }

        int[] roots = new int[n + 1];

        for (int i = 0; i < cities.length; i++) {
            int a = cities[i][0];
            int b = cities[i][1];

            int rootA = a;
            while (roots[rootA] != 0) {
                rootA = roots[rootA];
            }

            while (roots[a] != 0) {
                int temp = roots[a];
                roots[a] = rootA;
                a = temp;
            }

            int rootB = b;
            while (roots[rootB] != 0) {
                rootB = roots[rootB];
            }

            while (roots[b] != 0) {
                int temp = roots[b];
                roots[b] = rootB;
                b = temp;
            }

            if (rootA != rootB) {
                roots[rootB] = rootA;
            }
        }

        int num_subtrees = 0;
        for (int i = 1; i <= n; i++) {
            if (roots[i] == 0) {
                num_subtrees++;
            }
        }

        return (long) num_subtrees * hospitalCost + (long) (n - num_subtrees) * highwayCost;
    }
}
