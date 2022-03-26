import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public static int maxProfit(int[] prices) {
        int[] differenceArr = new int[prices.length - 1];
        int j = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            differenceArr[j] = prices[i + 1] - prices[i];
            j += 1;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
        int sum = 0;
        for (int i = 0; i < differenceArr.length; i++) {
            if (differenceArr[i] > 0) {
                sum += differenceArr[i];
            } else {
                maxHeap.add(sum);
                sum = 0;
            }
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums  = {7, 1, 5, 3, 6, 4};
        int resu = maxProfit(nums);
        System.out.println(resu);

    }
}
