package org.jvan100.jutils.matrix;

import org.jvan100.jutils.iterable.Enumerator;

import java.util.Arrays;

public class Matrix {

    private double[][] arr;
    private int m;
    private int n;

    public Matrix(double[][] arr) {
        this.m = arr.length;
        this.n = arr[0].length;

        for (int i = 1; i < m; i++) {
            if (arr[i].length != n)
                throw new MatrixException("Rows must all be the same length");
        }

        this.arr = arr;
    }

    public Matrix(double[][] arr, int m, int n) {
        this.arr = arr;
        this.m = m;
        this.n = n;
    }

    public Matrix(double[] vals, int m) {
        if (vals.length % m != 0)
            throw new MatrixException("Value array length must be a multiple of m");

        this.m = m;
        this.n = vals.length / m;
        this.arr = new double[m][n];

        for (int i = 0; i < vals.length; i++)
            arr[i / n][i % n] = vals[i];
    }

    public Matrix(int m, int n, double s) {
        this.arr = new double[m][n];
        this.m = m;
        this.n = n;

        for (final double[] row : arr)
            Arrays.fill(row, s);
    }

    public Matrix(int m, int n) {
        this.arr = new double[m][n];
        this.m = m;
        this.n = n;
    }

    public double[][] getArray() {
        return arr;
    }

    public double[][] getArrayCopy() {
        return Arrays.stream(arr).map(double[]::clone).toArray(double[][]::new);
    }

    public int getRowDimension() {
        return m;
    }

    public int getColDimension() {
        return n;
    }

    public double get(int row, int col) {
        return arr[row][col];
    }

    public void set(int row, int col, double s) {
        arr[row][col] = s;
    }

    public Matrix transpose() {
        return transpose(this);
    }

    public Matrix plus(double s) {
        return plus(this, s);
    }

    public Matrix plus(Matrix B) {
        return plus(this, B);
    }

    public Matrix plusEquals(double s) {
        return plusEquals(this, s);
    }

    public Matrix plusEquals(Matrix B) {
        return plusEquals(this, B);
    }

    public Matrix minus(Matrix B) {
        return minus(this, B);
    }

    public Matrix minusEquals(Matrix B) {
        return minusEquals(this, B);
    }

    public Matrix elemMult(Matrix B) {
        return elemMult(this, B);
    }

    public Matrix elemMultEquals(Matrix B) {
        return elemMultEquals(this, B);
    }

    public Matrix elemRightDivide(Matrix B) {
        return elemRightDivide(this, B);
    }

    public Matrix elemRightDivideEquals(Matrix B) {
        return elemRightDivideEquals(this, B);
    }

    public Matrix elemLeftDivide(Matrix B) {
        return elemLeftDivide(this, B);
    }

    public Matrix elemLeftDivideEquals(Matrix B) {
        return elemLeftDivideEquals(this, B);
    }

    public Matrix mult(double s) {
        return mult(this, s);
    }

    public Matrix multEquals(double s) {
        return multEquals(this, s);
    }

    public Matrix mult(Matrix B) {
        return mult(this, B);
    }

    public Matrix multEquals(Matrix B) {
        return multEquals(this, B);
    }

    public static Matrix uminus(Matrix A) {
        return mult(A, -1);
    }

    public static Matrix transpose(Matrix A) {
        final int m = A.getRowDimension();
        final int n = A.getColDimension();

        Matrix arrT = new Matrix(n, m);

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++)
                arrT.set(col, row, A.get(row, col));
        }

        return arrT;
    }

    public static Matrix plus(Matrix A, double s) {
        final int m = A.getRowDimension();
        final int n = A.getColDimension();

        final Matrix B = new Matrix(m, n);

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++)
                B.set(row, col, A.get(row, col) + s);
        }

        return B;
    }

    public static Matrix plusEquals(Matrix A, double s) {
        for (int row = 0; row < A.getRowDimension(); row++) {
            for (int col = 0; col < A.getColDimension(); col++)
                A.set(row, col, A.get(row, col) + s);
        }

        return A;
    }

    public static Matrix plus(Matrix A, Matrix B) {
        assertDimensionsEqual(A, B);

        final int m = A.getRowDimension();
        final int n = A.getColDimension();

        Matrix C = new Matrix(m, n);

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++)
                C.set(row, col, A.get(row, col) + B.get(row, col));
        }

        return C;
    }

    public static Matrix plusEquals(Matrix A, Matrix B) {
        assertDimensionsEqual(A, B);

        final int m = A.getRowDimension();
        final int n = A.getColDimension();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++)
                A.set(row, col, A.get(row, col) + B.get(row, col));
        }

        return A;
    }

    public static Matrix minus(Matrix A, Matrix B) {
        assertDimensionsEqual(A, B);

        final int m = A.getRowDimension();
        final int n = A.getColDimension();

        Matrix C = new Matrix(m, n);

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++)
                C.set(row, col, A.get(row, col) - B.get(row, col));
        }

        return C;
    }

    public static Matrix minusEquals(Matrix A, Matrix B) {
        assertDimensionsEqual(A, B);

        final int m = A.getRowDimension();
        final int n = A.getColDimension();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++)
                A.set(row, col, A.get(row, col) - B.get(row, col));
        }

        return A;
    }

    public static Matrix elemMult(Matrix A, Matrix B) {
        assertDimensionsEqual(A, B);

        final int m = A.getRowDimension();
        final int n = A.getColDimension();

        Matrix C = new Matrix(m, n);

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++)
                C.set(row, col, A.get(row, col) * B.get(row, col));
        }

        return C;
    }

    public static Matrix elemMultEquals(Matrix A, Matrix B) {
        assertDimensionsEqual(A, B);

        final int m = A.getRowDimension();
        final int n = A.getColDimension();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++)
                A.set(row, col, A.get(row, col) * B.get(row, col));
        }

        return A;
    }

    public static Matrix elemRightDivide(Matrix A, Matrix B) {
        assertDimensionsEqual(A, B);

        final int m = A.getRowDimension();
        final int n = A.getColDimension();

        Matrix C = new Matrix(m, n);

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++)
                C.set(row, col, A.get(row, col) / B.get(row, col));
        }

        return C;
    }

    public static Matrix elemRightDivideEquals(Matrix A, Matrix B) {
        assertDimensionsEqual(A, B);

        final int m = A.getRowDimension();
        final int n = A.getColDimension();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++)
                A.set(row, col, A.get(row, col) / B.get(row, col));
        }

        return A;
    }

    public static Matrix elemLeftDivide(Matrix A, Matrix B) {
        assertDimensionsEqual(A, B);

        final int m = A.getRowDimension();
        final int n = A.getColDimension();

        Matrix C = new Matrix(m, n);

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++)
                C.set(row, col, B.get(row, col) / A.get(row, col));
        }

        return C;
    }

    public static Matrix elemLeftDivideEquals(Matrix A, Matrix B) {
        assertDimensionsEqual(A, B);

        final int m = A.getRowDimension();
        final int n = A.getColDimension();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++)
                A.set(row, col, B.get(row, col) / A.get(row, col));
        }

        return A;
    }

    public static Matrix mult(Matrix A, double s) {
        final int m = A.getRowDimension();
        final int n = A.getColDimension();

        Matrix B = new Matrix(m, n);

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++)
                B.set(row, col, A.get(row, col) * s);
        }

        return B;
    }

    public static Matrix multEquals(Matrix A, double s) {
        for (int row = 0; row < A.getRowDimension(); row++) {
            for (int col = 0; col < A.getColDimension(); col++)
                A.set(row, col, A.get(row, col) * s);
        }

        return A;
    }

    public static Matrix mult(Matrix A, Matrix B) {
        if (A.getColDimension() != B.getRowDimension())
            throw new MatrixException(String.format("Column dimension of matrix A must equal row dimension of matrix B (A: %d x *%d*, B: *%d* x %d)", A.getRowDimension(), A.getColDimension(), B.getRowDimension(), B.getColDimension()));

        Matrix C = new Matrix(A.getRowDimension(), B.getColDimension());

        for (int row = 0; row < A.getRowDimension(); row++) {
            for (int col = 0; col < B.getColDimension(); col++) {
                double sum = 0;

                for (int mid = 0; mid < A.getColDimension(); mid++)
                    sum += A.get(row, mid) * B.get(mid, col);

                C.set(row, col, sum);
            }
        }

        return C;
    }

    public static Matrix multEquals(Matrix A, Matrix B) {
        if (A.getColDimension() != B.getRowDimension())
            throw new MatrixException(String.format("Column dimension of matrix A must equal row dimension of matrix B (A: %d x *%d*, B: *%d* x %d)", A.getRowDimension(), A.getColDimension(), B.getRowDimension(), B.getColDimension()));

        for (int row = 0; row < A.getRowDimension(); row++) {
            for (int col = 0; col < B.getColDimension(); col++) {
                double sum = 0;

                for (int mid = 0; mid < A.getColDimension(); mid++)
                    sum += A.get(row, mid) * B.get(mid, col);

                A.set(row, col, sum);
            }
        }

        return A;
    }
    
    public static Matrix identity(int m) {
        Matrix A = new Matrix(m, m);

        for (int i = 0; i < m; i++)
            A.set(i, i, 1);

        return A;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");

        Enumerator.enumerate(arr).forEach((enumItem) -> {
            if (enumItem.index() != 0)
                sb.append(" ");

            sb.append("[");

            Arrays.stream(enumItem.item()).forEach((val) -> sb.append(" ").append(val).append(" "));

            sb.append("]");

            if (enumItem.index() != arr.length - 1)
                sb.append("\n");
        });

        return sb.append("]").toString();
    }

    private static void assertDimensionsEqual(Matrix A, Matrix B) {
        if (A.getRowDimension() != B.getRowDimension() || A.getColDimension() != B.getColDimension())
            throw new MatrixException(String.format("Matrices must have the same dimensions (A: %d x %d, B: %d x %d)", A.getRowDimension(), A.getColDimension(), B.getRowDimension(), B.getColDimension()));
    }

}
