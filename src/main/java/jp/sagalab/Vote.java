package jp.sagalab;
import java.util.*;

public class Vote {

  final private FuzzyPoint m_point1;
  final private FuzzyPoint m_point2;

  private final int NUM_OF_DIVISION_RHO = 1600;
  private final int NUM_OF_DIVISION_THETA = 360;

  final private double R = Math.sqrt(Math.pow(800,2) + Math.pow(800,2));

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

      for(int j= -NUM_OF_DIVISION_RHO/2; j<NUM_OF_DIVISION_RHO/2; j++){
        vote[i][j + NUM_OF_DIVISION_RHO/2] = Math.min(p.getGradeOfAngle3(i * 2 * Math.PI / NUM_OF_DIVISION_THETA),p.getGradeOfDistance(i * 2 * Math.PI / NUM_OF_DIVISION_THETA,j * R / (NUM_OF_DIVISION_RHO/2.0)));
      }
    }
    return vote;
  }

	// その地点の投票結果を返すメソッド. (グレードで返す)
  public double getGrade(int _thetaNum, int _rhoNum){
    double[][] vote = voteGrade();
    return vote[_thetaNum][_rhoNum];
  }

	// グレード0以上の軸を返すメソッド. (リストで返す)
  public List<Axis> getSymmetricAxis(){
    List<Axis> axis = new ArrayList<>();
    double grade;
    double[][] vote = voteGrade();

    for(int i=0; i<NUM_OF_DIVISION_THETA; i++){
      for(int j=-NUM_OF_DIVISION_RHO/2; j<NUM_OF_DIVISION_RHO/2; j++){
        grade = vote[i][j + NUM_OF_DIVISION_RHO/2];
        if(grade > 0){
          axis.add(Axis.create(j * R / (NUM_OF_DIVISION_RHO/2.0),i * 2 * Math.PI / NUM_OF_DIVISION_THETA,grade));
        }
      }
    }
    return axis;
  }

  public Axis getMostSymmetricAxis(List<Axis> axis){
    Axis mostSymmetricAxis = axis.get(0);

    for(int i = 0; i < axis.size() - 1; i++){
      if(axis.get(i).getGrade() < axis.get(i + 1).getGrade()){
        mostSymmetricAxis = axis.get(i + 1);
      }
    }
    return mostSymmetricAxis;
  }
}
