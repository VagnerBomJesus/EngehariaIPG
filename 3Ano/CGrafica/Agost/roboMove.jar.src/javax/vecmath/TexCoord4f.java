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
/*    */ 
/*    */ 
/*    */ public class TexCoord4f
/*    */   extends Tuple4f
/*    */   implements Serializable
/*    */ {
/*    */   static final long serialVersionUID = -3517736544731446513L;
/*    */   
/* 38 */   public TexCoord4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { super(paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public TexCoord4f(float[] paramArrayOfFloat) { super(paramArrayOfFloat); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public TexCoord4f(TexCoord4f paramTexCoord4f) { super(paramTexCoord4f); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 68 */   public TexCoord4f(Tuple4f paramTuple4f) { super(paramTuple4f); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 78 */   public TexCoord4f(Tuple4d paramTuple4d) { super(paramTuple4d); }
/*    */   
/*    */   public TexCoord4f() {}
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\vecmath\TexCoord4f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */