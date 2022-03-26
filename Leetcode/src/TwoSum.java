import java.util.Arrays;

class TwoSum {
    /*
     * O(nlogn) solution.
     */
    public static int [] twoSum(int [] nums, int target) {
        int [] resu = new int[2];
        int [] sortedIndex = new int[2];
        // Fisrt sort the input integer list.
        int [] originalArray = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            int newTarget = target - nums[i];
            int index = Arrays.binarySearch(nums, i + 1, nums.length, newTarget);
            if (index > i) {
                sortedIndex[0] = i;
                sortedIndex[1] = index;
                break;
            }
        }
        for (int i = 0; i < originalArray.length; i++) {
            if(originalArray[i] == nums[sortedIndex[0]] || originalArray[i] == nums[sortedIndex[1]]) {
                resu[0] = i;
                break;
            }
        }
        for (int i = resu[0] + 1; i < originalArray.length; i++) {
            if(originalArray[i] == nums[sortedIndex[0]] || originalArray[i] == nums[sortedIndex[1]]) {
                resu[1] = i;
                break;
            }
        }
        Arrays.sort(resu);
        return resu;
    }

    public static void main(String[] args) {
        int[] nums  = {-1, -2, -3, -4, -5};
        int[] resu = twoSum(nums, -8);
        System.out.println(resu[0]);
        System.out.println(resu[1]);

    }
}


