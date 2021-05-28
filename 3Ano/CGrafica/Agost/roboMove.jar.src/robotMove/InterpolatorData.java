/*    */ package robotMove;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.vecmath.AxisAngle4f;
/*    */ import javax.vecmath.Point3f;
/*    */ import javax.vecmath.Quat4f;
/*    */ 
/*    */ 
/*    */ public class InterpolatorData
/*    */ {
/* 12 */   private final List<Point3f> positions = new ArrayList();
/* 13 */   private final List<Quat4f> orientations = new ArrayList();
/*    */   
/*    */   void add(Point3f p, float a) {
/* 16 */     this.positions.add(p);
/*    */     
/* 18 */     AxisAngle4f aa = new AxisAngle4f(0.0F, 1.0F, 0.0F, (float)Math.toRadians(a));
/* 19 */     Quat4f q = new Quat4f();
/* 20 */     q.set(aa);
/*    */     
/* 22 */     this.orientations.add(q);
/*    */   }
/*    */ 
/*    */   
/* 26 */   Point3f[] getPositions() { return (Point3f[])this.positions.toArray(new Point3f[0]); }
/*    */ 
/*    */ 
/*    */   
/* 30 */   Quat4f[] getOrientations() { return (Quat4f[])this.orientations.toArray(new Quat4f[0]); }
/*    */ 
/*    */   
/*    */   float[] getKnots() {
/* 34 */     float[] knots = new float[this.positions.size()];
/* 35 */     float k = 1.0F / (knots.length - 1);
/* 36 */     for (int i = 0; i < knots.length; i++)
/* 37 */       knots[i] = i * k; 
/* 38 */     return knots;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\robotMove\InterpolatorData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */