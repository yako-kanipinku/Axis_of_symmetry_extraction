package jp.sagalab;

public class Axis {

	public static Axis create(double _distance, double _degree){
		return new Axis(_distance, _degree);
	}

	public Axis(double _distance, double _degree){
		m_distance = _distance;
		m_degree = _degree;
	}

	private final double m_distance;
	private final double m_degree;
}
