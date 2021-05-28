/*    */ package javax.vecmath;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TexCoord3f
/*    */   extends Tuple3f
/*    */   implements Serializable
/*    */ {
/*    */   static final long serialVersionUID = -3517736544731446513L;
/*    */   
/* 36 */   public TexCoord3f(float paramFloat1, float paramFloat2, float paramFloat3) { super(paramFloat1, paramFloat2, paramFloat3); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public TexCoord3f(float[] paramArrayOfFloat) { super(paramArrayOfFloat); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public TexCoord3f(TexCoord3f paramTexCoord3f) { super(paramTexCoord3f); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 66 */   public TexCoord3f(Tuple3f paramTuple3f) { super(paramTuple3f); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 76 */   public TexCoord3f(Tuple3d paramTuple3d) { super(paramTuple3d); }
/*    */   
/*    */   public TexCoord3f() {}
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\vecmath\TexCoord3f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */