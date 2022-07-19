package jp.sagalab;
import java.util.*;

public class Vote {

  final private FuzzyPoint m_point1;
  final private FuzzyPoint m_point2;

  final private int NUM_OF_DIVISION_RHO = 1600;
  final private int NUM_OF_DIVISION_THETA = 360;

  public Vote(FuzzyPoint _p1, FuzzyPoint _p2){
    m_point1 = _p1;
    m_point2 = _p2;
  }

  public static Vote create(FuzzyPoint _p1, FuzzyPoint _p2){
    return new Vote(_p1,_p2);
  }


	// ファジィ角度とファジィ距離のうち小さい方を投票する. (角度のメンバシップ関数, 距離のメンバシップ関数)
  public double[][] voteGrade(){
    double[][] vote;
    vote = new double[NUM_OF_DIVISION_THETA][NUM_OF_DIVISION_RHO];
    Pair p = Pair.create(m_point1,m_point2);

    for(int i=0; i<NUM_OF_DIVISION_THETA; i++){
      for(int j=0; j<NUM_OF_DIVISION_RHO; j++){
        vote[i][j] = Math.min(p.getGradeOfAngle3(i),p.getGradeOfDistance(i,j));
      }
    }
    return vote;
  }

	// その地点の投票結果を返すメソッド. (グレードで返す)
  public double getGrade(double _theta, double _rho){
    double[][] vote = voteGrade();
    return vote[(int)_theta][(int)_rho];
  }

	// グレード0以上の軸を返すメソッド. (リストで返す)
  public List<Axis> getSymmetricAxis(){
    List<Axis> axis = new ArrayList<>();

    for(int i=0; i<NUM_OF_DIVISION_THETA; i++){
      for(int j=0; j<NUM_OF_DIVISION_RHO; j++){
        if(getGrade(i,j) > 0)
          axis.add(Axis.create(i,j,getGrade(i,j)));
      }
    }
    return axis;
  }


  public List<Axis> sort(List<Axis> _axis){

  }

  // いっちゃんでっかいグレードの軸を返すメソッド
  public Axis getMostSymmetricAxis(List<Axis> axis){
    
  }
}
