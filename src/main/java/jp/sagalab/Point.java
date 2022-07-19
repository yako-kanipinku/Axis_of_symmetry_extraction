package jp.sagalab;

/**
 * 平面上のファジィ点を表す. 平面上の点のx座標とy座標とファジィ点の半径を保持する.
 */
public final class Point {

  /**
   * 指定した座標にある点を生成する.
   * @param _x x座標
   * @param _y y座標
   * @param _f ファジネスの値
   * @return 点のインスタンス
   */
  static Point create(Double _x, Double _y, Double _f) {
    if (_f < 0) {
      throw new IllegalArgumentException("The value of _f must be 0 <= _f");
    }
    return new Point(_x, _y, 0.0, _f);
  }

  /**
   * 点のx座標を取得する.
   * @return x座標
   */
  public double getX() {
    return m_x;
  }

  /**
   * 点のy座標を取得する.
   * @return y座標
   */
  public double getY() {
    return  m_y;
  }

  /**
   * 点のz座標を取得する.
   * @return z座標
   */
  public double getZ() {
    return m_z;
  }

  /**
   * ファジィネス値を取得する.
   * @return ファジネスの値
   */
  public double getF() {
    return m_f;
  }

  /**
   * コンストラクタ
   * @param _x x座標
   * @param _y y座標
   * @param _z z座標
   * @param _f ファジネスの値
   */
  private Point(Double _x, Double _y, Double _z, Double _f) {
    m_x = _x;
    m_y = _y;
    m_z = _z;
    m_f = _f;
  }

  /** x座標 */
  private final Double m_x;
  /** y座標 */
  private final Double m_y;
  /** z座標 */
  private final Double m_z;
  /** ファジネスの値 */
  private final Double m_f;
}
