package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MutableMatrixTest {

  @Test
  public void constructors() {
    MutableMatrix m1 = new MutableMatrix();
    MutableMatrix result = new MutableMatrix(new double[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } });
    Assertions.assertEquals(m1, result);

    m1 = new MutableMatrix(2, 3);
    result = new MutableMatrix(new double[][] { { 0, 0, 0 }, { 0, 0, 0 } });
    Assertions.assertEquals(m1, result);

    m1 = new MutableMatrix(new double[] { 1, 2, 3 });
    result = new MutableMatrix(new double[][] { { 1, 0, 0 }, { 0, 2, 0 }, { 0, 0, 3 } });
    Assertions.assertEquals(m1, result);

    result = new MutableMatrix(new double[][] { { 1, 0 }, { 2, 0 } });
    m1 = new MutableMatrix(result);
    Assertions.assertEquals(m1, result);
  }

  @Test
  public void identity() {
    MutableMatrix m1 = new MutableMatrix(new double[][] { { 1, 2 } });
    MutableMatrix result = new MutableMatrix(new double[][] { { 1, 2 } });
    m1.identityMatrix();
    Assertions.assertEquals(m1, result);

    m1 = new MutableMatrix(new double[][] { { 1, 2 }, { 3, 4 } });
    result = new MutableMatrix(new double[][] { { 1, 0 }, { 0, 1 } });
    m1.identityMatrix();
    Assertions.assertEquals(m1, result);
  }

  @Test
  public void fill() {
    MutableMatrix m1 = new MutableMatrix();
    MutableMatrix result = new MutableMatrix(new double[][] { { 1, 2 } });
    m1.fillMatrix(new double[][] { { 1, 2 } });
    Assertions.assertEquals(m1, result);
  }

  @Test
  public void multByNum() {
    MutableMatrix m1 = new MutableMatrix(new double[][] { { 1, 2 }, { 3, 4 } });
    MutableMatrix result = new MutableMatrix(new double[][] { { 2, 4 }, { 6, 8 } });

    m1.multiplyByNumber(2);
    Assertions.assertEquals(m1, result);
  }

  @Test
  public void add() {
    MutableMatrix m1 = new MutableMatrix(new double[][] { { 1, 2 } });
    MutableMatrix m2 = new MutableMatrix(new double[][] { { 2, 4 }, { 6, 8 } });
    MutableMatrix result = new MutableMatrix(new double[][] { { 1, 2 } });
    m1.addMatrix(m2);
    Assertions.assertEquals(m1, result);

    m1 = new MutableMatrix(new double[][] { { 1, 2 }, { 3, 4 } });
    m2 = new MutableMatrix(new double[][] { { 2, 4 }, { 6, 8 } });
    result = new MutableMatrix(new double[][] { { 3, 6 }, { 9, 12 } });
    m1.addMatrix(m2);
    Assertions.assertEquals(m1, result);
  }

  @Test
  public void multByMatrix() {
    MutableMatrix m1 = new MutableMatrix(new double[][] { { 1, -2 }, { 3, 4 } });
    MutableMatrix m2 = new MutableMatrix(new double[][] { { 2, 4 } });
    MutableMatrix result = new MutableMatrix(new double[][] { { 1, -2 }, { 3, 4 } });

    m1.multiplyByMatrix(m2);
    Assertions.assertEquals(m1, result);

    m1 = new MutableMatrix(new double[][] { { 1, -2 }, { 3, 4 } });
    m2 = new MutableMatrix(new double[][] { { 2, 4 }, { -6, 8 } });
    result = new MutableMatrix(new double[][] { { 14, -12 }, { -18, 44 } });

    m1.multiplyByMatrix(m2);
    Assertions.assertEquals(m1, result);
  }

  @Test
  public void transp() {
    MutableMatrix m1 = new MutableMatrix(new double[][] { { 1, 2 }, { 3, 4 } });
    MutableMatrix result = new MutableMatrix(new double[][] { { 1, 3 }, { 2, 4 } });

    m1.transpose();
    Assertions.assertEquals(m1, result);
  }

  @Test
  public void upperTr() {
    MutableMatrix m1 = new MutableMatrix(new double[][] { { 1, 2 }, { 3, 4 } });
    MutableMatrix result = new MutableMatrix(new double[][] { { 1, 2 }, { 0, -2 } });

    m1.upperTriangular();
    Assertions.assertEquals(m1, result);
  }

  @Test
  public void lowerTr() {
    MutableMatrix m1 = new MutableMatrix(new double[][] { { 1, 2 }, { 3, 4 } });
    MutableMatrix result = new MutableMatrix(new double[][] { { -0.5, 0 }, { 3, 4 } });

    m1.lowerTriangular();
    Assertions.assertEquals(m1, result);
  }

  @Test
  public void inverse() {
    MutableMatrix m1 = new MutableMatrix(new double[][] { { 3, 4 }, { 5, 7 } });
    m1 = m1.inverse();

    double[][] a = m1.getMatrix();
    double[][] b = new double[][] { { 7, -4 }, { -5, 3 } };

    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[0].length; j++) {
        a[i][j] = Math.round(a[i][j]);
        b[i][j] = Math.round(b[i][j]);
      }
    }

    Assertions.assertArrayEquals(a, b);
  }

  @Test
  public void getters() {
    MutableMatrix m1 = new MutableMatrix(new double[][] { { 1, 2 }, { 3, 4 } });
    double[] ar = m1.getRow(3);
    Assertions.assertArrayEquals(null, ar);

    ar = m1.getRow(2);
    double[] result = new double[] { 3, 4 };
    Assertions.assertArrayEquals(result, ar);

    ar = m1.getColumn(3);
    Assertions.assertArrayEquals(null, ar);

    ar = m1.getColumn(2);
    result = new double[] { 2, 4 };
    Assertions.assertArrayEquals(result, ar);

    String dim = m1.getDimension();
    Assertions.assertEquals("2x2", dim);

    Assertions.assertArrayEquals(new double[][] { { 1, 2 }, { 3, 4 } }, m1.getMatrix());
  }

  @Test
  public void rowColumnMatrix() {
    MutableMatrix m1 = new MutableMatrix();
    m1.rowMatrix(3);
    Assertions.assertEquals("1x3", m1.getDimension());
    m1.columnMatrix(2);
    Assertions.assertEquals("2x1", m1.getDimension());
  }

}
