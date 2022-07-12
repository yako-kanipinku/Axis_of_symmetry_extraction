package jp.sagalab;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

/**
 * canvasの表示と,描画に関する入力処理を行う.
 */
public class Drawer extends Canvas
        implements MouseListener, MouseMotionListener {

  /**
   * コンストラクタ
   */
  public Drawer() {
    addMouseListener(this);
    addMouseMotionListener(this);
    this.setBackground(Color.WHITE);
  }

  /**
   * 出力された描画をクリアする
   */
  public void clearCanvas() {
    Graphics g = this.getGraphics();
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
  public void drawArc(FuzzyPoint _point, Color _color) {
    Graphics g = this.getGraphics();
    g.setColor(_color);
    g.drawOval((int) (_point.getX() - _point.getR()), (int) (_point.getY() - _point.getR()), (int) (2 * _point.getR()), (int) (2 * _point.getR()));
  }

  /**
   * マウスボタンをクリックした時の処理
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseClicked(MouseEvent e) {
  }

  /**
   * マウスボタンを押したときの処理
   *
   * @param e the event to be processed
   */
  @Override
  public void mousePressed(MouseEvent e) {
    m_centerPoint = new Point2D.Double(e.getX(), e.getY());
    drawPoint(m_centerPoint, Color.ORANGE);
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
    Point2D.Double arcPoint = new Point2D.Double(e.getX(), e.getY());
    FuzzyPoint point = FuzzyPoint.create(m_centerPoint.getX(), m_centerPoint.getY(),m_centerPoint.distance(arcPoint));
    drawArc(point, Color.BLACK);
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
  /** ファジィ点の中心 */
  private Point2D.Double m_centerPoint;
}