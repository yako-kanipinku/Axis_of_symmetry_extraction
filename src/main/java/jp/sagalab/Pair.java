package jp.sagalab;

/**
 * ファジィ点のペア
 */
public class Pair {

  /**
   * インスタンス生成を行う.
   * @param _point1 ファジィ点1
   * @param _point2 ファジィ点2
   * @return ファジィ点ペアのインスタンス
   */
  public static Pair create(Point _point1, Point _point2) {
    return new Pair(_point1, _point2);
  }

  /**
   * ファジィ点ペアの距離を算出する.
   * @return ファジィ点ペアの距離
   */
  public double calculateDistance() {
    return Point.getDistance(m_point1, m_point2);
  }

  /**
   * ファジィ点ペアをつないだ直線とx軸とのなす角を算出する.
   * @return 2点を結んだ直線ろx軸とのなす角の大きさ(単位:radian)
   */
  public double calculateRadian() {
    double radian = Math.atan2(m_point1.getY() - m_point2.getY(), m_point1.getX() - m_point2.getX());
    return normalizeAngle(radian);
  }

  /**
   * 角度の正規化(0≦_angle＜πへと変換)を行う.
   * @param _angle 角度
   * @return 正規化後の角度
   */
  public static double normalizeAngle(double _angle) {
    if (_angle > Math.PI) {
      return _angle - Math.PI;
    } else if (_angle < 0) {
      return _angle + Math.PI;
    } else {
      return _angle;
    }
  }

  /**
   *ファジィ中点を取得する.
   * @return ファジィ中点
   */
  public Point getCenterPoint() {
    double cpX = (m_point1.getX() + m_point2.getX()) / 2;
    double cpY = (m_point1.getY() + m_point2.getY()) / 2;
    double cpF = (m_point1.getF() + m_point2.getF()) / 2;
    return Point.createXYF(cpX, cpY, cpF);
  }

  /**
   * ファジィ点1を取得する.
   * @return ファジィ点
   */
  public Point getP1() {
    return m_point1;
  }

  /**
   * ファジィ点2を取得する.
   * @return ファジィ点
   */
  public Point getP2() {
    return m_point2;
  }

  /**
   * コンストラクタ
   * @param _point1 ファジィ点1
   * @param _point2 ファジィ点2
   */
  private Pair(Point _point1, Point _point2) {
    m_point1 = _point1;
    m_point2 = _point2;
  }

  /** ファジィ点1 */
  private final Point m_point1;
  /** ファジィ点2 */
  private final Point m_point2;
}