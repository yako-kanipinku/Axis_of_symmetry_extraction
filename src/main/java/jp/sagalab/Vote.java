package jp.sagalab;

import java.util.ArrayList;
import java.util.List;

public class Vote {
  public static Vote create(Pair _pair) {
    return new Vote(_pair);
  }

  public Pair getPair() {
    return m_pair;
  }

  public HoughPlane getHoughPlane() {
    return m_houghPlane;
  }

  public List<Axis> getAxis() {
    List<Axis> axisList = new ArrayList<>();
    int N_theta = HoughPlane.NUM_OF_DIVISION_ANGLES;
    int N_rho = HoughPlane.NUM_OF_DIVISION_PIXELS;
    double R = Math.sqrt(Math.pow(Main.CANVAS_SIZE_X, 2.0) + Math.pow(Main.CANVAS_SIZE_Y, 2.0));
    double max = 0.0;
    for (int t = 0; t < N_theta; t++) {
      double u_t = t * (2 * Math.PI / N_theta);
      for (int s = -N_rho; s <= N_rho; s++) {
        double v_s = s * (R / N_rho);
        double grade = m_houghPlane.getValue(t, s);
        if (max < grade) {
          max = grade;
          axisList.clear();
          axisList.add(Axis.create(u_t,v_s));
        }
        if(max == grade){
          axisList.add(Axis.create(u_t,v_s));
        }
      }
    }
    return axisList;
  }

  private void calculate() {
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

  private Vote(Pair _pair) {
    m_pair = _pair;
    m_houghPlane = HoughPlane.create();
    calculate();
  }

  private final Pair m_pair;
  private final HoughPlane m_houghPlane;
}
