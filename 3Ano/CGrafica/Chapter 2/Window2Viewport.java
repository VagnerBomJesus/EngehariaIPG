package window2viewport;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

import cglib2d.utils.W2V;

/**
 * 
 * @author ccarreto
 * Demonstration of the Window to Viewport mapping.
 *
 */

public class Window2Viewport extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new Window2Viewport();
		frame.setTitle("Mapeamento Window - Viewport");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}

}

class MyPanel extends JPanel {
	
	W2V w2v;  // Object to perform the mapping.

	int[][] Data = { { 6824, 7479, 122 },
			{ 1358, 7594, 612 },
			{ 2625, 1990, 880 }, 
			{ 6088, 7404, 266 }, 
			{ 6809, 2419, 358 },
			{ 8790, 2522, 755 }, 
			{ 5435, 4974, 45 }, 
			{ 7332, 8604, 812 },
			{ 5741, 6806, 590 }, 
			{ 0, 0, 900 }, 
			{ 7336, 6944, 783 } };

	// Limits of the Window.
	int XUmin = 0;  //-1000
	int YUmin = 0;  //-1000
	int XUmax = 9500;  //9500 
	int YUmax = 9000;

	// The data values (3rd column) are represented by circles.
	// It is considered that the maximum possible value in coordinates of the universe is 1000 and that the
	// maximum possible value for the radius of the circle in coordinates of the device is 20.
	// Thus, a given value is represented by a circle of radius = value * 20/1000
	int RMax = 1000;
	float Fr = 20f / RMax;

	// Limits of the Viewport.
	int XDmin = 30;
	int YDmin = 50;
	int XDmax = 270;
	int YDmax = 300;

	public MyPanel() {
		setPreferredSize(new Dimension(400, 400));
		w2v = new W2V(XUmin, YUmin, XUmax, YUmax, XDmin, YDmin, XDmax, YDmax);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.red);
		g2.drawRect(XDmin, YDmin, XDmax - XDmin - 1, YDmax - YDmin - 1);

		g2.setColor(Color.blue);

		for (int i = 0; i < 10; i++) {
			//Mapping of the coordinates.
			int x = w2v.MapX(Data[i][0]);
			
			int y = w2v.MapY(Data[i][1]);
			
			// Change the origin to the bottom left corner of the rectangle.
			//int y = YDmin + YDmax - w2v.MapY(Data[i][1]); 

			//Draw of the circle.
			int r = (int) (Fr * (Data[i][2]));
			g2.fillOval(x - r, y - r, 2 * r, 2 * r);
		}
	}
}
