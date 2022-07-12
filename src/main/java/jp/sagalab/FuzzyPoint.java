package jp.sagalab;

import java.awt.*;

public class FuzzyPoint {

	public static FuzzyPoint create(Double _x, Double _y, Double _r){
		return new FuzzyPoint(_x, _y, _r);
	}

	public double getX(){
		return m_x;
	}

	public double getY(){
		return m_y;
	}

	public double getR(){
		return m_r;
	}

	public FuzzyPoint midPoint(FuzzyPoint _p){
		return FuzzyPoint.create((_p.getX()-m_x)/2,(_p.getY()-m_y)/2, m_r);
		// とりあえずm_rで返してます.
	}

	private FuzzyPoint(Double _x, Double _y, Double _r){
		m_x = _x;
		m_y = _y;
		m_r = _r;
	}

	private final Double m_x;
	private final Double m_y;
	private final Double m_r;
}