package jp.sagalab;

public class Calculate {
  public static double calcGrade(Pair _pair, double _ut, double _vs) {
    double gradeTheta = calcFuzzyAngle.calcGradeTheta(_pair, _ut);
    double gradeRho = calcFuzzyDistance.calcGradeRho(_pair, _ut, _vs);
    return Math.min(gradeTheta, gradeRho);
  }

  public static class calcFuzzyAngle {
    public static double calcGradeTheta(Pair _pair, double _theta) {
      return Math.max(Math.max(calcGradeThetaAster(_pair, _theta - 2 * Math.PI), calcGradeThetaAster(_pair, _theta)), calcGradeThetaAster(_pair, _theta + 2 * Math.PI));
    }

    public static double calcGradeThetaAster(Pair _pair, double _theta) {
      double l = _pair.calculateDistance();
      double sumOfPointsF = _pair.getP1().getF() + _pair.getP2().getF();
      return Math.max(Math.max(calcThetaTriangle(_pair, _theta), calcThetaTriangle(_pair, _theta - Math.PI)), Math.max((sumOfPointsF - l)/ sumOfPointsF, 0.0));
    }

    public static double calcThetaTriangle(Pair _pair, double _theta) {
      return Math.min(calcThetaTriangleLeft(_pair, _theta), calcThetaTriangleRight(_pair, _theta));
    }

    public static double calcThetaTriangleLeft(Pair _pair, double _theta) {
      double l = _pair.calculateDistance();
      double sumOfPointsF = _pair.getP1().getF() + _pair.getP2().getF();
      return (2 * l) / (Math.PI * (sumOfPointsF)) * _theta
              + (Math.PI * (sumOfPointsF) - 2 * l * _pair.calculateRadian()) / (Math.PI * (sumOfPointsF));
    }

    public static double calcThetaTriangleRight(Pair _pair, double _theta) {
      double l = _pair.calculateDistance();
      double sumOfPointsF = _pair.getP1().getF() + _pair.getP2().getF();
      return -(2 * l) / (Math.PI * (sumOfPointsF)) * _theta
              + (Math.PI * (sumOfPointsF) + 2 * l * _pair.calculateRadian()) / (Math.PI * (sumOfPointsF));
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
      return 1/m.getF() * _rho + 1 - (m.getX() * Math.cos(_theta) + m.getY() * Math.sin(_theta)) / m.getF();
    }

    public static double calcRhoTriangleRight(Pair _pair, double _theta, double _rho) {
      Point m = _pair.getCenterPoint();
      return -1/m.getF() * _rho + 1 + (m.getX() * Math.cos(_theta) + m.getY() * Math.sin(_theta)) / m.getF();
    }
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
}
