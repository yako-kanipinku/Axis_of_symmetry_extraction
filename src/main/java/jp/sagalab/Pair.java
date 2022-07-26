package jp.sagalab;

public class Pair {

  public static Pair create(Point _point1, Point _point2) {
    return new Pair(_point1, _point2);
  }


  public double calculateDistance() {
    return Point.getDistance(m_point1, m_point2);
  }

  public double calculateRadian() {
    double radian = Math.atan2(m_point1.getY() - m_point2.getY(), m_point1.getX() - m_point2.getX());
    return normalizeAngle(radian);
  }

  public static double normalizeAngle(double _angle) {
    if (_angle > Math.PI) {
      return _angle - Math.PI;
    } else if (_angle < 0) {
      return _angle + Math.PI;
    } else {
      return _angle;
    }
  }

  public Point getCenterPoint() {
    double cpX = (m_point1.getX() + m_point2.getX()) / 2;
    double cpY = (m_point1.getY() + m_point2.getY()) / 2;
    double cpF = (m_point1.getF() + m_point2.getF()) / 2;
    return Point.createXYF(cpX, cpY, cpF);
  }

  public Point getP1() {
    return m_point1;
  }

  public Point getP2() {
    return m_point2;
  }

  private Pair(Point _point1, Point _point2) {
    m_point1 = _point1;
    m_point2 = _point2;
  }

  private final Point m_point1;
  private final Point m_point2;
}