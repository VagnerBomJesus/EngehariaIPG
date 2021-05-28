package ex10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.event.WindowEvent;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PointLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.universe.SimpleUniverse;

import appearance.MyMaterial;
import appearance.TextureAppearance;
import shapes.Floor;
import shapes.MyCone;
import shapes.MyObj;

public class Ex10 extends Frame {

	BoundingSphere bounds = new BoundingSphere(); // Bounds of the scene

	public static void main(String[] args) {
		Frame frame = new Ex10();
		frame.setPreferredSize(new Dimension(800, 800));
		frame.setTitle("Ex10");
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

	public Ex10() {
		// Create first canvas for the first view
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		Canvas3D cv = new Canvas3D(gc);

		// Add canvas to the frame
		setLayout(new BorderLayout());
		add(cv, BorderLayout.CENTER);

		// Create the a simple universe 
		SimpleUniverse su = new SimpleUniverse(cv);
		//su.getViewingPlatform().setNominalViewingTransform();

		// Create a personalized point of view 
		Transform3D viewTr = new Transform3D();
		viewTr.lookAt(new Point3d(-4.0, 1.5, 0.5), new Point3d(0.0, 0.0, 0.0), new Vector3d(0.0, 1.0, 0.0));
		viewTr.invert();
		su.getViewingPlatform().getViewPlatformTransform().setTransform(viewTr);
		
		BranchGroup bg = createSceneGraph();
		bg.compile();
		su.addBranchGraph(bg); // Add the content branch to the simple universe

		// Add a OrbitBehavior to control the first view with the mouse
		OrbitBehavior orbit = new OrbitBehavior(cv);
		orbit.setSchedulingBounds(bounds);
		su.getViewingPlatform().setViewPlatformBehavior(orbit);
	}

	private BranchGroup createSceneGraph() {
		BranchGroup root = new BranchGroup();

		// Floor
		root.addChild(new Floor(21, -1.5f, 1.5f, new Color3f(Color.LIGHT_GRAY), new Color3f(Color.GRAY), true));

		// Object to move.
		Appearance objApp = new Appearance();
		objApp.setMaterial(new MyMaterial(MyMaterial.BRASS));
		// Box obj = new Box(0.2f, 0.2f, 0.2f, Primitive.GENERATE_NORMALS, objApp);
		MyObj obj = new MyObj(0.2f, objApp);

		// TransformGroup to position the object
		Transform3D tr = new Transform3D();
		tr.setTranslation(new Vector3f(0f, 0f, 0f));
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(obj);
		// tg.addChild(new Axes(new Color3f(Color.BLUE), 3, 0.5f));

		// TransformGroup to move the object
		TransformGroup moveTg = new TransformGroup();
		moveTg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		moveTg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		moveTg.addChild(tg);
		root.addChild(moveTg);

		// Obstacles
		TextureAppearance wallApp = new TextureAppearance("images/brick_wall_texture.jpg", false, this);
		Box wall = new Box(1.0f, 0.5f + 0.001f, 0.01f, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS,
				wallApp);
		// root.addChild(wall);
		tr = new Transform3D();
		tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(-25)));
		tr.setTranslation(new Vector3f(0f, 0.5f, -0.7f));
		tg = new TransformGroup(tr);
		tg.addChild(wall);
		root.addChild(tg);

		TextureAppearance coneApp = new TextureAppearance("images/brick_wall_texture.jpg", true, this);
		MyCone cone = new MyCone(4, 0.5f, 0.3f, coneApp);
		// root.addChild(wall);
		tr = new Transform3D();
		// tr.setRotation(new AxisAngle4d(0, 1, 0, Math.toRadians(45)));
		tr.setTranslation(new Vector3f(-0.7f, 0f, 0.7f));
		tg = new TransformGroup(tr);
		tg.addChild(cone);
		root.addChild(tg);

		// Behavior to move the object.
		KeyControl kc = new KeyControl(moveTg, obj);
		kc.setSchedulingBounds(bounds);
		root.addChild(kc);

		// Background
		Background background = new Background(new Color3f(Color.DARK_GRAY));
		background.setApplicationBounds(bounds);
		root.addChild(background);

		// Lights
		AmbientLight ablight = new AmbientLight(true, new Color3f(Color.WHITE));
		ablight.setInfluencingBounds(bounds);
		root.addChild(ablight);

		PointLight ptlight = new PointLight(new Color3f(Color.WHITE), new Point3f(0f, 3f, 3f), new Point3f(1f, 0f, 0f));
		ptlight.setInfluencingBounds(bounds);
		root.addChild(ptlight);

		return root;
	}
}
