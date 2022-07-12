package jp.sagalab;

import javax.swing.*;
import java.awt.*;

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
    setSize(900, 700);
    Drawer canvas = new Drawer();
    canvas.setSize(800, 600);
    canvas.setBackground(Color.WHITE);
    setTitle("Line Symmetry");
    add(canvas);
    pack();
    setVisible( true );
  }
}