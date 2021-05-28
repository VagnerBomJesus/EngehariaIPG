/*    */ package robotMove;
/*    */ 
/*    */ import com.sun.j3d.utils.geometry.Box;
/*    */ import com.sun.j3d.utils.geometry.Cone;
/*    */ import javax.media.j3d.Appearance;
/*    */ import javax.media.j3d.BranchGroup;
/*    */ import javax.media.j3d.Material;
/*    */ import javax.media.j3d.Transform3D;
/*    */ import javax.media.j3d.TransformGroup;
/*    */ import javax.vecmath.AxisAngle4d;
/*    */ import javax.vecmath.Vector3d;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ 
/*    */ public class Object
/*    */   extends BranchGroup
/*    */ {
/*    */   public Object() {
/* 19 */     Appearance ap = new Appearance();
/* 20 */     ap.setMaterial(new Material());
/*    */     
/* 22 */     Box obj1 = new Box(0.2F, 0.2F, 0.2F, ap);
/* 23 */     Transform3D tr = new Transform3D();
/* 24 */     tr.setTranslation(new Vector3f(0.0F, 0.21000001F, 0.0F));
/* 25 */     TransformGroup tg = new TransformGroup(tr);
/* 26 */     tg.addChild(obj1);
/* 27 */     addChild(tg);
/*    */     
/* 29 */     Cone obj2 = new Cone(0.1F, 0.2F, ap);
/* 30 */     tr = new Transform3D();
/* 31 */     tr.setTranslation(new Vector3d(0.0D, 0.2D, -0.30000000000000004D));
/* 32 */     tr.setRotation(new AxisAngle4d(1.0D, 0.0D, 0.0D, Math.toRadians(-90.0D)));
/* 33 */     tg = new TransformGroup(tr);
/* 34 */     tg.addChild(obj2);
/*    */     
/* 36 */     addChild(tg);
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\robotMove\Object.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */