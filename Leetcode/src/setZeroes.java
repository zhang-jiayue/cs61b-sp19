public class setZeroes {
    public static void setZeroes(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        boolean zeroFirstrow = true;
        /* If a number in the matrix is 0, we store this information in the first row and first column of the matrix */
        for (int col = 0; col < colLen; col++) {
            if (matrix[0][col] == 0) {
                zeroFirstrow = true;
            }
        }
        for (int row = 1; row < rowLen; row++) {
            for (int col = 1; col < colLen; col++) {
                if (matrix[row][col] == 0) {
                    matrix[0][col] = 0;;
                    matrix[row][0] = 0;
                }
            }
        }

        for (int col = 1; col < colLen; col++) {
            if (matrix[0][col] == 0) {
                for (int j = 0; j < rowLen; j++) {
                    matrix[j][col] = 0;
                }
            }
        }

        for (int row = 0; row < rowLen; row++) {
            if (matrix[row][0] == 0) {
                for (int j = 0; j < colLen; j++) {
                    matrix[row][j] = 0;
                }
            }
        }
        if (zeroFirstrow) {
            for (int j = 0; j < rowLen; j++) {
                matrix[j][0] = 0;
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = {{1,1,1}, {1,0,1},{1,1,1}};
        setZeroes(matrix);
        System.out.println(matrix);
    }
}
