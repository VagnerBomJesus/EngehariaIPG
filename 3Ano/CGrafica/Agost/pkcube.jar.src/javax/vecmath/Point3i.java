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
/*    */ public class Point3i
/*    */   extends Tuple3i
/*    */   implements Serializable
/*    */ {
/*    */   static final long serialVersionUID = 6149289077348153921L;
/*    */   
/* 36 */   public Point3i(int paramInt1, int paramInt2, int paramInt3) { super(paramInt1, paramInt2, paramInt3); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public Point3i(int[] paramArrayOfInt) { super(paramArrayOfInt); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   public Point3i(Tuple3i paramTuple3i) { super(paramTuple3i); }
/*    */   
/*    */   public Point3i() {}
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\Point3i.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */