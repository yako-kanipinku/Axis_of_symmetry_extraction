package jp.sagalab;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * canvasの表示と,描画に関する入力処理を行う.
 */
public class MyCanvas extends Canvas
        implements MouseListener, MouseMotionListener {

  public static MyCanvas create(int _width, int _height) {
    return new MyCanvas(_width, _height);
  }


  /**
   * 出力された描画をクリアする
   */
  public void clearCanvas() {
    Graphics g = getGraphics();
    g.clearRect(0, 0, getWidth(), getHeight());
  }

  /**
   * 指定した点を指定した色で描画する.
   *
   * @param _point 点
   * @param _color 点の色
   */
  public void drawPoint(Point2D.Double _point, Color _color) {
    int radius = 4;
    int diameter = 2 * radius;
    Graphics g = this.getGraphics();
    g.setColor(_color);
    g.fillOval((int) _point.getX() - radius, (int) _point.getY() - radius, diameter, diameter);
  }

  /**
   * 指定したファジィ点のファジネスを指定した色で描画する.
   * @param _point ファジィ点
   * @param _color 色
   */
  public void drawArc(Point _point, Color _color) {
    Graphics g = this.getGraphics();
    g.setColor(_color);
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(1.5f));
    g.drawOval((int) (_point.getX() - _point.getF()), (int) (_point.getY() - _point.getF()), (int) (2 * _point.getF()), (int) (2 * _point.getF()));
  }

  /**
   * マウスボタンをクリックした時の処理
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    int button = e.getButton();
    if (button == MouseEvent.BUTTON3) {
      m_pointList.clear();
      clearCanvas();
    }
  }

  /**
   * マウスボタンを押したときの処理
   *
   * @param e the event to be processed
   */
  @Override
  public void mousePressed(MouseEvent e) {
    int button = e.getButton();
    if (button == MouseEvent.BUTTON1) {
      if (m_pointList.size() < MAX_POINTS) {
        m_centerPoint = new Point2D.Double(e.getX(), e.getY());
        drawPoint(m_centerPoint, Color.ORANGE);
      }
    }
  }

  /**
   * マウスボタンをドラッグした時の処理
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseDragged(MouseEvent e) {
  }

  /**
   * マウスを動かしたときの処理
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseMoved(MouseEvent e) {
  }

  /**
   * マウスボタンを離したときの処理
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    int button = e.getButton();
    if (button == MouseEvent.BUTTON1) {
      if (m_pointList.size() < MAX_POINTS) {
        Point2D.Double arcPoint = new Point2D.Double(e.getX(), e.getY());
        Point point = Point.createXYF(m_centerPoint.getX(), m_centerPoint.getY(), m_centerPoint.distance(arcPoint));
        drawArc(point, Color.BLACK);
        m_pointList.add(point);
      }
      if (m_pointList.size() == MAX_POINTS) {
        calculate();
      }
    }
  }

  /**
   * マウスが入ってきたときの処理
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseEntered(MouseEvent e) {
  }

  /**
   * マウスが出ていった時の処理
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseExited(MouseEvent e) {
  }

  public void calculate() {
    for (int i = 0; i < m_pointList.size(); i++) {
      for (int j = i + 1; j < m_pointList.size(); j++) {
        Pair pair = Pair.create(m_pointList.get(i), m_pointList.get(j));
        Vote vote = Vote.create(pair);
      }
    }
  }

  /**
   * コンストラクタ
   */
  private MyCanvas(int _width, int _height) {
    setSize(_width, _height);
    addMouseListener(this);
    addMouseMotionListener(this);
    this.setBackground(Color.WHITE);
  }

  /** 点のリスト */
  private final List<Point> m_pointList = new ArrayList<>();
  /** ファジィ点の中心 */
  private Point2D.Double m_centerPoint;
  /** 点の数の上限 */
  private static final int MAX_POINTS = 2;
}