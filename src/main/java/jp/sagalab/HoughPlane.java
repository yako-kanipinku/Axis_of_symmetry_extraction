package jp.sagalab;

/**
 * ハフ平面の生成と値の入出力を行う.
 */
public class HoughPlane {

  /**
   * ハフ平面のインスタンス生成を行う.
   * @return ハフ平面のインスタンス
   */
  public static HoughPlane create () {
    return new HoughPlane();
  }

  /**
   * ハフ平面を配列にして取得する.
   * @return ハフ平面(配列)
   */
  public double[][] getHoughPlaneArray() {
    return m_houghPlane;
  }

  /**
   * 指定した位置にハフ平面に値を入力する.
   * @param _angleNum 分割した際の角度番号[0 , N_θ-1]
   * @param _pixelNum 分割した際の距離番号[-N_ρ , N_ρ]
   * @param _value 入力したい値
   */
  public void setValue(int _angleNum, int _pixelNum, double _value) {
    m_houghPlane[_angleNum][NUM_OF_DIVISION_PIXELS + _pixelNum] = _value;
  }

  /**
   * 指定した位置の値を取得する.
   * @param _angleNum 分割した際の角度番号[0 , N_θ-1]
   * @param _pixelNum 分割した際の距離番号[-N_ρ , N_ρ]
   * @return 指定した位置の値
   */
  public double getValue(int _angleNum, int _pixelNum) {
    return m_houghPlane[_angleNum][NUM_OF_DIVISION_PIXELS + _pixelNum];
  }

  /**
   * コンストラクタ
   */
  HoughPlane() {
    m_houghPlane = new double[NUM_OF_DIVISION_ANGLES][NUM_OF_DIVISION_PIXELS * 2 + 1];
  }

  /** ハフ平面におけるρの分割数 (N_ρ) */
  public static final int NUM_OF_DIVISION_PIXELS = 800;
  /** ハフ平面におけるθの分割数 (N_θ) */
  public static final int NUM_OF_DIVISION_ANGLES = 360;
  /** ハフ平面 */
  private final double[][] m_houghPlane;
}