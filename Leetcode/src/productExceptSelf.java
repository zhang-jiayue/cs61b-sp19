class productExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        int[] resu = new int[nums.length];
        helper(resu, 0, nums.length-1, nums);
        return resu;
    }

    public static void helper(int[] resu, int start, int to, int[] nums) {
        int productLeft = 1;
        int mid = (start + to) / 2;
        if ((to - start) == 1) {
            resu[start] *= nums[to];
            resu[to] *= nums[start];
            return;
        }
        for (int i = start; i <= mid; i++) {
            productLeft *= nums[i];
        }
        int productRight = 1;
        for (int i = mid+1; i <= to; i++) {
            resu[i] = productLeft;
            productRight *= nums[i];
        }
        for (int i = start; i <= mid; i++) {
            if ((start+to) % 2 == 0) {
                resu[i] *= nums[nums.length / 2];
            }
            resu[i] = productRight;
        }
        if ((start+to) % 2 == 0) {
            resu[mid+1] *= productLeft;
            helper(resu, start, mid, nums);
            helper(resu, mid + 2, to, nums);
        } else {
            helper(resu, start, mid, nums);
            helper(resu, mid+1, to, nums);
        }

    }

    public static void main(String[] args) {
        int[] input = {-1,1,-3,3,1,2,3,4};
        int[] output = productExceptSelf(input);
    }
}