package jp.sagalab;

import java.util.ArrayList;
import java.util.List;

/**
 * ハフ平面への投票を行う.
 */
public class Vote {

  /**
   * ハフ投票のインスタンス生成を行う.
   * @param _pair ファジィ点ペア
   * @return ハフ投票のインスタンス
   */
  public static Vote create(Pair _pair) {
    return new Vote(_pair);
  }

  /**
   * ファジィ点ペアを取得する.
   * @return ファジィ点ペア
   */
  public Pair getPair() {
    return m_pair;
  }

  /**
   * ハフ平面を取得する.
   * @return ハフ平面
   */
  public HoughPlane getHoughPlane() {
    return m_houghPlane;
  }

  /**
   * 対称軸の候補を取得する.
   * @return 対称軸の候補
   */
  public List<Axis> getAxis() {
    List<Axis> axisList = new ArrayList<>();
    int N_theta = HoughPlane.NUM_OF_DIVISION_ANGLES;
    int N_rho = HoughPlane.NUM_OF_DIVISION_PIXELS;
    double R = Math.sqrt(Math.pow(Main.CANVAS_SIZE_X, 2.0) + Math.pow(Main.CANVAS_SIZE_Y, 2.0));
    for (int t = 0; t < N_theta; t++) {
      double u_t = t * (2 * Math.PI / N_theta);
      for (int s = -N_rho; s <= N_rho; s++) {
        double v_s = s * (R / N_rho);
        double grade = m_houghPlane.getValue(t, s);
        if (THRESHOLD <= grade) {
          axisList.add(Axis.create(u_t, v_s, grade));
        }
      }
    }
    return axisList;
  }

  /**
   * ハフ投票を行う.
   */
  private void vote() {
    int N_theta = HoughPlane.NUM_OF_DIVISION_ANGLES;
    int N_rho = HoughPlane.NUM_OF_DIVISION_PIXELS;
    double R = Math.sqrt(Math.pow(Main.CANVAS_SIZE_X, 2.0) + Math.pow(Main.CANVAS_SIZE_Y, 2.0));
    for (int t = 0; t < N_theta; t++) {
      double u_t = t * (2 * Math.PI / N_theta);
      for (int s = -N_rho; s <= N_rho; s++) {
        double v_s = s * (R / N_rho);
        double grade = Calculate.calcGrade(m_pair, u_t, v_s);
        m_houghPlane.setValue(t, s, grade);
      }
    }
  }

  /**
   * コンストラクタ
   * @param _pair ファジィ点のペア
   */
  private Vote(Pair _pair) {
    m_pair = _pair;
    m_houghPlane = HoughPlane.create();
    vote();
  }

  /** 対称軸として抽出する閾値 */
  public static final double THRESHOLD = 0.5;
  /** ファジィ点のペア */
  private final Pair m_pair;
  /** 投票を行うハフ平面 */
  private final HoughPlane m_houghPlane;
}
