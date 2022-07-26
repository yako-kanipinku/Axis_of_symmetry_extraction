package jp.sagalab;

public class HoughPlane {
  public static HoughPlane create () {
    return new HoughPlane();
  }

  public double[][] getHoughPlaneArray() {
    return m_houghPlane;
  }

  public void setValue(int _angleNum, int _pixelNum, double _value) {
    m_houghPlane[_angleNum][NUM_OF_DIVISION_PIXELS + _pixelNum] = _value;
  }

  public double getValue(int _angleNum, int _pixelNum) {
    return m_houghPlane[_angleNum][NUM_OF_DIVISION_PIXELS + _pixelNum];
  }

  private double[][] generateHoughPlane() {
    double[][] houghPlane = new double[NUM_OF_DIVISION_ANGLES][NUM_OF_DIVISION_PIXELS * 2 + 1];
    for (int i = 0; i < NUM_OF_DIVISION_ANGLES; i++) {
      for (int j = 0; j < NUM_OF_DIVISION_PIXELS; j++) {
         houghPlane[i][j] = 0.0;
      }
    }
    return houghPlane;
  }

  HoughPlane() {
    m_houghPlane = generateHoughPlane();
  }

  /** ハフ平面におけるρの分割数 (N_ρ) */
  public static final int NUM_OF_DIVISION_PIXELS = 800;
  /** ハフ平面におけるθの分割数 (N_θ) */
  public static final int NUM_OF_DIVISION_ANGLES = 360;
  /** ハフ平面 */
  private final double[][] m_houghPlane;
}