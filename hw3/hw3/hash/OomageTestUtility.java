package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        boolean resu = true;
        int[] bucketNums = new int[M];
        int i = 0;
        for (Oomage o : oomages) {
            bucketNums[(o.hashCode() & 0x7FFFFFFF) % M] += 1;
            i += 1;
        }
        for (i = 0; i < M; i++) {
            resu = resu & bucketNums[i] > (double) oomages.size() / 50 & bucketNums[i] < oomages.size() / 2.5;
        }
        return resu;
    }
}
