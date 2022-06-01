class maxChunksToSorted {
    public static int maxChunksToSorted(int[] arr) {
        int cur = 0;
        int resu = 0;
        for (int i = 0; i < arr.length; i++) {
            cur = cur ^ arr[i];
            cur = cur ^  i;
            if (cur == 0) {
                resu++;
            }
        }
        return resu;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 0, 4};
        System.out.println(maxChunksToSorted(arr));

    }
}