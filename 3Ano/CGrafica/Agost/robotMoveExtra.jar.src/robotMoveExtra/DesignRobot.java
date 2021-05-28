/*    */ package robotMoveExtra;
/*    */ 
/*    */ import com.sun.j3d.utils.geometry.Box;
/*    */ import com.sun.j3d.utils.geometry.Cone;
/*    */ import com.sun.j3d.utils.geometry.Cylinder;
/*    */ import javax.media.j3d.Appearance;
/*    */ import javax.media.j3d.BranchGroup;
/*    */ import javax.media.j3d.Material;
/*    */ import javax.media.j3d.Transform3D;
/*    */ import javax.media.j3d.TransformGroup;
/*    */ import javax.vecmath.AxisAngle4d;
/*    */ import javax.vecmath.Color3f;
/*    */ import javax.vecmath.Vector3d;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DesignRobot
/*    */   extends BranchGroup
/*    */ {
/*    */   public DesignRobot() {
/* 22 */     Appearance ap = new Appearance();
/* 23 */     Appearance roda = new Appearance();
/*    */     
/* 25 */     Material robotColor = new Material();
/* 26 */     robotColor.setAmbientColor(new Color3f(0.8F, 0.498039F, 0.196078F));
/* 27 */     robotColor.setDiffuseColor(new Color3f(0.8F, 0.498039F, 0.196078F));
/* 28 */     robotColor.setSpecularColor(new Color3f(0.8F, 0.498039F, 0.196078F));
/* 29 */     robotColor.setShininess(100.0F);
/* 30 */     ap.setMaterial(robotColor);
/*    */ 
/*    */     
/* 33 */     Material rodaClr = new Material();
/* 34 */     rodaClr.setAmbientColor(new Color3f(0.65F, 0.65F, 0.65F));
/* 35 */     rodaClr.setDiffuseColor(new Color3f(0.865F, 0.865F, 0.865F));
/* 36 */     rodaClr.setSpecularColor(new Color3f(0.55F, 0.55F, 0.55F));
/* 37 */     rodaClr.setShininess(50.0F);
/* 38 */     roda.setMaterial(rodaClr);
/*    */     
/* 40 */     Box obj = new Box(0.1F, 0.1F, 0.1F, 1, ap);
/*    */     
/* 42 */     Transform3D tr = new Transform3D();
/* 43 */     tr.setTranslation(new Vector3d(0.0D, 0.18D, 0.0D));
/* 44 */     TransformGroup tg = new TransformGroup(tr);
/* 45 */     tg.addChild(obj);
/* 46 */     addChild(tg);
/*    */     
/* 48 */     Cone obja = new Cone(0.1F, 0.1F, 1, ap);
/* 49 */     tr = new Transform3D();
/* 50 */     tr.setTranslation(new Vector3d(-0.0777D, 0.18D, -0.12D));
/* 51 */     tr.setRotation(new AxisAngle4d(1.0D, 0.0D, 0.0D, Math.toRadians(90.0D)));
/* 52 */     tg = new TransformGroup(tr);
/* 53 */     tg.addChild(obja);
/* 54 */     addChild(tg);
/*    */     
/* 56 */     Cone objb = new Cone(0.1F, 0.1F, 1, ap);
/* 57 */     tr = new Transform3D();
/* 58 */     tr.setTranslation(new Vector3d(0.0777D, 0.18D, -0.12D));
/* 59 */     tr.setRotation(new AxisAngle4d(1.0D, 0.0D, 0.0D, Math.toRadians(90.0D)));
/* 60 */     tg = new TransformGroup(tr);
/* 61 */     tg.addChild(objb);
/* 62 */     addChild(tg);
/*    */ 
/*    */     
/* 65 */     Cylinder cyD = new Cylinder(0.2F, 0.05F, 1, roda);
/* 66 */     tr.setScale(0.55D);
/* 67 */     tr.setTranslation(new Vector3f(0.1F, 0.1F, 0.0F));
/* 68 */     tr.setRotation(new AxisAngle4d(0.0D, 0.0D, 2.0D, (float)Math.toRadians(90.0D)));
/* 69 */     TransformGroup tg_cyD = new TransformGroup(tr);
/* 70 */     tg_cyD.addChild(cyD);
/* 71 */     addChild(tg_cyD);
/*    */     
/* 73 */     Cylinder cyE = new Cylinder(0.2F, 0.05F, 1, roda);
/* 74 */     tr.setScale(0.55D);
/* 75 */     tr.setTranslation(new Vector3f(-0.1F, 0.1F, 0.0F));
/* 76 */     tr.setRotation(new AxisAngle4d(0.0D, 0.0D, 2.0D, (float)Math.toRadians(90.0D)));
/* 77 */     TransformGroup tg_cyE = new TransformGroup(tr);
/* 78 */     tg_cyE.addChild(cyE);
/* 79 */     addChild(tg_cyE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\robotMoveExtra\DesignRobot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */