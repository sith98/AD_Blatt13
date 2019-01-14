import java.util.Arrays;

public class Aufgabe4 {
    private static final double Inf = Double.POSITIVE_INFINITY;

    private static double[][] floydWarshall(double[][] matrix) {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("matrix " + Arrays.deepToString(matrix) + " has to be square-shaped.");
        }

        final int N = matrix.length;

        Integer[][] predecessor = new Integer[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] != 0 && matrix[i][j] != Inf) {
                    predecessor[i][j] = i;
                }
            }
        }

        double[][] d = copy(matrix);
        for (int k = 0; k < N; k++) {
            double[][] newD = new double[d.length][d[0].length];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newD[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                    if (newD[i][j] != d[i][j]) {
                        predecessor[i][j] = predecessor[k][j];
                    }
                }
            }
            d = newD;
        }

        for (Integer[] row : predecessor) {
            System.out.println(
                Arrays.toString(Arrays.stream(row)
                    .map(it -> it == null ? null : it + 1)
                    .toArray())
            );
        }
        System.out.println();
        return d;
    }

    private static double[][] copy(double[][] matrix) {
        double[][] copy = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, copy[i], 0, matrix[0].length);
        }
        return copy;
    }

    public static void main(String[] args) {
        double[][] example = {
            {0, 3, 8, Inf, -4},
            {Inf, 0, Inf, 1, 7},
            {Inf, 4, 0, Inf, Inf},
            {2, Inf, -5, 0, Inf},
            {Inf, Inf, Inf, 6, 0}
        };

        double[][] example2 = {
            {0, Inf, Inf, Inf, -1, Inf},
            {1, 0, Inf, 2, Inf, Inf},
            {Inf, 2, 0, Inf, Inf, -8},
            {-4, Inf, Inf, 0, 3, Inf},
            {Inf, 7, Inf, Inf, 0, Inf},
            {Inf, 5, 10, Inf, Inf, 0}
        };
        var result = floydWarshall(example2);

        for (double[] row : result) {
            System.out.println(Arrays.toString(row));
        }


    }
}
