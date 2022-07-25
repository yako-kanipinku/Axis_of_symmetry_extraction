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
  static Point createXYF(Double _x, Double _y, Double _f) {
    if (_f < 0) {
      throw new IllegalArgumentException("The value of _f must be 0 <= _f");
    }
    return new Point(_x, _y, _f);
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

  public static double getDistance(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2.0) + Math.pow(p1.getY() - p2.getY(), 2.0));
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
   * @param _f ファジネスの値
   */
  private Point(Double _x, Double _y, Double _f) {
    m_x = _x;
    m_y = _y;
    m_f = _f;
  }

  /** x座標 */
  private final Double m_x;
  /** y座標 */
  private final Double m_y;
  /** ファジネスの値 */
  private final Double m_f;
}
