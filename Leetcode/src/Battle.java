import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Battle {
    public static void main(String[] args) {
//        //Test case 1
//        int n = 3;
//        int[] H = {2, 1, 4};
//        int[] D = {3, 1, 2};
//        int B = 4;
//
////        Test case 2
////        int n = 4;
////        int[] H = {1, 1, 2, 100};
////        int[] D = {1, 2, 1, 3};
////        int B = 8;
//
//        //Test case 3
////        int n = 4;
////        int[] H = {1, 1, 2, 3};
////        int[] D = {1, 2, 1, 100};
////        int B = 8;
//        double result = getMaxDamageDealt(n, H, D, B);
        List result = new ArrayList<>();
        result.add(5);
        result.add("5");
        System.out.println(result);
    }

    //sorting function by inner ArrayList's product


    static double getMaxDamageDealt(int numOfWarriors, int[] Health, int[] Damage, int BossDamage) {
        int[] arr = new int[numOfWarriors];
        int maxIndex = 0;
        for (int i = 0; i < numOfWarriors; i++) {
            arr[i] = Health[i] * (Damage[i] + 1);
            if (arr[i] >= arr[maxIndex]) {
                maxIndex = i;
            }
        }
        double resu = 0;
        for (int i = 0; i < numOfWarriors; i++) {
            if (i != maxIndex) {
                double firstOrder = Health[maxIndex] * ((double)Damage[maxIndex] +  Damage[i]) + Health[i] * Damage[i];
                double secondOrder =Health[i] * ((double)Damage[maxIndex] +  Damage[i]) + Health[maxIndex] * Damage[maxIndex];
                resu = Math.max(resu, firstOrder);
                resu = Math.max(resu, secondOrder);
            }
        }
        return resu / BossDamage;

    }

}