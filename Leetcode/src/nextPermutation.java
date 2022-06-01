class nextPermutation {
    public static void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int right = nums.length - 1;
        if (nums[right] > nums[right - 1]) {
            swap(nums, right, right- 1);
        } else {
            while (right > 0 && nums[right] <= nums[right - 1]) {
                right--;
            }
            right--;
            if (right == 0) {
                reverse(nums, 0);
            } else {
                int anotherRightPtr = nums.length - 1;
                while (anotherRightPtr > 0 && nums[anotherRightPtr] <= nums[right]) {
                    anotherRightPtr--;
                }
                swap(nums, right, anotherRightPtr);
                reverse(nums, right + 1);
            }
        }
    }
    public static void reverse(int[] nums, int start) {    // sort in non descending order
        int j = nums.length - 1;
        while (start < j) {
            int temp = nums[start];
            nums[start] = nums[j];
            nums[j] = temp;
            start++;
            j--;

        }
    }
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums  = {1, 3, 2};
        nextPermutation(nums);
        System.out.println(nums);

    }
}