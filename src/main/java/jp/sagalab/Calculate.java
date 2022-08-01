package jp.sagalab;

/**
 * グレードの計算を行う.
 */
public class Calculate {

  /**
   * グレードの算出を行う.
   * @param _pair ファジィ点のペア
   * @param _theta 角度パラメータ
   * @param _rho 距離パラメータ
   * @return 指定した点(θ,ρ)におけるグレード
   */
  public static double calcGrade(Pair _pair, double _theta, double _rho) {
    double gradeAngle = calcFuzzyAngle.calcGradeTheta(_pair, _theta);
    double gradeDistance = calcFuzzyDistance.calcGradeRho(_pair, _theta, _rho);
    return Math.min(gradeAngle, gradeDistance);
  }

  /**
   * ファジィ角度に基づくグレードの算出を行う内部クラス.
   */
  public static class calcFuzzyAngle {

    /**
     * ファジィ角度に基づくグレードの算出を行う.
     * @param _pair ファジィ点のペア
     * @param _theta 角度パラメータ(θ)
     * @return ファジィ角度に基づくグレード
     */
    public static double calcGradeTheta(Pair _pair, double _theta) {
      return Math.max(Math.max(calcGradeThetaAster(_pair, _theta - 2 * Math.PI), calcGradeThetaAster(_pair, _theta)), calcGradeThetaAster(_pair, _theta + 2 * Math.PI));
    }

    /**
     * 三角ファジィ(ハット型ファジィ)(角度)から指定した角度の時のグレードを算出する.
     * @param _pair ファジィ点のペア
     * @param _theta 角度パラメータ(θ)
     * @return 三角ファジィ(ハット型ファジィ)(角度)の結果に基づくグレード
     */
    public static double calcGradeThetaAster(Pair _pair, double _theta) {
      double l = _pair.calculateDistance();
      double sumOfPointsF = _pair.getP1().getF() + _pair.getP2().getF();
      return Math.max(Math.max(calcThetaTriangle(_pair, _theta), calcThetaTriangle(_pair, _theta - Math.PI)), Math.max((sumOfPointsF - l) / sumOfPointsF, 0.0));
    }

    /**
     * 二つの直線のandをとることで,指定した角度における三角ファジィ(角度)を算出する.
     * @param _pair ファジィ点のペア
     * @param _theta 角度パラメータ(θ)
     * @return 三角ファジィ(角度)のグレード
     */
    public static double calcThetaTriangle(Pair _pair, double _theta) {
      return Math.min(calcThetaTriangleLeft(_pair, _theta), calcThetaTriangleRight(_pair, _theta));
    }

    /**
     * 三角ファジィ(角度)の左側の直線を算出する.
     * @param _pair ファジィ点のペア
     * @param _theta 角度パラメータ(θ)
     * @return 三角ファジィ(角度)の左側の直線
     */
    public static double calcThetaTriangleLeft(Pair _pair, double _theta) {
      double l = _pair.calculateDistance();
      double sumOfPointsF = _pair.getP1().getF() + _pair.getP2().getF();
      return (2 * l * _theta) / (Math.PI * sumOfPointsF)
              + (Math.PI * sumOfPointsF - 2 * l * _pair.calculateRadian()) / (Math.PI * sumOfPointsF);
    }

    /**
     * 三角ファジィ(角度)の右側の直線の算出する.
     * @param _pair ファジィ点のペア
     * @param _theta 角度パラメータ(θ)
     * @return 三角ファジィ(角度)の右側の直線
     */
    public static double calcThetaTriangleRight(Pair _pair, double _theta) {
      double l = _pair.calculateDistance();
      double sumOfPointsF = _pair.getP1().getF() + _pair.getP2().getF();
      return -(2 * l * _theta) / (Math.PI * sumOfPointsF)
              + (Math.PI * sumOfPointsF + 2 * l * _pair.calculateRadian()) / (Math.PI * sumOfPointsF);
    }
  }

  /**
   * ファジィ距離に基づくグレードの算出を行う内部クラス
   */
  public static class calcFuzzyDistance {

    /**
     * ファジィ距離に基づくグレードの算出を行う.
     * @param _pair ファジィ点のペア
     * @param _theta 角度パラメータ(θ)
     * @param _rho 距離パラメータ(ρ)
     * @return ファジィ距離に基づくグレード
     */
    public static double calcGradeRho(Pair _pair, double _theta, double _rho) {
      return Math.max(calcRhoTriangle(_pair, _theta, _rho), 0.0);
    }

    /**
     * 二つの直線のandをとることで,指定した角度における三角ファジィ(距離)を算出する.
     * @param _pair ファジィ点のペア
     * @param _theta 角度パラメータ(θ)
     * @param _rho 距離パラメータ(ρ)
     * @return 三角ファジィ(距離)のグレード
     */
    public static double calcRhoTriangle(Pair _pair, double _theta, double _rho) {
      return Math.min(calcRhoTriangleLeft(_pair, _theta, _rho), calcRhoTriangleRight(_pair, _theta, _rho));
    }

    /**
     * 三角ファジィ(距離)の左側の直線を算出する.
     * @param _pair ファジィ点のペア
     * @param _theta 角度パラメータ(θ)
     * @param _rho 距離パラメータ(ρ)
     * @return 三角ファジィ(距離)の左側の直線
     */
    public static double calcRhoTriangleLeft(Pair _pair, double _theta, double _rho) {
      Point m = _pair.getCenterPoint();
      return (1 * _rho) / m.getF() + 1 - (m.getX() * Math.cos(_theta) + m.getY() * Math.sin(_theta)) / m.getF();
    }

    /**
     * 三角ファジィ(距離)の右側の直線を算出する.
     * @param _pair ファジィ点のペア
     * @param _theta 角度パラメータ(θ)
     * @param _rho 距離パラメータ(ρ)
     * @return 三角ファジィ(距離)の右側の直線
     */
    public static double calcRhoTriangleRight(Pair _pair, double _theta, double _rho) {
      Point m = _pair.getCenterPoint();
      return (-1 * _rho) / m.getF() + 1 + (m.getX() * Math.cos(_theta) + m.getY() * Math.sin(_theta)) / m.getF();
    }
  }
}
