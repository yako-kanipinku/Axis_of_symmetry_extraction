package jp.sagalab;

import javax.swing.*;
import java.awt.*;
import java.awt.Canvas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame{

	public void drawFuzzyPoint(){

	}

	public void drawAxis(){

	}

	class WindowClosing extends WindowAdapter {
		public void windowClosing(WindowEvent e){
			int ans = JOptionPane.showConfirmDialog(Main.this,  "本当に閉じますか ?");
			if(ans == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}

	public Main() {
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		addWindowListener(new WindowClosing());
		setState(JFrame.ICONIFIED);
		//setIconImage(new ImageIcon("icon2.jpg").getImage());
		canvas.setSize(800, 600);
		canvas.setBackground(Color.WHITE);
		setTitle("Axis_of_symmetry_extraction");
		add(canvas);
		pack();
		setVisible( true );
	}

	private final Canvas canvas = new Canvas();

	public static void main(String[] args) {
		new Main();
	}
}
