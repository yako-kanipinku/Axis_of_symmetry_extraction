package jp.sagalab;

public class Calculate {
  public static double calcGrade(Pair _pair, double _theta, double _rho) {
    double gradeAngle = calcFuzzyAngle.calcGradeTheta(_pair, _theta);
    double gradeDistance = calcFuzzyDistance.calcGradeRho(_pair, _theta, _rho);
    return Math.min(gradeAngle, gradeDistance);
  }

  public static class calcFuzzyAngle {
    public static double calcGradeTheta(Pair _pair, double _theta) {
      return Math.max(Math.max(calcGradeThetaAster(_pair, _theta - 2 * Math.PI), calcGradeThetaAster(_pair, _theta)), calcGradeThetaAster(_pair, _theta + 2 * Math.PI));
    }

    public static double calcGradeThetaAster(Pair _pair, double _theta) {
      double l = _pair.calculateDistance();
      double sumOfPointsF = _pair.getP1().getF() + _pair.getP2().getF();
      return Math.max(Math.max(calcThetaTriangle(_pair, _theta), calcThetaTriangle(_pair, _theta - Math.PI)), Math.max((sumOfPointsF - l) / sumOfPointsF, 0.0));
    }

    public static double calcThetaTriangle(Pair _pair, double _theta) {
      return Math.min(calcThetaTriangleLeft(_pair, _theta), calcThetaTriangleRight(_pair, _theta));
    }

    public static double calcThetaTriangleLeft(Pair _pair, double _theta) {
      double l = _pair.calculateDistance();
      double sumOfPointsF = _pair.getP1().getF() + _pair.getP2().getF();
      return (2 * l * _theta) / (Math.PI * sumOfPointsF)
              + (Math.PI * sumOfPointsF - 2 * l * _pair.calculateRadian()) / (Math.PI * sumOfPointsF);
    }

    public static double calcThetaTriangleRight(Pair _pair, double _theta) {
      double l = _pair.calculateDistance();
      double sumOfPointsF = _pair.getP1().getF() + _pair.getP2().getF();
      return -(2 * l * _theta) / (Math.PI * sumOfPointsF)
              + (Math.PI * sumOfPointsF + 2 * l * _pair.calculateRadian()) / (Math.PI * sumOfPointsF);
    }
  }

  public static class calcFuzzyDistance {
    public static double calcGradeRho(Pair _pair, double _theta, double _rho) {
      return Math.max(calcRhoTriangle(_pair, _theta, _rho), 0.0);
    }

    public static double calcRhoTriangle(Pair _pair, double _theta, double _rho) {
      return Math.min(calcRhoTriangleLeft(_pair, _theta, _rho), calcRhoTriangleRight(_pair, _theta, _rho));
    }

    public static double calcRhoTriangleLeft(Pair _pair, double _theta, double _rho) {
      Point m = _pair.getCenterPoint();
      return (1 * _rho) / m.getF() + 1 - (m.getX() * Math.cos(_theta) + m.getY() * Math.sin(_theta)) / m.getF();
    }

    public static double calcRhoTriangleRight(Pair _pair, double _theta, double _rho) {
      Point m = _pair.getCenterPoint();
      return (-1 * _rho) / m.getF() + 1 + (m.getX() * Math.cos(_theta) + m.getY() * Math.sin(_theta)) / m.getF();
    }
  }



}
