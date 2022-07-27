package jp.sagalab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JPanelクラスを継承したMyCanvasクラス
 * MyCanvasの設定と描画に関する入力処理を行う.
 */
public class MyJPanel extends JPanel
        implements MouseListener, MouseMotionListener {

  /**
   * MyCanvasのインスタンス生成を行う.
   * @param _width MyCanvasの横幅
   * @param _height MyCanvasの高さ
   * @return MyCanvasのインスタンス
   */
  public static MyJPanel create(int _width, int _height) {
    return new MyJPanel(_width, _height);
  }

  /**
   * マウスボタンをクリックした時の処理
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    int button = e.getButton();
    if (button == MouseEvent.BUTTON1) {
      if (m_pointList.size() < MAX_POINTS) {
        Graphics2D g = (Graphics2D) getGraphics();
        if (m_canCreateNextPoint) {
          Point point1 = Point.createXY((double) e.getX(), (double) e.getY());
          m_centerPoint = point1;
          drawPoint(point1, g);
          m_canCreateNextPoint = false;
        } else {
          Point point2 = Point.createXY((double) e.getX(), (double)e.getY());
          Point point = Point.createXYF(m_centerPoint.getX(), m_centerPoint.getY(), Point.getDistance(m_centerPoint, point2));
          m_pointList.add(point);
          drawPoint(point, g);
          repaint();
          m_canCreateNextPoint = true;
        }
        if (m_pointList.size() == MAX_POINTS) {
          if (m_canCalculate) {
            calculate();
            repaint();
            m_canCalculate = false;
          }
        }
      }
    }
    if (button == MouseEvent.BUTTON3) {
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

  /**
   * 出力された描画をクリアする
   */
  private void clearCanvas() {
    m_pointList.clear();
    m_axisList.clear();
    Graphics g = getGraphics();
    g.clearRect(0, 0, getWidth(), getHeight());
    repaint();
    m_canCreateNextPoint = true;
    m_canCalculate = true;
  }

  /**
   * 指定した点を描画する.
   *
   * @param _point 点
   */
  private void drawPoint(Point _point, Graphics2D _g) {
    int radius = 4;
    int diameter = 2 * radius;
    _g.setColor(Color.ORANGE);
    _g.fillOval((int) _point.getX() - radius, (int) _point.getY() - radius, diameter, diameter);
    if (_point.getF() > 0.0) {
      _g.setColor(Color.BLACK);
      _g.setStroke(new BasicStroke(1.5f));
      _g.drawOval((int) (_point.getX() - _point.getF()), (int) (_point.getY() - _point.getF()), (int) (2 * _point.getF()), (int) (2 * _point.getF()));
    }
  }

  /**
   * 指定した対称軸を指定した色で描画する.
   * @param _axis 軸のパラメータ (ρ-θ表現)
   * @param _color 色
   */
  private void drawAxis(Axis _axis, Color _color, Graphics2D _g) {
    double angle = _axis.getAngle();
    double distance = _axis.getDistance();
    _g.setColor(_color);
    _g.setStroke(new BasicStroke(1.5f));
    if (Math.cos(angle) != 0) {
      double p1X = (distance - Math.sin(angle) * m_height) / Math.cos(angle);
      double p2X = (distance - Math.sin(angle) * 0) / Math.cos(angle);
      _g.drawLine((int)p1X, m_height, (int) p2X, 0);
    } else {
      double p1y = (distance - Math.cos(angle) * m_width) / Math.sin(angle);
      double p2y = (distance - Math.cos(angle) * 0) / Math.sin(angle);
      _g.drawLine(m_width, (int)p1y, 0, (int) p2y);
    }
  }

  /**
   * 対称軸の計算を行う.
   */
  private void calculate() {
    for (int i = 0; i < m_pointList.size(); i++) {
      for (int j = i + 1; j < m_pointList.size(); j++) {
        Pair pair = Pair.create(m_pointList.get(i), m_pointList.get(j));
        Vote vote = Vote.create(pair);
        m_axisList = vote.getAxis();
        Axis.sort(m_axisList);
      }
    }
    repaint();
  }

  /**
   * ファジィ点と軸の描画を行う
   * @param g  the <code>Graphics</code> context in which to paint
   */
  @Override
  public void paint(Graphics g) {
    super.paint(g);
    if (m_pointList.size() > 0) {
      for (Point point : m_pointList) {
        drawPoint(point, (Graphics2D) g);
      }
    }
    if (m_axisList.size() > 0) {
      Color color = Color.GREEN;
      for (Axis axis : m_axisList) {
        //Axis.printAxis(axis);
        Color setColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) ((axis.getGrade() - Vote.THRESHOLD) * 30));
        drawAxis(axis, setColor, (Graphics2D) g);
      }
      drawAxis(m_axisList.get(0), Color.BLUE, (Graphics2D) g);
    }
  }

  /**
   * コンストラクタ
   */
  private MyJPanel(int _width, int _height) {
    m_width = _width;
    m_height = _height;
    setPreferredSize(new Dimension(m_width, m_height));
    addMouseListener(this);
    addMouseMotionListener(this);
    this.setBackground(Color.WHITE);
  }

  /** 点の数の上限 */
  private static final int MAX_POINTS = 2;
  /** 点のリスト */
  private final List<Point> m_pointList = new ArrayList<>();
  /** 対称軸のリスト */
  private List<Axis> m_axisList = new ArrayList<>();
  /** ファジィ点の中心 */
  private Point m_centerPoint;
  /** キャンバスのサイズ(x軸方向) */
  private final int m_width;
  /** キャンバスのサイズ(y軸方向) */
  private final int m_height;
  /** 計算を行うかどうかのフラグ */
  private boolean m_canCalculate = true;
  /** 次の点をつくれるかのフラグ */
  private boolean m_canCreateNextPoint = true;
}