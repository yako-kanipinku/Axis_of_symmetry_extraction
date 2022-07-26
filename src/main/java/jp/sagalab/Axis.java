package jp.sagalab;

public class Axis {
  public static Axis create(double _angle, double _distance) {
    return new Axis(_angle, _distance);
  }
  public double getAngle() {
    return m_angle;
  }

  public double getDistance() {
    return m_distance;
  }

  private Axis(double _angle, double _distance) {
    m_angle = _angle;
    m_distance = _distance;
  }

  private final double m_angle;
  private final double m_distance;

}
