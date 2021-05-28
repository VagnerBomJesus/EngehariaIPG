package motioncapture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;

import javax.media.j3d.Appearance;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.GeometryUpdater;
import javax.media.j3d.IndexedLineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.PointArray;
import javax.media.j3d.PointAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnElapsedFrames;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;

import shapes.Floor;

public class MotionCapture extends Frame {
	BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 4.0);

	public static void main(String[] args) {
		Frame frame = new MotionCapture();
		frame.setPreferredSize(new Dimension(800, 800));
		frame.setTitle("Motion Capture");
		frame.pack();
		frame.setVisible(true);

	}

	// The Frame class doesn't have a
	// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// A possible solution is to override the processWindowEvent method.
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}

	public MotionCapture() {

		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		Canvas3D cv = new Canvas3D(gc);
		setLayout(new BorderLayout());
		add(cv, BorderLayout.CENTER);

		BranchGroup bg = createSceneGraph();
		bg.compile();
		SimpleUniverse su = new SimpleUniverse(cv);
		su.addBranchGraph(bg);
		// su.getViewingPlatform().setNominalViewingTransform();

		// Initial position of the view.
		Transform3D locator = new Transform3D();
		locator.lookAt(new Point3d(-4.5, 4.5, 4.5), new Point3d(0, 0, 0), new Vector3d(0, 1, 0));
		locator.invert();
		su.getViewingPlatform().getViewPlatformTransform().setTransform(locator);

		OrbitBehavior orbit = new OrbitBehavior(cv);
		orbit.setSchedulingBounds(bounds);
		su.getViewingPlatform().setViewPlatformBehavior(orbit);
	}

	private BranchGroup createSceneGraph() {
		BranchGroup root = new BranchGroup();

		// Floor
		root.addChild(new Floor(15, -2f, 2f, new Color3f(Color.LIGHT_GRAY), new Color3f(Color.GRAY), true));


		return root;
	}

	private void readData(float markers[][], String file) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";

		try {
			// br = new BufferedReader(new FileReader(csvFile));
			URL url = getClass().getClassLoader().getResource(file);
			br = new BufferedReader(new InputStreamReader(url.openStream()));

			int m = 0;
			while ((line = br.readLine()) != null) {

				String[] data = line.split(cvsSplitBy);
				for (int i = 0; i < data.length; i++)
					markers[m][i] = Float.parseFloat(data[i]);
				m++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
