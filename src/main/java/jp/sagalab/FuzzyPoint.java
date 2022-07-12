package jp.sagalab;

/**
 * 平面上のファジィ点を表す. 平面上の点のx座標とy座標とファジィ点の半径を保持する.
 */
public final class FuzzyPoint {

  /**
   * 指定した座標にある点を生成する.
   * @param _x x座標
   * @param _y y座標
   * @param _r ファジィ点の半径
   * @return 点
   */
  static FuzzyPoint create(Double _x, Double _y, Double _r) {
    if (_r < 0) {
      throw new IllegalArgumentException("Radius size must be 0 <= r");
    }
    return new FuzzyPoint(_x, _y, _r);
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
   * ファジィネスの大きさを取得する.
   * @return ファジネスの大きさ
   */
  public double getR() {
    return m_r;
  }

  /**
   * コンストラクタ
   * @param _x x座標
   * @param _y y座標
   * @param _r ファジィ点の半径
   */
  private FuzzyPoint(Double _x, Double _y, Double _r) {
    m_x = _x;
    m_y = _y;
    m_r = _r;
  }

  /** x座標 */
  private final Double m_x;
  /** y座標 */
  private final Double m_y;
  /** ファジィ点の半径 */
  private final Double m_r;
}
