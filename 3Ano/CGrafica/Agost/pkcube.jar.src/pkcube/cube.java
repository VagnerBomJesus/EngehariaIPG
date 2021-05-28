/*    */ package pkcube;
/*    */ 
/*    */ import com.sun.j3d.utils.geometry.ColorCube;
/*    */ import com.sun.j3d.utils.universe.SimpleUniverse;
/*    */ import java.applet.Applet;
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.GraphicsConfiguration;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.media.j3d.Alpha;
/*    */ import javax.media.j3d.Background;
/*    */ import javax.media.j3d.BoundingSphere;
/*    */ import javax.media.j3d.BranchGroup;
/*    */ import javax.media.j3d.Canvas3D;
/*    */ import javax.media.j3d.RotationInterpolator;
/*    */ import javax.media.j3d.Transform3D;
/*    */ import javax.media.j3d.TransformGroup;
/*    */ import javax.vecmath.AxisAngle4d;
/*    */ import javax.vecmath.Color3f;
/*    */ 
/*    */ 
/*    */ public class cube
/*    */   extends Applet
/*    */ {
/*    */   public static void main(String[] args) {}
/*    */   
/* 27 */   Background background = null;
/* 28 */   Color3f[] BKcolor = new Color3f[3];
/* 29 */   int cclick = 0;
/*    */ 
/*    */   
/*    */   public void init() {
/* 33 */     GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
/* 34 */     Canvas3D cv = new Canvas3D(gc);
/* 35 */     setLayout(new BorderLayout());
/* 36 */     add(cv, "Center");
/* 37 */     cv.addMouseListener(new MouseAdapter() {
/*    */           public void mousePressed(MouseEvent ev) {
/* 39 */             cube.this.cclick = (cube.this.cclick + 1) % 3;
/* 40 */             cube.this.background.setColor(cube.this.BKcolor[cube.this.cclick]);
/*    */           }
/*    */         });
/* 43 */     BranchGroup bg = createSceneGraph();
/* 44 */     bg.compile();
/*    */     
/* 46 */     SimpleUniverse su = new SimpleUniverse(cv);
/* 47 */     su.getViewingPlatform().setNominalViewingTransform();
/* 48 */     su.addBranchGraph(bg);
/*    */   }
/*    */   
/*    */   private BranchGroup createSceneGraph() {
/* 52 */     BranchGroup root = new BranchGroup();
/* 53 */     ColorCube cube = new ColorCube(0.25D);
/* 54 */     Transform3D tr = new Transform3D();
/* 55 */     tr.setRotation(new AxisAngle4d(1.0D, 1.0D, 1.0D, 0.7853981633974483D));
/* 56 */     TransformGroup tg = new TransformGroup(tr);
/* 57 */     tg.addChild(cube);
/* 58 */     BoundingSphere bounds = new BoundingSphere();
/* 59 */     TransformGroup spin = new TransformGroup();
/* 60 */     spin.setCapability(18);
/* 61 */     Alpha alpha = new Alpha(-1, 2000L);
/* 62 */     RotationInterpolator rotator = new RotationInterpolator(alpha, spin);
/* 63 */     rotator.setSchedulingBounds(bounds);
/* 64 */     root.addChild(spin);
/* 65 */     spin.addChild(rotator);
/* 66 */     spin.addChild(tg);
/*    */     
/* 68 */     this.BKcolor[0] = new Color3f(255.0F, 0.0F, 0.0F);
/* 69 */     this.BKcolor[1] = new Color3f(0.0F, 255.0F, 0.0F);
/* 70 */     this.BKcolor[2] = new Color3f(0.0F, 0.0F, 255.0F);
/*    */ 
/*    */     
/* 73 */     this.background = new Background(this.BKcolor[0]);
/* 74 */     this.background.setApplicationBounds(bounds);
/* 75 */     this.background.setCapability(17);
/* 76 */     root.addChild(this.background);
/*    */     
/* 78 */     return root;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\pkcube\cube.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */