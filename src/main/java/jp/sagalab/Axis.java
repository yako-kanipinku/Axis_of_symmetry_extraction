package jp.sagalab;

public class Axis {

	public static Axis create(double _distance, double _degree){
		return new Axis(_distance, _degree);
	}

	// 角度と距離を求める. (角度のメンバシップ関数を返すメソッドと距離のメンバシップ関数を返すメソッド)

	public Axis(double _distance, double _degree){
		m_distance = _distance;
		m_degree = _degree;
	}

	private final double m_distance;
	private final double m_degree;
}
