import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class findDisappearedNumbers {
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> resu = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[nums[i]-1];
            nums[nums[i]-1] =  nums[i];
            nums[i] = temp;
        }

        for (int i = 0; i < nums.length; i++) {
            int temp = nums[nums[i]-1];
            nums[nums[i]-1] =  nums[i];
            nums[i] = temp;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1) {
                resu.add(i+1);
            }
        }
        return resu;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,4,5,6,7,8,1};
        System.out.println(findDisappearedNumbers(nums));
    }
}