package org.example;

import java.util.Arrays;

public final class ImmutableMatrix {

    private final double[][] matrix;
    private final int rows;
    private final int columns;

    public ImmutableMatrix() {
        this.matrix = new double[3][3];
        this.rows = 3;
        this.columns = 3;
    }

    public ImmutableMatrix(int rows, int columns) {
        this.matrix = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public ImmutableMatrix(double[][] data) {
        this.rows = data.length;
        this.columns = data[0].length;
        this.matrix = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.copyOf(data[i], columns);
        }
    }

    public ImmutableMatrix(double[] vector) {
        this.rows = vector.length;
        this.columns = vector.length;
        this.matrix = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == j) {
                    matrix[i][j] = vector[i];
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public ImmutableMatrix(ImmutableMatrix source) {
        this.rows = source.rows;
        this.columns = source.columns;
        this.matrix = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            this.matrix[i] = Arrays.copyOf(source.matrix[i], columns);
        }
    }

    public ImmutableMatrix identityMatrix() {
        if (this.rows != this.columns) {
            System.out.println("\nYou can not create an identity matrix from this matrix.");
            return this;
        }

        int rows = this.rows;
        int columns = this.columns;
        double[][] newMatrix = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == j) {
                    newMatrix[i][j] = 1;
                } else {
                    newMatrix[i][j] = 0;
                }
            }
        }

        return new ImmutableMatrix(newMatrix);
    }

    public ImmutableMatrix fillMatrix(double[][] data) {
        if (rows != data.length || columns != data[0].length) {
            System.out.println("You can not fill matrix with this data");
            return this;
        } else {
            int row = data.length;
            int col = data[0].length;
            double[][] newMatrix = new double[row][col];

            for (int i = 0; i < row; i++) {
                newMatrix[i] = Arrays.copyOf(data[i], col);
            }

            return new ImmutableMatrix(newMatrix);
        }
    }

    public ImmutableMatrix addMatrix(ImmutableMatrix m) {
        if (this.rows != m.rows || this.columns != m.columns) {
            System.out.println("\nYou can not add this matrixes.");
            return this;
        }

        int rows = this.rows;
        int columns = this.columns;
        double[][] newMatrix = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newMatrix[i][j] = this.getElement(i, j) + m.getElement(i, j);
            }
        }

        return new ImmutableMatrix(newMatrix);
    }

    public ImmutableMatrix multiplyByNumber(double number) {
        int rows = this.rows;
        int columns = this.columns;
        double[][] newMatrix = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newMatrix[i][j] = this.getElement(i, j) * number;
            }
        }

        return new ImmutableMatrix(newMatrix);
    }

    public ImmutableMatrix multiplyByMatrix(ImmutableMatrix m) {
        if (this.columns != m.rows) {
            System.out.println("\nYou can not multiply this matrixes.");
            return this;
        }

        double[][] newMatrix = new double[this.rows][m.columns];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < m.columns; j++) {
                for (int k = 0; k < m.rows; k++)
                    newMatrix[i][j] += this.getElement(i, k) * m.getElement(k, j);
            }
        }

        return new ImmutableMatrix(newMatrix);
    }

    public ImmutableMatrix transpose() {
        int rows = this.rows;
        int columns = this.columns;
        double[][] newMatrix = new double[rows][columns];

        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                newMatrix[i][j] = this.getElement(j, i);
            }
        }

        return new ImmutableMatrix(newMatrix);
    }

    public ImmutableMatrix upperTriangular() {
        if (rows != columns) {
            System.out.println("You can not convert this matrix to upper triangular.");
            return this;
        } else {
            double[][] newMatrix = this.getMatrix();
            double mult;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < rows; j++) {
                    if (j > i) {
                        mult = newMatrix[j][i] / newMatrix[i][i];
                        for (int k = 0; k < rows; k++)
                            newMatrix[j][k] -= (newMatrix[i][k] * mult);
                    }
                }
            }

            return new ImmutableMatrix(newMatrix);
        }
    }

    public ImmutableMatrix lowerTriangular() {
        if (rows != columns) {
            System.out.println("You can not convert this matrix to lower triangular.");
            return this;
        } else {
            double[][] newMatrix = this.getMatrix();
            double mult;

            for (int i = rows - 1; i >= 0; i--) {
                for (int j = rows - 1; j >= 0; j--) {
                    if (j < i) {
                        mult = newMatrix[j][i] / newMatrix[i][i];
                        for (int k = rows - 1; k >= 0; k--)
                            newMatrix[j][k] -= (newMatrix[i][k] * mult);
                    }
                }
            }

            return new ImmutableMatrix(newMatrix);
        }
    }

    public ImmutableMatrix inverse() {
        if (rows != columns) {
            System.out.println("This matrix do not have inverse.");
            return this;
        }

        double[][] newMatrix = new double[rows][rows * 2];
        ImmutableMatrix inverseMatrix = new ImmutableMatrix(rows, rows);
        double a;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.rows; j++) {
                newMatrix[i][j] = this.matrix[i][j];
            }
            newMatrix[i][i + this.rows] = 1;
        }

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.rows; j++) {
                if (j > i) {
                    a = newMatrix[j][i] / newMatrix[i][i];
                    for (int k = 0; k < this.rows * 2; k++)
                        newMatrix[j][k] -= (newMatrix[i][k] * a);
                }
            }
        }

        for (int i = 0; i < this.rows; i++) {
            if (newMatrix[i][i] == 0) {
                System.out.println("This matrix do not have inverse.");
                return null;
            }
        }

        for (int i = this.rows - 1; i >= 0; i--) {
            for (int j = this.rows - 1; j >= 0; j--) {
                if (j < i) {
                    a = newMatrix[j][i] / newMatrix[i][i];
                    for (int k = this.rows * 2 - 1; k >= 0; k--)
                        newMatrix[j][k] -= (newMatrix[i][k] * a);
                }
            }
        }

        for (int i = 0; i < this.rows; i++) {
            a = newMatrix[i][i];
            for (int j = 0; j < this.rows * 2; j++) {
                newMatrix[i][j] /= a;
            }
        }

        for (int i = 0; i < this.rows; i++) {
            for (int j = rows; j < this.rows * 2; j++) {
                inverseMatrix.matrix[i][j - this.rows] = newMatrix[i][j];
            }
        }

        return inverseMatrix;
    }

    public ImmutableMatrix rowMatrix(int length) {
        double[][] newMatrix = new double[1][length];

        double max = 50;
        double min = -50;
        double range = max - min + 1;

        for (int i = 0; i < length; i++) {
            newMatrix[0][i] = Math.random() * range + min;
        }

        return new ImmutableMatrix(newMatrix);
    }

    public ImmutableMatrix columnMatrix(int length) {
        double[][] newMatrix = new double[length][1];

        double max = 50;
        double min = -50;
        double range = max - min + 1;

        for (int i = 0; i < length; i++) {
            newMatrix[i][0] = Math.random() * range + min;
        }

        return new ImmutableMatrix(newMatrix);
    }

    public double getElement(int row, int column) {
        return this.matrix[row][column];
    }

    public double[] getRow(int row) {
        if (row <= this.rows) {
            for (int i = 0; i < matrix.length; i++) {
                if (i == row - 1) {
                    return Arrays.copyOf(matrix[i], matrix.length);
                }
            }
        }

        System.out.println("This row does not exist.");
        return null;
    }

    public double[] getColumn(int column) {
        double[] col = new double[rows];

        if (column <= this.columns) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (j == column - 1) {
                        col[i] = matrix[i][j];
                    }
                }
            }

            return col;
        }

        System.out.println("This column does not exist.");
        return null;

    }

    public String getDimension() {
        return (this.rows + "x" + this.columns);
    }

    public double[][] getMatrix() {
        int rows = this.rows;
        int columns = this.columns;
        double[][] m = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            m[i] = Arrays.copyOf(this.matrix[i], columns);
        }

        return m;
    }

    public void printMatrix() {
        System.out.println();
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.printf("%6.2f", element);
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(matrix);
        result = prime * result + rows;
        result = prime * result + columns;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ImmutableMatrix other = (ImmutableMatrix) obj;
        if (!Arrays.deepEquals(matrix, other.matrix))
            return false;
        if (rows != other.rows)
            return false;
        if (columns != other.columns)
            return false;
        return true;
    }

}
