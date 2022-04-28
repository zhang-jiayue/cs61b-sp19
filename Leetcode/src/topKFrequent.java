import java.util.*;

public class topKFrequent {
    public static int[] topKFrequent(int[] nums, int k) {

        int[] resu = new int[k];
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((a,b)->(b.getValue() - a.getValue()));
        Arrays.sort(nums);
        int currentNumber = nums[0];
        int i = 0;
        int frequency = 0;
        while (i < nums.length) {
            while (i < nums.length && nums[i] == currentNumber) {
                frequency ++;
                i++;
            }
            if (i >= nums.length) {
                break;
            }
            currentNumber = nums[i];
            frequency = 0;
        }
        for (int j = 0; j < k; j++) {
            resu[j] = heap.poll().getValue();
        }
        return resu;
    }

    public static void main(String[] args) {
        int[] nums  = {5,2,5,3,5,3,1,1,3};
        int [] resu = topKFrequent(nums, 2);
        System.out.println(resu);

    }
}
