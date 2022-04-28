public class merge {
        public static void merge(int[] nums1, int m, int[] nums2, int n) {
            int index1 = 0; //  indices of the current positions in the two lists that we are comparing
            int index2 = 0;
            if (n == 0) {
                return;
            }
            int i = m;
            while (!(index1 == m && index2 == n)) {
                if (index1 == m) {
                    nums1[i] = nums2[index2];
                    index2++;
                } else if (index2 == n) {
                    nums1[i] = nums1[index1];
                    index1++;
                } else if(nums1[index1] < nums2[index2]) {
                    nums1[i] = nums1[index1];
                    index1++;
                } else {
                    nums1[i] = nums2[index2];
                    index2++;
                }
                i = plusOne(i, m, n);
            }
        }

        public static int plusOne(int i, int m, int n) {
            int resu = i;
            if (i == m + n - 1) {
                resu = 0;
            } else {
                resu++;
            }
            return resu;
        }
    public static void main(String[] args) {
        int[] nums1  = {1, 2, 3, 0, 0, 0};
        int[] nums2 =  {2, 5, 6};
        merge(nums1, 3,nums2, 3);
        System.out.println(nums1);
    }
}


