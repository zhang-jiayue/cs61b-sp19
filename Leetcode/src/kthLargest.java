import java.util.PriorityQueue;
class KthLargest{
    public static class Largest {
        // create a dynamic array, which uses doubling as its resizing strategy
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int k;
        public Largest(int k, int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                heap.add(nums[i]);
            }
            this.k = k;
        }
        public int add(int val) {
            this.heap.add(val);
            int temp = this.heap.size();
            for (int i = 0; i < temp - k; i++) {
                if (temp != 1) {
                    this.heap.poll();
                }
            }
            int resu = this.heap.peek();
            return resu;
        }
    }

    public static void main(String[] args) {
        int[] nums  = {};
        Largest k = new Largest(1, nums);
        int resu = k.add(-3);
        System.out.println(resu);

    }
}
