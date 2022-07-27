package jp.sagalab;

import javax.swing.*;

/**
 * ウィンドウを出力するクラス
 */
public class Main extends JFrame{

  /**
   * mainメソッド
   */
  public static void main(String[] args) {
    new Main();
  }

  /**
   * ウィンドウの各種設定を行うコンストラクタ
   */
  private Main() {
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    MyJPanel myJPanel = MyJPanel.create(CANVAS_SIZE_X, CANVAS_SIZE_Y);
    JPanel panel = new JPanel();
    panel.add(myJPanel);
    getContentPane().add(panel);
    setTitle("Line Symmetry");
    pack();
    setVisible( true );
  }

  /** キャンバスサイズ(X軸方向) */
  public static final int CANVAS_SIZE_X = 800;
  /** キャンバスサイズ(Y軸方向) */
  public static final int CANVAS_SIZE_Y = 600;
}