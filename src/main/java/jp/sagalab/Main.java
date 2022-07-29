package jp.sagalab;

import javax.swing.*;
import java.awt.*;
import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame{

	class WindowClosing extends WindowAdapter {
		public void windowClosing(WindowEvent e){
			int ans = JOptionPane.showConfirmDialog(Main.this,  "本当に閉じますか ?");
			if(ans == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}

	public void drawPoint(Point _p, Color _color) {
		Graphics g = canvas.getGraphics();
		g.setColor(_color);
		int diameter = 8;
		g.fillOval((int)_p.getX()-diameter/2, (int)_p.getY()-diameter/2, diameter, diameter);
	}

	public void drawFuzzy(Point _previousPoint, Point _currentPoint, Color _color){
		Graphics g = canvas.getGraphics();
		g.setColor(_color);
		double radiusX = Math.pow(_currentPoint.getX() - _previousPoint.getX(), 2);
		double radiusY = Math.pow(_currentPoint.getY() - _previousPoint.getY(), 2);
		double radius = Math.sqrt(radiusX + radiusY);

		FuzzyPoint fuzzyPoint = FuzzyPoint.create(_previousPoint.getX(), _previousPoint.getY(), radius);
		m_fuzzyPoints.add(fuzzyPoint);

		g.drawOval((int)_previousPoint.getX()-(int)radius, (int)_previousPoint.getY()-(int)radius, (int)radius*2, (int)radius*2);
	}

	public void drawAxis(Axis _axis, Color _color){
		double x1,x2;
		x1 = _axis.getDistance()/Math.cos(_axis.getDegree());
		x2 = _axis.getDistance()/Math.cos(_axis.getDegree()) - Math.tan(_axis.getDegree())*CANVAS_SIZE_Y;

		Graphics g = canvas.getGraphics();
		g.setColor(_color);

		g.drawLine((int)x1,0,(int)x2,CANVAS_SIZE_Y);
	}

	public Main() {
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		addWindowListener(new WindowClosing());
		setState(JFrame.ICONIFIED);
		//setIconImage(new ImageIcon("icon2.jpg").getImage());
		canvas.setSize(CANVAS_SIZE_X, CANVAS_SIZE_Y);
		canvas.setBackground(Color.WHITE);
		setTitle("Axis_of_symmetry_extraction");
		add(canvas);
		pack();
		setVisible( true );

		canvas.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						m_counter++;

						if(m_counter%2 == 1){
							Point point = new Point(e.getX(), e.getY());
							drawPoint(point, Color.RED);
							m_points.add(point);
						}else if(m_counter%2 == 0){
							drawFuzzy(m_points.get(0), e.getPoint(), Color.BLUE);
							m_points.clear();

							if(m_fuzzyPoints.size() == 2){
								System.out.println("if文内ではある.");
								Vote a = Vote.create(m_fuzzyPoints.get(0), m_fuzzyPoints.get(1));
								System.out.println(" a出てる.");
								List<Axis> b = a.getSymmetricAxis();
								System.out.println(" b出てる.");
								Axis c = a.getMostSymmetricAxis(b);
								System.out.println(" c出てる.");
								System.out.println("distance:"+c.getDistance()+", degree:"+Math.toDegrees(c.getDegree()));
								drawAxis(c, Color.GREEN);
								System.out.println("MostAxis描画完了");


							}
						}

					}

				}
		);
	}


	private int m_counter = 0;
	private List<Point> m_points = new ArrayList<>();
	private List<FuzzyPoint> m_fuzzyPoints = new ArrayList<>();
	private final int CANVAS_SIZE_X = 800;
	private final int CANVAS_SIZE_Y = 800;
	private final Canvas canvas = new Canvas();

	public static void main(String[] args) {
		new Main();
	}
}
