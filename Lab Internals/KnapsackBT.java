public class KnapsackBT {
    static int maxProfit = 0;

    static void knapsack(int i, int profit, int weight, int[] val, int[] wt, int n, int W) {
        if (weight <= W && profit > maxProfit)
            maxProfit = profit;
        if (i == n)
            return;
        knapsack(i + 1, profit + val[i], weight + wt[i], val, wt, n, W);
        knapsack(i + 1, profit, weight, val, wt, n, W);
    }

    public static void main(String[] args) {
        int val[] = { 60, 100, 120 };
        int wt[] = { 10, 20, 30 };
        int W = 50;
        int n = val.length;

        knapsack(0, 0, 0, val, wt, n, W);
        System.out.println("Maximum value (Backtracking): " + maxProfit);
    }
}
