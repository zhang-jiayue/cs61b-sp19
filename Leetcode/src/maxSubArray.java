class maxSubArray {
    public static int maxSubArray(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        else if (nums.length == 1) {
            return nums[0];
        } else {
            int sum = nums[0];
            int max = sum;
            for (int i = 1; i < nums.length; i++) {
                sum = sum += nums[i];
                max = Math.max(sum, max);
                if (sum < 0) {
                    sum = 0;
                }
            }
            return max;
        }
    }


    public static void main(String[] args) {
        int[] nums  = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int resu = maxSubArray(nums);
        System.out.println(resu);

    }
}
